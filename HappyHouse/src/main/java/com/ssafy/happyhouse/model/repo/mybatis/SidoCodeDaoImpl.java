package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;
import com.ssafy.happyhouse.model.repo.SidoCodeDao;

@Repository
public class SidoCodeDaoImpl implements SidoCodeDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoCodeDto> selectSido() throws Exception {
		return sqlSession.selectList("sidocode.selectSido");
	}

	@Override
	public List<SidoCodeDto> selectGugun(String sido) throws Exception {
		return sqlSession.selectList("sidocode.selectGugun", sido);
	}

	@Override
	public List<HouseInfo> selectDong(String gugun) throws Exception {
		return sqlSession.selectList("sidocode.selectDong", gugun);
	}

	@Override
	public List<HouseInfo> selectApt(String dong) throws Exception {
		return sqlSession.selectList("sidocode.selectApt", dong);
	}
	
	@Override
	public HouseInfo selectDongLatLan(String dong) throws Exception {
		return sqlSession.selectOne("sidocode.selectDongLatLan", dong);
	}

}
