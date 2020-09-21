package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.ShoppingMall;
import com.ssafy.happyhouse.model.dto.ShoppingMallPageBean;
import com.ssafy.happyhouse.model.repo.ShoppingMallDao;

@Repository
public class ShoppingMallDaoImpl implements ShoppingMallDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ShoppingMall> searchAll(ShoppingMallPageBean bean) {
		return sqlSession.selectList("shopMapper.searchAll", bean);
	}

	@Override
	public ShoppingMall search(int num) {
		return sqlSession.selectOne("shopMapper.search", num);
	}

	@Override
	public int getTotalCount(String dong) {
		return sqlSession.selectOne("shopMapper.getTotalCount", dong);
	}

	@Override
	public List<ShoppingMall> testSearchAll(ShoppingMallPageBean shoppingMallPageBean) {
		return sqlSession.selectList("shopMapper.testSearchAll", shoppingMallPageBean);
	}

}
