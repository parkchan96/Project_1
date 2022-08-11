package edu.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class MainController {
	
	@GetMapping("/listName/{aptName}")
	public @ResponseBody ResponseEntity<Map<String, Object>> listName(@PathVariable("aptName") String aptName) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", aptName);
		map.put("searchBy", "aptName");
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/listDong/{dongName}")
	public @ResponseBody ResponseEntity<Map<String, Object>> listDong(@PathVariable("dongName") String dongName) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", dongName);
		map.put("searchBy", "dong");
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
}
