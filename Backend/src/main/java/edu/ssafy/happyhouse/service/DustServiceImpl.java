package edu.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ssafy.happyhouse.DTO.DustDTO;
import edu.ssafy.happyhouse.mapper.DustMapper;

@Service
public class DustServiceImpl implements DustService{
	@Autowired
	DustMapper dmapper;
	
	public List<DustDTO> viewDust() {
		return dmapper.viewDust();
	}

	@Override
	public void deleteDust() {
		dmapper.deleteDust();
	}

	@Override
	public void insertDust(DustDTO dustDto) {
		dmapper.insertDust(dustDto);
	}
}