package edu.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ssafy.happyhouse.DTO.BoardDTO;
import edu.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<BoardDTO> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(BoardDTO boardDto) {
		return boardMapper.insertBoard(boardDto) == 1;
	}

	@Override
	public BoardDTO detailBoard(int articleno) {
		boardMapper.addHitByNo(articleno);
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(BoardDTO boardDto) {
		return boardMapper.updateBoard(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}
}