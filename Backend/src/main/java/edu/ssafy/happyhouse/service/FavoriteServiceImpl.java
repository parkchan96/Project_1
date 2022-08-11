package edu.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.happyhouse.DTO.FavoriteDTO;
import edu.ssafy.happyhouse.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteMapper fmapper;

	@Override
	public List<FavoriteDTO> viewFavor(String id) {
		return fmapper.viewFavor(id);
	}

	@Override
	public boolean insertFavor(FavoriteDTO favor) {
		return fmapper.insertFavor(favor) == 1;
	}

	@Override
	public boolean deleteFavor(String id, String aptName, String dongCode) {
		return fmapper.deleteFavor(id, aptName, dongCode) == 1;
	}

	@Override
	public boolean checkFavor(String id, String aptName, String dongCode) {
		return fmapper.checkFavor(id, aptName, dongCode) == 1;
	}

}
