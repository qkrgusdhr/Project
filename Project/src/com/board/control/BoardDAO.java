package com.board.control;

import java.util.List;

public interface BoardDAO {
	public int insert (BoardVO vo);
	
	public int update(BoardVO vo);
	
	public int delete(BoardVO vo);
	
	public BoardVO search(BoardVO vo);
	
	public List<BoardVO> select();
	
	public List<BoardVO> search(String search, String searchString);
	
	public List<BoardVO> LikeCntASC();
	
	public int getCount();
	
	public int selectBoard(BoardVO vo);
	
	public String SearchUser(BoardVO vo);

	public int LikeInCrease(BoardVO vo);
	
	
}
