package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.Comment;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
    @Autowired
	private CommentMapper commentMapper;

    @Override
	public List<Comment> retrieveComment(int articleno) {
		return commentMapper.selectComment(articleno);
	}
    
  	@Override
	public boolean writeComment(Comment comment) {
		return commentMapper.insertComment(comment) == 1;
	}

	@Override
	@Transactional
	public boolean updateComment(Comment comment) {
		return commentMapper.updateComment(comment) == 1;
	}

	@Override
	@Transactional
	public boolean deleteComment(int commentno) {
		return commentMapper.deleteComment(commentno) == 1;
	}

}