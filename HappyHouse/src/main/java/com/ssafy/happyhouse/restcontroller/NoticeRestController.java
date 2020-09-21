package com.ssafy.happyhouse.restcontroller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.NoticeListDto;
import com.ssafy.happyhouse.model.repo.AccountDao;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.util.PageNavigation;

@RestController
public class NoticeRestController {
	@Autowired
	private NoticeService noticeService;

	// 공지 작성(작성권한 확인)
	@PostMapping(value = "/notice", produces = "text/plain; charset=UTF-8")
	public String register(NoticeDto proRegReq, HttpSession httpSession) {
		System.out.println(proRegReq.toString());
		AccountDto accountDto = (AccountDto) httpSession.getAttribute("user_session");
		if (accountDto == null) {
			return "세션 null";
		} else {
			if (!"admin".equals(accountDto.getUserid())) {

				return "작성권한이 없습니다!";
			}
		}
		System.out.println(accountDto.getUserid());
		proRegReq.setUserid(accountDto.getUserid());
		return noticeService.insert(proRegReq) == 1 ? "등록 성공" : "등록 실패";
	}

	// 공지목록
	@GetMapping("/notice")
	public NoticeListDto noticeList(String option, String keyword, String pg, String spp) {
		NoticeListDto dto = new NoticeListDto();
		NoticeDto noticeDto = new NoticeDto();
		if (option.equals("all")) {
			noticeDto.setContent(keyword);
			noticeDto.setTitle(keyword);
		} else if (option.equals("title")) {
			noticeDto.setTitle(keyword);
		} else if (option.equals("content")) {
			noticeDto.setContent(keyword);
		}

		if (pg == null || "".equals(pg) || "0".equals(pg)) {
			pg = "1";
		}
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// sizePerPage

		try {
			dto.setList(noticeService.selectAll(Integer.parseInt(pg), sizePerPage, noticeDto));
			PageNavigation pageNavigation = noticeService.makePageNavigation(Integer.parseInt(pg), sizePerPage, noticeDto);
			dto.setPageNavigation(pageNavigation);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.err.println(dto.getList().size());
		System.out.println("getCountPerPage" + dto.getPageNavigation().getCountPerPage());
		System.out.println("getCurrentPage" + dto.getPageNavigation().getCurrentPage());
		System.out.println("getNaviSize" + dto.getPageNavigation().getNaviSize());
		System.out.println("getNewCount" + dto.getPageNavigation().getNewCount());
		System.out.println("getTotalCount" + dto.getPageNavigation().getTotalCount());
		System.out.println("getTotalPageCount" + dto.getPageNavigation().getTotalPageCount());

		return dto;
	}

	// 상세 보기
	@GetMapping("/notice/{id}")
	public NoticeDto noticeDetail(@PathVariable String id) {
		return noticeService.select(Integer.parseInt(id));
	}

	// 공지 수정
	@PutMapping(value = "/notice", produces = "text/plain; charset=UTF-8")
	public String modify(@RequestBody NoticeDto modReq) {
		System.out.println(modReq.toString());
		return noticeService.update(modReq) == 1 ? "수정 성공" : "수정 실패";
	}

	// 공지 삭제
	@DeleteMapping(value = "/notice/{id}", produces = "text/plain; charset=UTF-8")
	public String delete(@PathVariable String id) {
		return noticeService.delete(Integer.parseInt(id)) == 1 ? "삭제 성공" : "삭제 실패";
	}
}
