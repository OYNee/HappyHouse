package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.NoticePageBean;
import com.ssafy.happyhouse.model.repo.NoticeDao;

@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public List<NoticeDto> selectAll(NoticePageBean noticeDto) {
		return sqlSession.selectList("notice.selectAll", noticeDto);
	}

	@Override
	public NoticeDto select(int id) {
		return sqlSession.selectOne("notice.select", id);
	}

	@Override
	public int insert(NoticeDto noticeDto) {
		return sqlSession.insert("notice.insert", noticeDto);
	}

	@Override
	public int update(NoticeDto noticeDto) {
		return sqlSession.update("notice.update", noticeDto);
	}

	@Override
	public int delete(int id) {
		return sqlSession.delete("notice.delete", id);
	}

	@Override
	public int getTotalCount(NoticeDto noticeDto) {
		return sqlSession.selectOne("notice.getTotalCount", noticeDto);
	}


}
