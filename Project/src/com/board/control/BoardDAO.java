package com.board.control;

import java.util.List;

public interface BoardDAO {
	public int insert (boardVO vo);
	
	public int update(boardVO vo);
	
	public int delete(boardVO vo);
	
	public boardVO search(boardVO vo);
	
	public List<boardVO> select();
	
	public void search(String search, String searchString);
	
	public int getCount();
	
	public int selectBoard(boardVO vo);
	
}
