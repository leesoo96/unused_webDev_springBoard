package com.spring.board.sboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
//	게시글 목록 
	public List<BoardDomain> selBoardList(BoardDTO p) {
		if(p.getTyp() == 0) {
			p.setTyp(1);
		}
		return mapper.selBoardList(p);
	}
	
//	글쓰기
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
	
//	글읽기
	public BoardDomain selBoard(BoardDTO p) {
		p.setHits(1);
		mapper.upBoardHits(p); // 조회수 증가
		
		return mapper.selBoard(p);
	}
	
//글삭제
	public int delBoard(BoardDTO p) {
		return mapper.delBoard(p);
	}
	
//	글수정
	public int upBoard(BoardEntity p) {
		return mapper.upBoard(p);
	}
	
//	댓글쓰기
	public int insCmt(BoardCmtEntity p) {
		if(p.getI_board() != 0) {
			return 1;
		}else {
			return 0;
		}
	}
}