package edu.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ssafy.happyhouse.DTO.MemberDTO;
import edu.ssafy.happyhouse.service.JwtServiceImpl;
import edu.ssafy.happyhouse.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
@RequestMapping("/member")
@CrossOrigin("*")
@Api("MEMBER REST API")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDTO memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(memberDto.getId());
		System.out.println(memberDto.getPassword());
		try {
			MemberDTO loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				System.out.println("성공");
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				System.out.println("실패");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			System.out.println("실패");
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/logout")
	@ApiOperation(value = "member logout service")
	public @ResponseBody ResponseEntity<Map<String, Object>> logout(HttpSession session) {
		session.invalidate();
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "로그아웃 성공");
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@PostMapping("/regist")
	@ApiOperation(value = "member regist service")
	public @ResponseBody ResponseEntity<Map<String, Object>> registerMember(@RequestBody MemberDTO memberDTO) throws Exception {
		System.out.println("abcd");
		logger.debug("memberDTO info : {}", memberDTO);
		memberService.insertMember(memberDTO);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", SUCCESS);
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/view/{id}")
	@ApiOperation(value = "member info service")
	public @ResponseBody ResponseEntity<Map<String, Object>> viewUser(@PathVariable("id") String id) {
		MemberDTO member = memberService.viewMember(id);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "회원 조회 성공");
		map.put("memberinfo", member);
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/checkid/{id}")
	@ApiOperation(value = "member info list service")
	public @ResponseBody ResponseEntity<Map<String, Object>> checkId(@PathVariable("id") String id) {
		int result = memberService.checkId(id);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(result ==0) {
			map.put("res", SUCCESS);
			map.put("ok", result);
		}else {
			map.put("res", FAIL);
		}
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/info")
	@ApiOperation(value = "member info list service")
	public @ResponseBody ResponseEntity<Map<String, Object>> listUser() {
		List<MemberDTO> memberlist = memberService.viewAllMember();
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "회원 조회 성공");
		map.put("memberlist", memberlist);
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "member update service")
	public @ResponseBody ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDTO member) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (member != null) {
			memberService.updateMember(member);
			map.put("res", "업데이트 성공");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			return resEntity;
		}
		map.put("res", "업데이트 실패");
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "member delete service")
	public @ResponseBody ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("id") String id) {
		memberService.deleteMember(id);
		ResponseEntity<Map<String, Object>> resEntity = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("res", "삭제 성공");
		resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String id,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDTO memberDto = memberService.userInfo(id);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}