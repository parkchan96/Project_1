package edu.ssafy.happyhouse.service;

import java.util.List;

import edu.ssafy.happyhouse.DTO.FavoriteDTO;

public interface FavoriteService {
	List<FavoriteDTO> viewFavor(String id);
	public boolean insertFavor(FavoriteDTO favor);
	public boolean deleteFavor(String id, String aptName, String dongCode);
	boolean checkFavor(String id, String aptName, String dongCode);
}
