package edu.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.happyhouse.DTO.FavoriteDTO;

@Mapper
public interface FavoriteMapper {
	List<FavoriteDTO> viewFavor(String id);
	public int insertFavor(FavoriteDTO favor);
	public int deleteFavor(String id, String aptName, String dongCode);
	public int checkFavor(String id, String aptName, String dongCode);
}
