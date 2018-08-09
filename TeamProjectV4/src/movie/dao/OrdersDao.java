package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import movie.common.ConnFactory;
import movie.vo.Orders;

public class OrdersDao implements IDao<Orders, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");

	@Override
	public void insert(Orders vo) {
		String sql = "INSERT INTO ORDERS "
				+ "VALUES(SEQ_ORDERS.NEXTVAL,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,vo.getCus_id());
			pstmt.setString(2,vo.getCus_tel());
			pstmt.setInt(3,vo.getMovie_id());
			pstmt.setString(4,vo.getOrder_date());
			pstmt.setString(5,vo.getOrder_time());
			pstmt.setInt(6,vo.getOrder_amount());
			pstmt.setString(7,vo.getOrder_seat());
			
			int res=pstmt.executeUpdate();
			if(res>0) {
				System.out.println("예매정보를 잘 입력했습니다.");
			}else {
				System.out.println("예매정보를 입력하지 못했어요.");
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
	public void update(Orders vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orders select(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectt(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
