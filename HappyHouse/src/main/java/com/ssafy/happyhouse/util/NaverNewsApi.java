package com.ssafy.happyhouse.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ssafy.happyhouse.model.dto.NewsDto;

public class NaverNewsApi {

	public List<NewsDto> parse() throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(readData());
		JSONArray array = (JSONArray) jsonObject.get("items");
		List<NewsDto> newsList = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			newsList.add(new NewsDto());
			JSONObject row = (JSONObject) array.get(i);
			newsList.get(i).setTitle((String) row.get("title"));
			newsList.get(i).setOriginallink((String) row.get("originallink"));
			newsList.get(i).setLink((String) row.get("link"));
			newsList.get(i).setDescription((String) row.get("description"));
			newsList.get(i).setPubdate((String) row.get("pubDate"));
		}
		System.out.println(newsList.toString());
		return newsList;
	}

	public String readData() {
		String clientId = "Vv8iPlizGOowW6JsvmRT";
		String clientSecret = "oGiUzrF3Ay";
		try {
			String keyword = URLEncoder.encode("부동산 주택", "UTF-8"); // 검색어";
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + keyword
					+ "&display=50&start=1&sort=date";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
