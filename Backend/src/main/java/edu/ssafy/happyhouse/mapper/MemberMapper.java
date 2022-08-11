package edu.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import edu.ssafy.happyhouse.DTO.MemberDTO;

@Mapper
public interface MemberMapper {
	MemberDTO login(MemberDTO memberDto) throws Exception;
	public void insertMember(MemberDTO user);
	public MemberDTO viewMember(String id);
	public List<MemberDTO> viewAllMember();
	public void updateMember(MemberDTO user);
	public void deleteMember(String id);
	public MemberDTO userInfo(String id) throws SQLException;
	public int checkId(String id);
}
