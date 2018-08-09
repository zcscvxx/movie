package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.common.ConnFactory;
import movie.vo.Customer;
import movie.vo.Movie;

public class CustomerDao implements IDao<Customer, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");
	
	@Override
	public void insert(Customer vo) {
		String sql = "INSERT INTO CUSTOMER(CUS_ID,CUS_NAME,CUS_AGE,CUS_GENDER,CUS_TEL) "
				+ "VALUES(SEQ_CUSTOMER.NEXTVAL,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCus_name());
			pstmt.setInt(2, vo.getCus_age());
			pstmt.setString(3, vo.getCus_gender());
			pstmt.setString(4, vo.getCus_tel());
			int res = pstmt.executeUpdate();
			if(res>0) {
				System.out.println("회원가입 완료");
			}else {
				System.out.println("회원가입 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void delete(Integer primaryKey) {
		String sql = "DELETE FROM CUSTOMER "
				+ "WHERE CUS_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			int res = pstmt.executeUpdate();
			if(res>0) {
				System.out.println("회원탈퇴 완료");
			}else {
				System.out.println("회원탈퇴 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Customer vo) {
		String sql = "UPDATE CUSTOMER "
				+ "SET CUS_NAME=?,CUS_AGE=?,CUS_GENDER=?,CUS_TEL=? "
				+ "WHERE CUS_ID=?";
		PreparedStatement pstmt =null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCus_name());
			pstmt.setInt(2, vo.getCus_age());
			pstmt.setString(3, vo.getCus_gender());
			pstmt.setString(4, vo.getCus_tel());
			pstmt.setInt(5, vo.getCus_id());
			
			int res = pstmt.executeUpdate();
			if(res>0) {
				System.out.println("수정 완료");
			}else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Customer select(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll() {
		String sql ="SELECT * FROM CUSTOMER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<Customer> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer vo = new Customer();
				vo.setCus_id(rs.getInt("CUS_ID"));
				vo.setCus_name(rs.getString("CUS_NAME"));
				vo.setCus_age(rs.getInt("CUS_AGE"));
				vo.setCus_gender(rs.getString("CUS_GENDER"));
				vo.setCus_tel(rs.getString("CUS_TEL"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List selectt(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
