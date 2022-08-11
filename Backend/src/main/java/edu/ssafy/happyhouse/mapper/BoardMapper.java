package edu.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.ssafy.happyhouse.DTO.BoardDTO;
@Mapper
public interface BoardMapper {
	public List<BoardDTO> selectBoard();
	public BoardDTO selectBoardByNo(int articleno);
	public void addHitByNo(int articleno);
	public int insertBoard(BoardDTO boardDto);
	public int updateBoard(BoardDTO boardDto);
	public int deleteBoard(int articleno);
}