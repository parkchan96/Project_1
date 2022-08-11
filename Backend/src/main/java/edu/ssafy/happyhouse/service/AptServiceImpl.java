package edu.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.happyhouse.DTO.FacilityDTO;
import edu.ssafy.happyhouse.DTO.HouseDealDTO;
import edu.ssafy.happyhouse.DTO.HouseInfoDTO;
import edu.ssafy.happyhouse.DTO.SidoGugunCodeDTO;
import edu.ssafy.happyhouse.DTO.SubwayDTO;
import edu.ssafy.happyhouse.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {

	@Autowired
	private AptMapper aptMapper;

	@Override
	public List<HouseInfoDTO> viewApt(String aptName) {
		return aptMapper.viewApt(aptName);
	}

	@Override
	public List<HouseInfoDTO> viewDong(String dongName) {
		return aptMapper.viewDong(dongName);
	}

	@Override
	public List<HouseInfoDTO> viewListSiGugunCode(String siGugunCode) {
		return aptMapper.viewListSiGugunCode(siGugunCode);
	}

	@Override
	public List<HouseInfoDTO> viewListDongCode(String dongCode) {
		return aptMapper.viewListDongCode(dongCode);
	}

	@Override
	public List<SidoGugunCodeDTO> getSido() throws Exception {
		return aptMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDTO> getGugunInSido(String sido) throws Exception {
		return aptMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDTO> getDongInGugun(String gugun) throws Exception {
		return aptMapper.getDongInGugun(gugun);
	}

	@Override
	public String findSubway(String lat, String lng) {
		String subway = null;
		ArrayList<SubwayDTO> sublist = aptMapper.getSubway();
		String ansLat = null;
		String ansLng = null;
		double minDist = Double.MAX_VALUE;
		for (int i = 0; i < sublist.size(); i++) {
			double dist = Math.sqrt(Math.pow(Double.parseDouble(sublist.get(i).getLat()) - Double.parseDouble(lat), 2)
					+ Math.pow(Double.parseDouble(sublist.get(i).getLng()) - Double.parseDouble(lng), 2));
			if (minDist > dist) {
				ansLat = sublist.get(i).getLat();
				ansLng = sublist.get(i).getLng();
				minDist = dist;
			}
		}
		return aptMapper.findSubway(ansLat, ansLng);
	}

	@Override
	public HouseInfoDTO searchApt(String aptName, String dongCode) {
		return aptMapper.searchByAptName(aptName, dongCode);
	}

	@Override
	public List<HouseDealDTO> searchAptDeal(String aptCode) {
		return aptMapper.searchAptDeal(aptCode);
	}

	@Override
	public List<FacilityDTO> getfaclType() {
		return aptMapper.getfaclType();
	}
}