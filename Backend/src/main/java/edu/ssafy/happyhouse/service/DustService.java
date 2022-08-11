package edu.ssafy.happyhouse.service;

import java.util.List;

import edu.ssafy.happyhouse.DTO.DustDTO;

public interface DustService {
	public List<DustDTO> viewDust();

	public void deleteDust();

	public void insertDust(DustDTO dustDto);
}