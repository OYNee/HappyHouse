package com.ssafy.happyhouse.model.repo;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.NoticePageBean;

public interface NoticeDao {
	List<NoticeDto> selectAll(NoticePageBean noticeDto);
	NoticeDto select(int id);
	int insert(NoticeDto noticeDto);
	int update(NoticeDto noticeDto);
	int delete(int id);
	int getTotalCount(NoticeDto noticeDto);

}
