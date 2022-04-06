package com.ssafy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.HouseInfoDto;
import com.ssafy.model.StoreDto;
import com.ssafy.util.DBUtil;

public class HouseInfoDao {
	private static HouseInfoDao hdao = new HouseInfoDao(); 
	DBUtil db;
	private HouseInfoDao() {
		db = DBUtil.getInstance();				
	}
	public static HouseInfoDao getInstance() {
		return hdao;
	}
	public List<HouseInfoDto> donglist(String dongName) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = " select i.aptName, i.dongName, d.dealAmount,i.lat,i.lng from housedeal d, houseinfo i "
					+" where i.aptCode = d.aptCode and i.dongName = ? limit 50 ";
		List<HouseInfoDto> houses = new ArrayList<>();
		try {
			con = db.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dongName);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int j  = 1;
				HouseInfoDto house = new HouseInfoDto(
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++)
							);
				houses.add(house);
			}
			
		} catch (SQLException e) {
			System.out.println("동검색 목록 불러오기 오류: "+e);
		}finally {
			db.close(rs,psmt,con);
		}
		return houses;
	}
	public List<HouseInfoDto> aptlist(String aptName) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		StringBuilder listArticle = new StringBuilder();
		listArticle.append(" select i.aptName, i.dongName, d.dealAmount,i.lat,i.lng from housedeal d, houseinfo i where i.aptCode = d.aptCode \n");
		List<HouseInfoDto> houses = new ArrayList<>();
		try {
			con = db.getConnection();
			if(aptName != null && !aptName.isEmpty()) { // 검색 조건, null될일이 없다				
				listArticle.append("and i.aptName like concat('%', ?, '%') limit 50 \n"); // 포함				
			}
			psmt = con.prepareStatement(listArticle.toString());
			int idx = 0;
			if(aptName != null && !aptName.isEmpty()) { // 검색했다면
				psmt.setString(++idx, aptName);
			}
			rs = psmt.executeQuery();
			while(rs.next()) {
				int j  = 1;
				HouseInfoDto house = new HouseInfoDto(
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++)
							);
				houses.add(house);
			}
			
		} catch (SQLException e) {
			System.out.println("아파트검색 목록 불러오기 오류: "+e);
		}finally {
			db.close(rs,psmt,con);
		}
		return houses;
	}
	public List<StoreDto> storelist(String dongName) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = " select s.sname, s.type, s.dongName, s.lng, s.lat from store s, dongcode d where s.dongName = d.dongName and d.dongName=? limit 50 ";
		List<StoreDto> stores = new ArrayList<>();
		try {
			con = db.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dongName);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int j  = 1;
				StoreDto store = new StoreDto(
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++),
							rs.getString(j++)
							);
				stores.add(store);
			}
			
		} catch (SQLException e) {
			System.out.println("주변 상권 목록 불러오기 오류: "+e);
		}finally {
			db.close(rs,psmt,con);
		}
		return stores;
	}

}
