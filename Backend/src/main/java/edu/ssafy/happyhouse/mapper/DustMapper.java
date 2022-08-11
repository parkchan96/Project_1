package edu.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.happyhouse.DTO.DustDTO;

@Mapper
public interface DustMapper {
	public List<DustDTO> viewDust();

	public void deleteDust();

	public void insertDust(DustDTO dustDto);
}