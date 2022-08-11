package edu.ssafy.happyhouse.service;

import java.util.List;

import edu.ssafy.happyhouse.DTO.FacilityDTO;
import edu.ssafy.happyhouse.DTO.HouseDealDTO;
import edu.ssafy.happyhouse.DTO.HouseInfoDTO;
import edu.ssafy.happyhouse.DTO.SidoGugunCodeDTO;

public interface AptService {
	public List<HouseInfoDTO> viewApt(String aptName);

	public List<HouseInfoDTO> viewDong(String dongName);

	public List<HouseInfoDTO> viewListSiGugunCode(String siGugunCode);

	public List<HouseInfoDTO> viewListDongCode(String dongCode);

	List<SidoGugunCodeDTO> getSido() throws Exception;

	List<SidoGugunCodeDTO> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDTO> getDongInGugun(String gugun) throws Exception;

	public String findSubway(String lat, String lng);

	public HouseInfoDTO searchApt(String aptName, String dongCode);

	public List<HouseDealDTO> searchAptDeal(String aptCode);

	public List<FacilityDTO> getfaclType();

}