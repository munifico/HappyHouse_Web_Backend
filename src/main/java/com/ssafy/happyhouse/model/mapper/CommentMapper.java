package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Comment;
@Mapper
public interface CommentMapper {
	public List<Comment> selectComment();
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int commentno);
	public List<Comment> selectComment(int articleno);
}