package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.common.ConnFactory;
import movie.vo.Genre;
import movie.vo.Grade;

public class GradeDao implements IDao<Grade, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");

	@Override
	public void insert(Grade vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Grade vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grade select(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll() {
		String sql ="SELECT * FROM GRADE";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<Grade> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Grade vo = new Grade();
				vo.setGrade_id(rs.getInt("GRADE_ID"));
				vo.setGrade_name(rs.getString("GRADE_NAME"));
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
