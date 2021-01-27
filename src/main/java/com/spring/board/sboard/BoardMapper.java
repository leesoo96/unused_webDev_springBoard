package com.spring.board.sboard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.spring.board.model.BoardDTO;
import com.spring.board.model.BoardDomain;
import com.spring.board.model.BoardEntity;
import com.spring.board.model.ManageBoardEntity;

@Mapper
public interface BoardMapper {

	List<BoardDomain> selBoardList(BoardDTO param); // 게시글 목록 읽어오기
	
	int insertBoard(BoardEntity param); // 게시글 쓰기
	
	BoardDomain selBoard(BoardDTO param); // 게시글 읽기
	
	int delBoard(BoardDTO param); // 게시글 삭제
}
