package com.ssafy.happyhouse.model.repo.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.dto.QnAPageBean;
import com.ssafy.happyhouse.model.repo.QnADao;

@Repository
public class QnADaoImpl implements QnADao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QnADto> selectQnA(QnAPageBean bean) {
		return sqlSession.selectList("qna.selectQnA", bean);
	}

	@Override
	public int insertQnA(QnADto qna) {
		return sqlSession.insert("qna.insertQnA", qna);
	}

	@Override
	public int updateQnA(QnADto qna) {
		return sqlSession.update("qna.updateQnA", qna);
	}

	@Override
	public int deleteQnA(int id) {
		return sqlSession.delete("qna.deleteQnA", id);
	}

	@Override
	public QnADto detailQnA(int id) {
		return sqlSession.selectOne("qna.detailQnA", id);
	}

	@Override
	public int updateReply(QnADto qna) {
		return sqlSession.update("qna.updateReply", qna);
	}

	@Override
	public List<QnADto> getQuestionByUserid(String userid) {
		return sqlSession.selectList("qna.getQuestionByUserid", userid);
	}

	@Override
	public List<QnADto> searchQ(QnADto qna) {
		return sqlSession.selectList("qna.searchQ", qna);
	}
	
	@Override
	public int getTotalCount() {
		return sqlSession.selectOne("qna.getTotalCount");
	}
}
