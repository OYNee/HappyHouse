package com.ssafy.happyhouse.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlingContent {
	
	public String crawling(String url) {
		String tmp="";
		String result="";
		
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements element = doc.select("body");

		String text = element.toString();
		text = text.replaceAll("<br>", "");
		boolean open = false;
		for (int i = 0; i < text.length(); i++) {
			if (!open && text.charAt(i) == '<') {
				open = true;
				if (tmp.length() > result.length()) {
					result = tmp;
				}
				tmp = "";
			} else if (open && text.charAt(i) == '>') {
				open = false;
			} else if(!open) {
				tmp += text.charAt(i);
			}
		}
		return result;
	}

}
