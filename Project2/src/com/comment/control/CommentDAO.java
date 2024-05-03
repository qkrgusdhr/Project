package com.comment.control;

import java.util.List;

public interface CommentDAO {
	public int insert(CommentVO vo);
	
	public List<CommentVO> select(int PostNum);

	int delete(CommentVO vo);
	
	
}
