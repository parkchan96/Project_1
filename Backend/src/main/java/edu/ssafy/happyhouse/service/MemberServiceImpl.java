package edu.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.happyhouse.DTO.MemberDTO;
import edu.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper mmapper;

	@Override
	public MemberDTO login(MemberDTO memberDto) throws Exception {
		if(memberDto.getId() == null || memberDto.getPassword() == null) {
			System.out.println("널");
			return null;
		}
		return mmapper.login(memberDto);
	}

	@Override
	public void insertMember(MemberDTO member) throws Exception {
		mmapper.insertMember(member);
	}

	@Override
	public MemberDTO viewMember(String id) {
		return mmapper.viewMember(id);
	}

	@Override
	public List<MemberDTO> viewAllMember() {
		return mmapper.viewAllMember();
	}

	@Override
	public void updateMember(MemberDTO member) {
		mmapper.updateMember(member);
	}

	@Override
	public void deleteMember(String id) {
		mmapper.deleteMember(id);
	}
	
	@Override
	public MemberDTO userInfo(String id) throws Exception {
		return mmapper.userInfo(id);
	}

	@Override
	public int checkId(String id) {
		// TODO Auto-generated method stub
		return mmapper.checkId(id);
	}
}