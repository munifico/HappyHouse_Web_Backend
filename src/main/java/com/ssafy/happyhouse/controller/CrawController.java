package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/crawling")
public class CrawController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";


    @ApiOperation(value = "뉴스 기사들을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getNewsList() throws Exception {
		logger.debug("getNewsList - 호출");
		System.out.println("getNewsList!!");
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String WeatherURL = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260"; 
		Document doc = Jsoup.connect(WeatherURL).get();
		Elements elem = doc.select(".newsflash_body .type06_headline li dl");
		
		List<String> url = new ArrayList<>();
		List<String> img = new ArrayList<>();
		List<String> title = new ArrayList<>();
		List<String> content = new ArrayList<>();
		List<String> author = new ArrayList<>();
		
		for(int i = 0; i < elem.size(); i++) {
			url.add(elem.get(i).select("dt a").attr("href"));
			img.add(elem.get(i).select("dt img").attr("src"));
			title.add(elem.get(i).select("dt a").text().trim());
			content.add(elem.get(i).select("dd span.lede").text().trim());
			author.add(elem.get(i).select("dd span.writing").text().trim());
		}
		
//		url.add(elem.select("li dl dt a").attr("href"));
//		img.add(elem.select("li dl dt img").attr("src"));
//		title.add(elem.select("li dl dt a").text().trim());
//		content.add(elem.select("li dl dd span.lede").text().trim());
//		author.add(elem.select("li dl dd span.writing").text().trim());
		
		
		resultMap.put("urlList", url);
		resultMap.put("imgList", img);
		resultMap.put("titleList", title);
		resultMap.put("contentList", content);
		resultMap.put("authorList", author);
		
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		System.out.println("NewsList 성공");
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
