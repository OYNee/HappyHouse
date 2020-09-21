package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface QnAService {
	List<QnADto> listQnA(int pg, int sizePerPage);
	int insertQnA(QnADto qna);
	int updateQnA(QnADto qna);
	int deleteQnA(int id);
	QnADto detailQnA(int id);
	int updateReply(QnADto qna);
	List<QnADto> getQuestionByUserid(String userid);
	List<QnADto> searchQ(QnADto qna);
	public PageNavigation makePageNavigation(int pg, int sizePerPage) throws Exception;
}
