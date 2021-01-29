package com.spring.board.sboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.board.model.BoardCmtDomain;
import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;

@Mapper
public interface BoardMapper {

	List<BoardDomain> selBoardList(BoardDTO p);

	int insBoard(BoardEntity p);

	BoardDomain selBoard(BoardDTO p);

	int updBoard(BoardEntity p);

	int updBoardHits(BoardDTO p);

	int delBoard(BoardDTO p);

//	댓글---------------------------------------------
	int insCmt(BoardCmtEntity p); 
	
	List<BoardCmtDomain> selCmtList(int i_board);
}
