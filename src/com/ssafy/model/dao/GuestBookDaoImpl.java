package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.GuestBookDto;
import com.ssafy.model.ListParameterDto;
import com.ssafy.util.DBUtil;

public class GuestBookDaoImpl implements GuestBookDao {
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	private static GuestBookDao guestBookDao = new GuestBookDaoImpl();
	
	private GuestBookDaoImpl() {}

	public static GuestBookDao getGuestBookDao() {
		return guestBookDao;
	}

	@Override
	public void registerArticle(GuestBookDto guestBookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder registerArticle = new StringBuilder();
			registerArticle.append("insert into notice (userid, subject, content, regtime) \n");
			registerArticle.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(registerArticle.toString());
			int j = 1;
			pstmt.setString(j++, guestBookDto.getUserId());
			pstmt.setString(j++, guestBookDto.getSubject());
			pstmt.setString(j++, guestBookDto.getContent());
			
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<GuestBookDto> listArticle(ListParameterDto listParameterDto) throws SQLException {
		List<GuestBookDto> list = new ArrayList<GuestBookDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select g.articleno, g.userid, g.subject, g.content, m.name,  \n");
			listArticle.append(" 		case when date_format(g.regtime, '%y%m%d') = date_format(now(), '%y%m%d') \n");
			listArticle.append("			then date_format(g.regtime, '%H:%i:%d') \n");
			listArticle.append("			else date_format(g.regtime, '%y.%m.%d') \n");
			listArticle.append("		end regtime \n");
			listArticle.append("from notice g, user m \n");
			listArticle.append("where g.userid = m.id COLLATE utf8mb4_general_ci \n");
			String key = listParameterDto.getKey();
			String word = listParameterDto.getWord();
			if(!word.isEmpty()) {
				if(key.equals("subject")) {
					listArticle.append("and g.subject like concat('%',?,'%') \n");
				} else {
					listArticle.append("and g."+key+" = ? \n");
				}
			}
			listArticle.append("order by g.articleno desc limit ?, ? \n");
			pstmt = conn.prepareStatement(listArticle.toString());
			int idx = 0;
			if(!word.isEmpty()) {
				pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, listParameterDto.getStart());
			pstmt.setInt(++idx, listParameterDto.getCurrentPerPage());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestBookDto guestBookDto = new GuestBookDto();
				guestBookDto.setArticleNo(rs.getInt("articleno"));
				guestBookDto.setUserId(rs.getString("userid"));
				guestBookDto.setUserName(rs.getString("name"));
				guestBookDto.setSubject(rs.getString("subject").replace("<", "&lt;"));
				guestBookDto.setContent(rs.getString("content"));
				guestBookDto.setRegTime(rs.getString("regtime"));
				
				list.add(guestBookDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	@Override
	public int getTotalCount(ListParameterDto listParameterDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select count(articleno) \n");
			listArticle.append("from notice \n");
			String key = listParameterDto.getKey();
			String word = listParameterDto.getWord();
			if(!word.isEmpty()) {
				if(key.equals("subject")) {
					listArticle.append("where subject like concat('%',?,'%') \n");
				} else {
					listArticle.append("where "+key+" = ? \n");
				}
			}
			pstmt = conn.prepareStatement(listArticle.toString());
			if(!word.isEmpty()) {
				pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws SQLException {
		GuestBookDto guestBookDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder listArticle = new StringBuilder();
			listArticle.append("select g.articleno, g.userid, g.subject, g.content, m.name, g.regtime \n");
			listArticle.append("from notice g, user m \n");
			listArticle.append("where g.userid = m.id and g.articleno=? COLLATE utf8mb4_general_ci  \n");
			pstmt = conn.prepareStatement(listArticle.toString());
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				guestBookDto = new GuestBookDto();
				guestBookDto.setArticleNo(rs.getInt("articleno"));
				guestBookDto.setUserId(rs.getString("userid"));
				guestBookDto.setUserName(rs.getString("name"));
				guestBookDto.setSubject(rs.getString("subject"));
				guestBookDto.setContent(rs.getString("content"));
				guestBookDto.setRegTime(rs.getString("regtime"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return guestBookDto;
	}

	@Override
	public void updateArticle(GuestBookDto guestBookDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder updateArticle = new StringBuilder();
			updateArticle.append("update notice set subject=?, content=? \n");
			updateArticle.append("where articleno=?  COLLATE utf8mb4_general_ci");
			pstmt = conn.prepareStatement(updateArticle.toString());
			pstmt.setString(1, guestBookDto.getSubject());
			pstmt.setString(2, guestBookDto.getContent());
			pstmt.setInt(3, guestBookDto.getArticleNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder deleteArticle = new StringBuilder();
			deleteArticle.append("delete from notice \n");
			deleteArticle.append("where articleno=?");
			pstmt = conn.prepareStatement(deleteArticle.toString());
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	

}
