package edu.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;


import edu.ssafy.happyhouse.DTO.MemberDTO;

public interface MemberService {
	MemberDTO login(MemberDTO memberDto) throws Exception;
	public void insertMember(MemberDTO user) throws Exception;
	public MemberDTO viewMember(String id);
	public List<MemberDTO> viewAllMember();
	public void updateMember(MemberDTO member);
	public void deleteMember(String id);
	public MemberDTO userInfo(String id) throws Exception;
	public int checkId(String id);
}
