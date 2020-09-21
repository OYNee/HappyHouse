package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {
	List<NoticeDto> selectAll(int pg, int sizePerPage, NoticeDto noticeDto);
	NoticeDto select(int id);
	int insert(NoticeDto noticeDto);
	int update(NoticeDto noticeDto);
	int delete(int id);
	public PageNavigation makePageNavigation(int pg, int sizePerPage, NoticeDto noticeDto) throws Exception;

}
