package edu.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.happyhouse.DTO.FacilityDTO;
import edu.ssafy.happyhouse.DTO.HouseDealDTO;
import edu.ssafy.happyhouse.DTO.HouseInfoDTO;
import edu.ssafy.happyhouse.DTO.SidoGugunCodeDTO;
import edu.ssafy.happyhouse.service.AptService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apt")
@CrossOrigin("*")
public class AptController {

	private final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	private AptService aptservice;

	@GetMapping("/searchName")
	@ApiOperation(value = "view apart service (search by aptName)")
	public ResponseEntity<List<HouseInfoDTO>> searchName(@RequestParam("aptName") String aptName)
			throws SQLException {
		logger.debug("aptName : {}", aptservice.viewApt(aptName));
		return new ResponseEntity<List<HouseInfoDTO>>(aptservice.viewApt(aptName), HttpStatus.OK);
	}

	@GetMapping("/searchDong")
	@ApiOperation(value = "view apart service (search by dongName)")
	public ResponseEntity<List<HouseInfoDTO>> searchDong(@RequestParam("dongName") String dongName)
			throws SQLException {
		logger.debug("dongName : {}", aptservice.viewDong(dongName));
		return new ResponseEntity<List<HouseInfoDTO>>(aptservice.viewDong(dongName), HttpStatus.OK);
	}
	
	@GetMapping("/searchBySiGugunCode")
	@ApiOperation(value = "search apartlist service (search by siGugunCode)")
	public ResponseEntity<List<HouseInfoDTO>> searchListByGugun(@RequestParam("siGugunCode") String siGugunCode) throws Exception {
		logger.debug("siGugunCode : {}", aptservice.viewListSiGugunCode(siGugunCode));
		return new ResponseEntity<List<HouseInfoDTO>>(aptservice.viewListSiGugunCode(siGugunCode), HttpStatus.OK);
	}
	
	@GetMapping("/searchByDongCode")
	@ApiOperation(value = "search apartlist service (search by dongCode)")
	public ResponseEntity<List<HouseInfoDTO>> searchListByDong(@RequestParam("dongCode") String dongCode) throws Exception {
		logger.debug("dongCode : {}", aptservice.viewListDongCode(dongCode));
		return new ResponseEntity<List<HouseInfoDTO>>(aptservice.viewListDongCode(dongCode), HttpStatus.OK);
	}
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDTO>> sido() throws Exception {
		logger.debug("sido : {}", aptservice.getSido());
		return new ResponseEntity<List<SidoGugunCodeDTO>>(aptservice.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDTO>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDTO>>(aptservice.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDTO>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDTO>>(aptservice.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/detail")
	@ApiOperation(value = "search apart detail info service")
	public @ResponseBody ResponseEntity<HouseInfoDTO> aptDetail(@RequestParam("aptName") String aptName, @RequestParam("dongCode") String dongCode)
			throws SQLException {
		HouseInfoDTO apt = aptservice.searchApt(aptName, dongCode);
		apt.setSubway(aptservice.findSubway(apt.getLat(), apt.getLng()));
		ResponseEntity<HouseInfoDTO> resEntity = null;
		resEntity = new ResponseEntity<HouseInfoDTO>(apt, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/dealList")
	@ApiOperation(value = "search deal status info service")
	public @ResponseBody ResponseEntity<List<HouseDealDTO>> aptDetail(@RequestParam("aptCode") String aptCode)
			throws SQLException {
		return new ResponseEntity<List<HouseDealDTO>>(aptservice.searchAptDeal(aptCode), HttpStatus.OK);
	}
	
	@GetMapping("/faclType")
	public ResponseEntity<List<FacilityDTO>> faclType() throws Exception {
		logger.debug("faclType : {}", aptservice.getfaclType());
		return new ResponseEntity<List<FacilityDTO>>(aptservice.getfaclType(), HttpStatus.OK);
	}
}