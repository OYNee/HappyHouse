package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.dto.QnAPageBean;
import com.ssafy.happyhouse.model.repo.QnADao;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnADao dao;

	@Override
	public List<QnADto> listQnA(int pg, int sizePerPage) {
		QnAPageBean bean = new QnAPageBean((pg - 1) * sizePerPage, sizePerPage);
		return dao.selectQnA(bean);
	}

	@Override
	public int insertQnA(QnADto qna) {
		return dao.insertQnA(qna);
	}

	@Override
	public int updateQnA(QnADto qna) {
		return dao.updateQnA(qna);
	}

	@Override
	public int deleteQnA(int id) {
		return dao.deleteQnA(id);
	}

	@Override
	public QnADto detailQnA(int id) {
		return dao.detailQnA(id);
	}

	@Override
	public int updateReply(QnADto qna) {
		return dao.updateReply(qna);
	}

	@Override
	public List<QnADto> getQuestionByUserid(String userid) {
		return dao.getQuestionByUserid(userid);
	}

	@Override
	public List<QnADto> searchQ(QnADto qna) {
		if (qna.getContent() != null) {
			qna.setContent("%" + qna.getContent() + "%");
		} else if (qna.getTitle() != null) {
			qna.setTitle("%" + qna.getTitle() + "%");
		} else {
			return null;
		}
		return dao.searchQ(qna);
	}
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;// 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = dao.getTotalCount();// 총 개시글 수(?)
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1) / sizePerPage;// 총 페이지수(?)
		pageNavigation.setTotalPageCount(totalPageCount+1);
		boolean startRange = currentPage <= naviSize;// startRange true:이전X, false : 이전 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;// endRange true:다음X, false : 다음 O
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
}
