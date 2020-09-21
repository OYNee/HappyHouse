package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.dto.QnAPageBean;

public interface QnADao {
	List<QnADto> selectQnA(QnAPageBean bean);
	int insertQnA(QnADto qna);
	int updateQnA(QnADto qna);
	int deleteQnA(int id);
	QnADto detailQnA(int id);
	int updateReply(QnADto qna);
	List<QnADto> getQuestionByUserid(String userid);
	List<QnADto> searchQ(QnADto qna);
	int getTotalCount();
}
