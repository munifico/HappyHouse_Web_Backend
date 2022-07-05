package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseFavor;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.service.HouseFavorService;

@RestController
@RequestMapping("/favor")
@CrossOrigin("*")
public class HouseFavorController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseFavorController.class);

	@Autowired
	private HouseFavorService houseFavorService;
	
	@GetMapping
	public ResponseEntity<List<HouseInfoDto>> getPopularList() throws Exception {
		logger.debug("popular : {}", houseFavorService.getPopularList());
		if(houseFavorService.getPopularList() != null)
			return new ResponseEntity<List<HouseInfoDto>>(houseFavorService.getPopularList(), HttpStatus.OK);
		else
			return new ResponseEntity<List<HouseInfoDto>>(houseFavorService.getPopularList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("{userid}")
	public ResponseEntity<List<Integer>> getFavor(@PathVariable String userid) throws Exception {
		logger.debug("getFavor : {}", houseFavorService.getFavor(userid));
		if(houseFavorService.getFavor(userid) != null)
			return new ResponseEntity<List<Integer>>(houseFavorService.getFavor(userid), HttpStatus.OK);
		else
			return new ResponseEntity<List<Integer>>(houseFavorService.getFavor(userid), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@GetMapping("/favorlist/{userid}")
	public ResponseEntity<List<HouseInfoDto>> getFavorList(@PathVariable String userid) throws Exception {
		logger.debug("popular : {}", houseFavorService.getFavorList(userid));
		if(houseFavorService.getFavorList(userid) != null)
			return new ResponseEntity<List<HouseInfoDto>>(houseFavorService.getFavorList(userid), HttpStatus.OK);
		else
			return new ResponseEntity<List<HouseInfoDto>>(houseFavorService.getFavorList(userid), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping
	public ResponseEntity<Integer> insertFavor(@RequestBody HouseFavor houseFavor) throws Exception {
		logger.debug("insertFavor - 호출");
		//if (houseFavorService.insertFavor(houseFavor) == 1) {
			return new ResponseEntity<Integer>(houseFavorService.insertFavor(houseFavor), HttpStatus.OK);
		//} else {
		//	return new ResponseEntity<Integer>(houseFavorService.insertFavor(houseFavor), HttpStatus.INTERNAL_SERVER_ERROR);
		///}
	}
	
	@DeleteMapping
	public ResponseEntity<Integer> deleteFavor(@RequestBody HouseFavor houseFavor) throws Exception {
		logger.debug("deleteFavor - 호출");
		//if (houseFavorService.deleteFavor(houseFavor) == 1) {
			return new ResponseEntity<Integer>(houseFavorService.deleteFavor(houseFavor), HttpStatus.OK);
		//}
		//return new ResponseEntity<Integer>(houseFavorService.deleteFavor(houseFavor), HttpStatus.NO_CONTENT);
	}
}