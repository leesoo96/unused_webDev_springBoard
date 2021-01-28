package com.spring.board.sboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.board.model.BoardCmtEntity;
import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;

@Mapper
public interface BoardMapper {
	
//	게시글 목록 읽어오기
	List<BoardDomain> selBoardList(BoardDTO p);
	
//	게시글 쓰기
	int insBoard(BoardEntity p);
	
//	 게시글 읽기
	BoardDomain selBoard(BoardDTO p);
	
//	조회수 증가
	int upBoardHits(BoardDTO p);
	
//	게시글 수정
	int upBoard(BoardEntity p);
	
//	게시글 삭제
	int delBoard(BoardDTO p);
}