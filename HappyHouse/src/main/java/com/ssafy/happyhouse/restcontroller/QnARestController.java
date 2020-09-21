package com.ssafy.happyhouse.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.dto.QnAListDto;
import com.ssafy.happyhouse.model.service.QnAService;
import com.ssafy.happyhouse.util.PageNavigation;

@RestController
public class QnARestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;

	// q&a 리스트 불러오기
	@GetMapping("qna")
	public QnAListDto retrieveBoard(String pg, String spp) throws Exception {
		if (pg == null || "".equals(pg) || "0".equals(pg)) {
			pg = "1";
		}
		QnAListDto dto = new QnAListDto();
		try {
			
			int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// sizePerPage
			dto.setList(qnaService.listQnA(Integer.parseInt(pg), sizePerPage));
			PageNavigation pageNavigation = qnaService.makePageNavigation(Integer.parseInt(pg), sizePerPage);
			dto.setPageNavigation(pageNavigation);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return dto;
	}
    
	// question 작성
   	@PostMapping("qna/write-question")
   	public String writeBoard(QnADto qna, HttpSession httpSession) {
   		//test
   		AccountDto accountDto = (AccountDto)httpSession.getAttribute("user_session");
   		qna.setUserid(accountDto.getUserid());
   		System.out.println(qna.toString());
   		if (qnaService.insertQnA(qna) == 1) {
   			return "1";
   		}
   		return "0";
   	}
    
   	// Question 수정
	@PutMapping("qna/updateQnA")
	public String updateQnA(@RequestBody QnADto qna) {
		if (qnaService.updateQnA(qna) == 1) {
			return "1";
		}
		return "0";
	}
    
	// 응답수정
   	@PutMapping("qna/updateReply")
   	public String updateReply(@RequestBody QnADto qna, HttpSession httpSession) {
   		AccountDto acc = (AccountDto)httpSession.getAttribute("user_session");
   		if (!"admin".equals(acc.getUserid())) {
			return "0";
		}
   		qna.setReplyUserid("admin");
   		System.out.println(qna.toString());
   		if (qnaService.updateReply(qna) == 1) {
   			return "1";
   		}
   		return "0";
   	}
   	
   	// 삭제
	@DeleteMapping("qna/{id}")
	public String deleteBoard(@PathVariable int id) {
		if (qnaService.deleteQnA(id) == 1) {
			return "1";
		}
		return "0";
	}
    
	// 상세보기
	@GetMapping("qna/{id}")
	public QnADto detailBoard(@PathVariable int id) {
		System.out.println(qnaService.detailQnA(id).toString());
		return qnaService.detailQnA(id);
	}
	
	// 검색(title or content 둘 중 하나)
	@GetMapping("qna/search")
	public List<QnADto> searchQ(QnADto qna) {
		System.out.println(qna.toString());
		return qnaService.searchQ(qna);
	}
	
	// 마이페이지 내 게시물 목록
	@GetMapping("myboard")
	public List<QnADto> myboardList(HttpSession httpSession) throws Exception {
		AccountDto accountDto = (AccountDto)httpSession.getAttribute("user_session");
		System.out.println( qnaService.getQuestionByUserid(accountDto.getUserid()));
		return qnaService.getQuestionByUserid(accountDto.getUserid());
	}
}
