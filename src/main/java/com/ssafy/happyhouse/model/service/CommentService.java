package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.Comment;

public interface CommentService {
	public List<Comment> retrieveComment(int articleno);
	public boolean writeComment(Comment comment);
	public boolean updateComment(Comment comment);
	public boolean deleteComment(int commentno);
}
