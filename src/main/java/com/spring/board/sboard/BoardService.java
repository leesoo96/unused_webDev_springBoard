package com.spring.board.sboard;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;
import com.spring.board.model.ManageBoardEntity;

@Service
public class BoardService {

	@Autowired
	public BoardMapper mapper;
	
//	각 게시판 게시글 목록 읽어오기
	public List<BoardDomain> selBoardList(BoardDTO param){
		if(param.getTyp() == 0) {
			param.setTyp(1);
		}
		return mapper.selBoardList(param);
	}
	
//	게시글 쓰기
	public int insertBoard(BoardEntity param) {
		return mapper.insertBoard(param);
	}
	
//	글읽기
	public BoardDomain selBoard(BoardDTO param) {
		return mapper.selBoard(param);
	}
	
//	글삭제
	public int delBoard(BoardDTO param) {
		return mapper.delBoard(param);
	}
}
