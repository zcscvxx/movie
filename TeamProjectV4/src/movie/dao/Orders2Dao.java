package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import movie.common.ConnFactory;
import movie.vo.Orders2;

public class Orders2Dao implements IDao<Orders2, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");

	@Override
	public void insert(Orders2 vo) {
		String sql = "INSERT INTO ORDERS2 "
				+ "VALUES(SEQ_ORDERS2.NEXTVAL,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vo.getCus_name());
			pstmt.setString(2,vo.getCus_tel());
			pstmt.setString(3,vo.getMovie_name());
			pstmt.setString(4,vo.getOrder_date());
			pstmt.setString(5,vo.getOrder_time());
			pstmt.setInt(6,vo.getOrder_amount());
			pstmt.setString(7,vo.getOrder_seat());
			pstmt.setString(8,vo.getMovie_price());
			
			int res=pstmt.executeUpdate();
			if(res>0) {
				System.out.println("완료");
			}else {
				System.out.println("실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Orders2 vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orders2 select(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll() {
		String sql ="SELECT * FROM ORDERS2";
		Statement stmt = null;
		// 질의 결과를 담고
		ResultSet rs = null;
		// 질의 결과의 메타정보를 이용 
		ResultSetMetaData rsmd = null;
		// 리턴시킬 자료구조를 생성 
		List<Orders2> list = new ArrayList<>();
		
		try {
			stmt =  conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Orders2 vo = new Orders2();
				vo.setOrder_id(rs.getInt("ORDER_ID"));
				vo.setCus_name(rs.getString("CUS_NAME"));
				vo.setCus_tel(rs.getString("CUS_TEL"));
				vo.setMovie_name(rs.getString("MOVIE_NAME"));
				vo.setOrder_date(rs.getString("ORDER_DATE"));
				vo.setOrder_time(rs.getString("ORDER_TIME"));
				vo.setOrder_amount(rs.getInt("ORDER_AMOUNT"));
				vo.setOrder_seat(rs.getString("ORDER_SEAT"));
				vo.setMovie_price(rs.getString("MOVIE_PRICE"));
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
