package com.ssafy.happyhouse.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.NewsListDto;
import com.ssafy.happyhouse.model.service.NewsService;
import com.ssafy.happyhouse.util.CrawlingContent;
import com.ssafy.happyhouse.util.PageNavigation;

@RestController
public class NewsRestController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/newslist")
	private NewsListDto newsList(String keyword, String pg, String spp) {

		NewsListDto newsListDto = new NewsListDto();
		newsListDto.setKeyword(keyword);
		if (pg == null || "".equals(pg) ||"0".equals(pg)) {
			pg = "1";
		}
		if ("".equals(keyword)) {
			keyword = null;
		}
		if (keyword != null) {
			keyword = "%" + keyword + "%";
		}
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// sizePerPage
		try {
				newsListDto.setList(newsService.search(Integer.parseInt(pg), sizePerPage, keyword));
				PageNavigation pageNavigation = newsService.makePageNavigation(Integer.parseInt(pg), sizePerPage, keyword);
				newsListDto.setPageNavigation(pageNavigation);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		System.err.println(newsListDto.getList().size());
//		System.out.println("getCountPerPage" + newsListDto.getPageNavigation().getCountPerPage());
//		System.out.println("getCurrentPage" + newsListDto.getPageNavigation().getCurrentPage());
//		System.out.println("getNaviSize" + newsListDto.getPageNavigation().getNaviSize());
//		System.out.println("getNewCount" + newsListDto.getPageNavigation().getNewCount());
//		System.out.println("getTotalCount" + newsListDto.getPageNavigation().getTotalCount());
//		System.out.println("getTotalPageCount" + newsListDto.getPageNavigation().getTotalPageCount());
		return newsListDto;
	}
	
	@GetMapping("/news")
	private String news(String url) {
		
		String tmp = new CrawlingContent().crawling(url);
		tmp = tmp.replaceAll("\\n", "<br>");
		System.out.println(url + "result : " + tmp);
		return tmp;
	}
}
