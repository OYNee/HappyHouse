package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.repo.HouseDao;

@Repository
public class HouseDaoImple implements HouseDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HouseDeal> searchAll(HousePageBean bean) {
		return sqlSession.selectList("houseMapper.searchAll", bean);
	}

	@Override
	public HouseDeal search(int no) {
		return sqlSession.selectOne("houseMapper.search", no);

	}

	@Override
	public int getTotalCount(HousePageBean bean) {
		return sqlSession.selectOne("houseMapper.getTotalCount", bean);
	}

	@Override
	public List<HouseDeal> testSearchAll(HousePageBean bean) {
		return sqlSession.selectList("houseMapper.testSearch", bean);
	}

	@Override
	public List<HouseDeal> getList(HousePageBean housePageBean) {
		return sqlSession.selectList("houseMapper.getList", housePageBean);
	}
	
	

}
