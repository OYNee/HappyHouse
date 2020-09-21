package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NewsPageBean;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.NoticePageBean;
import com.ssafy.happyhouse.model.repo.NoticeDao;
import com.ssafy.happyhouse.util.PageNavigation;
@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public NoticeDto select(int id) {
		return noticeDao.select(id);
	}

	@Override
	public int insert(NoticeDto noticeDto) {
		return noticeDao.insert(noticeDto);
	}

	@Override
	public int update(NoticeDto noticeDto) {
		return noticeDao.update(noticeDto);
	}

	@Override
	public int delete(int id) {
		return noticeDao.delete(id);
	}

	@Override
	public List<NoticeDto> selectAll(int pg, int sizePerPage, NoticeDto noticeDto) {
		if (noticeDto.getContent() != null) {
			noticeDto.setContent("%" +noticeDto.getContent()+"%");
		} 
		if (noticeDto.getTitle() != null) {
			noticeDto.setTitle("%" +noticeDto.getTitle()+"%");
		} 
		NoticePageBean bean = new NoticePageBean(noticeDto.getTitle(), noticeDto.getContent(),(pg - 1) * sizePerPage, sizePerPage);
		return noticeDao.selectAll(bean);
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, NoticeDto noticeDto) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;// 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = noticeDao.getTotalCount(noticeDto);// 총 개시글 수(?)
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
