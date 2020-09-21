package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.NewsPageBean;
import com.ssafy.happyhouse.model.repo.NewsDao;

@Repository
public class NewsDaoImpl implements NewsDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertList(List<NewsDto> list) {
		return sqlSession.insert("news.insertList", list);
	}
	@Override
	public int insert(NewsDto dto) {
		return sqlSession.insert("news.insert", dto);
	}

	@Override
	public List<NewsDto> selectAll() {
		return sqlSession.selectList("news.selectAll");
	}

	@Override
	public List<NewsDto> search(NewsPageBean been) {
		return sqlSession.selectList("news.searchAll", been);
	}
	@Override
	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("news.getTotalCount", keyword);
	}
	@Override
	public List<NewsDto> getDailyNews() {
		return sqlSession.selectList("news.getDailyNews");
	}

}
