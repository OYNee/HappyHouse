package com.ssafy.happyhouse.model.repo.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.repo.BIdCheckDAO;

@Repository
public class BIdCheckDAOImpl implements BIdCheckDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int idCheck(String idFromAjax) throws Exception {
		return sqlSession.selectOne("accountMapper.idCheck", idFromAjax);
		
	}//idCheck

}//class
