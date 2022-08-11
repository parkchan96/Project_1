package edu.ssafy.happyhouse.service;

import java.util.List;

import edu.ssafy.happyhouse.DTO.BoardDTO;

public interface BoardService {
	public List<BoardDTO> retrieveBoard();
	public BoardDTO detailBoard(int articleno);
	public boolean writeBoard(BoardDTO boardDto);
	public boolean updateBoard(BoardDTO boardDto);
	public boolean deleteBoard(int articleno);
}
