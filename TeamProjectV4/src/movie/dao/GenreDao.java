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

public class GenreDao implements IDao<Genre, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");
	
	@Override
	public void insert(Genre vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Genre vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Genre select(Integer primaryKey) {
		String sql ="SELECT * FROM GENRE "
				+ "WHERE GENRE_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Genre vo = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new Genre();
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGenre_name(rs.getString("GENRE_NAME"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List selectAll() {
		String sql ="SELECT * FROM GENRE";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<Genre> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Genre vo = new Genre();
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGenre_name(rs.getString("GENRE_NAME"));
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
