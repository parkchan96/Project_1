package edu.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.happyhouse.DTO.FavoriteDTO;
import edu.ssafy.happyhouse.service.FavoriteService;

@RestController
@RequestMapping("/favor")
@CrossOrigin("*")
public class FavoriteController {
	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

	@Autowired
	private FavoriteService favorService;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@PostMapping("/regist")
	public @ResponseBody ResponseEntity<String> registFavor(@RequestBody FavoriteDTO favorDTO) {
		logger.debug("registFavor - 호출");
		if (favorService.insertFavor(favorDTO)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/info")
	public @ResponseBody ResponseEntity<List<FavoriteDTO>> viewFavor(@RequestParam("id") String id) {
		logger.debug("id : {}", favorService.viewFavor(id));
		return new ResponseEntity<List<FavoriteDTO>>(favorService.viewFavor(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<String> deleteFavor(@RequestParam("id") String id, @RequestParam("aptName") String aptName, @RequestParam("dongCode") String dongCode) {		
		logger.debug("deleteFavor - 호출");
		if (favorService.deleteFavor(id, aptName, dongCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/check")
	public @ResponseBody ResponseEntity<String> checkFavor(@RequestParam("id") String id, @RequestParam("aptName") String aptName, @RequestParam("dongCode") String dongCode) {
		logger.debug("checkFavor - 호출");
		if (favorService.checkFavor(id, aptName, dongCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
