package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/aptlist")
	public ResponseEntity<List<HouseInfoDto>> aptlist(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptList(dong), HttpStatus.OK);
	}
	
	@GetMapping("/aptlistinfo")
	public ResponseEntity<List<HouseInfoDto>> aptlistinfo(@RequestParam("aptlist") String aptlist) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptListInfo(aptlist), HttpStatus.OK);
	}

	
	@GetMapping("/aptByText")
	public ResponseEntity<List<HouseInfoDto>> aptByText(@RequestParam("text") String text) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptByText(text), HttpStatus.OK);
	}
	
	@GetMapping("/aptByAptCode")
	public ResponseEntity<List<HouseInfoDto>> aptByAptCode(@RequestParam("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptByAptCode(aptCode), HttpStatus.OK);
	}
	
	@GetMapping("/searchBygugun")
	public ResponseEntity<List<HouseInfoDto>> searchBygugun(@RequestParam("gugun") String gugun, @RequestParam("userid") String userid) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getSearchBygugun(gugun, userid), HttpStatus.OK);
	}
	
	
}