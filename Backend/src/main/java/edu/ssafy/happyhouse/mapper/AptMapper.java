package edu.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.happyhouse.DTO.FacilityDTO;
import edu.ssafy.happyhouse.DTO.HouseDealDTO;
import edu.ssafy.happyhouse.DTO.HouseInfoDTO;
import edu.ssafy.happyhouse.DTO.SidoGugunCodeDTO;
import edu.ssafy.happyhouse.DTO.SubwayDTO;

@Mapper
public interface AptMapper {
	
	public List<HouseInfoDTO> viewApt(String aptName);

	public List<HouseInfoDTO> viewDong(String dongName);

	public List<HouseInfoDTO> viewListSiGugunCode(String siGugunCode);

	public List<HouseInfoDTO> viewListDongCode(String dongCode);

	public List<SidoGugunCodeDTO> getSido() throws SQLException;

	public List<SidoGugunCodeDTO> getGugunInSido(String sido) throws SQLException;

	public List<HouseInfoDTO> getDongInGugun(String gugun) throws SQLException;

	public ArrayList<SubwayDTO> getSubway();

	public String findSubway(String lat, String lng);

	public HouseInfoDTO searchByAptName(String aptName, String dongCode);

	public List<HouseDealDTO> searchAptDeal(String aptCode);

	public List<FacilityDTO> getfaclType();
	
}