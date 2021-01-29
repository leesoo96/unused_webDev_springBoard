package com.spring.board.sboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.model.BoardCmtDomain;
import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;

	public List<BoardDomain> selBoardList(BoardDTO p) {
		if(p.getTyp() == 0) {
			p.setTyp(1);
		}
		return mapper.selBoardList(p);
	}

	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}

	public BoardDomain selBoard(BoardDTO p) {
		mapper.updBoardHits(p);	
		return mapper.selBoard(p);
	}
	
	public int updBoard(BoardEntity p) {
		return mapper.updBoard(p);
	}

	public int delBoard(BoardDTO p) {
		return mapper.delBoard(p);
	}

//	댓글처리코드---------------------------------------------
	public int insCmt(BoardCmtEntity p) {
		return mapper.insCmt(p);
	}
	
	public List<BoardCmtDomain> selCmtList(int i_board){
		return mapper.selCmtList(i_board);
	}
}
