package movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import movie.common.ConnFactory;
import movie.vo.Grade;
import movie.vo.Movie;

public class MovieDao implements IDao<Movie, Integer> {
	
	private Connection conn = ConnFactory.getConnection("movie.config.oracle");

	@Override
	public void insert(Movie vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movie vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List selectt(Integer primaryKey) {
		String sql ="SELECT * FROM MOVIE "
				+ "WHERE GENRE_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Movie vo = null;
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new Movie();
				vo.setMovie_id(rs.getInt("MOVIE_ID"));
				vo.setMovie_name(rs.getString("MOVIE_NAME"));
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGrade_id(rs.getInt("GRADE_ID"));
				vo.setMovie_runtime(rs.getInt("MOVIE_RUNTIME"));
				vo.setMovie_openday(rs.getString("MOVIE_OPENDAY"));
				vo.setMovie_price(rs.getInt("MOVIE_PRICE"));
				vo.setMovie_poster(rs.getString("MOVIE_POSTER"));
				vo.setMovie_remark(rs.getString("MOVIE_REMARK"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List selectGrade(Integer primaryKey) {
		String sql ="SELECT * FROM MOVIE "
				+ "WHERE GRADE_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Movie vo = null;
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, primaryKey);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new Movie();
				vo.setMovie_id(rs.getInt("MOVIE_ID"));
				vo.setMovie_name(rs.getString("MOVIE_NAME"));
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGrade_id(rs.getInt("GRADE_ID"));
				vo.setMovie_runtime(rs.getInt("MOVIE_RUNTIME"));
				vo.setMovie_openday(rs.getString("MOVIE_OPENDAY"));
				vo.setMovie_price(rs.getInt("MOVIE_PRICE"));
				vo.setMovie_poster(rs.getString("MOVIE_POSTER"));
				vo.setMovie_remark(rs.getString("MOVIE_REMARK"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List selectGG(Integer genreId,Integer gradeId) {
		String sql ="SELECT * FROM MOVIE "
				+ "WHERE GENRE_ID=? AND GRADE_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Movie vo = null;
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genreId);
			pstmt.setInt(2, gradeId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new Movie();
				vo.setMovie_id(rs.getInt("MOVIE_ID"));
				vo.setMovie_name(rs.getString("MOVIE_NAME"));
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGrade_id(rs.getInt("GRADE_ID"));
				vo.setMovie_runtime(rs.getInt("MOVIE_RUNTIME"));
				vo.setMovie_openday(rs.getString("MOVIE_OPENDAY"));
				vo.setMovie_price(rs.getInt("MOVIE_PRICE"));
				vo.setMovie_poster(rs.getString("MOVIE_POSTER"));
				vo.setMovie_remark(rs.getString("MOVIE_REMARK"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List selectAll() {
		String sql ="SELECT * FROM MOVIE";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<Movie> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Movie vo = new Movie();
				vo.setMovie_id(rs.getInt("MOVIE_ID"));
				vo.setMovie_name(rs.getString("MOVIE_NAME"));
				vo.setGenre_id(rs.getInt("GENRE_ID"));
				vo.setGrade_id(rs.getInt("GRADE_ID"));
				vo.setMovie_runtime(rs.getInt("MOVIE_RUNTIME"));
				vo.setMovie_openday(rs.getString("MOVIE_OPENDAY"));
				vo.setMovie_price(rs.getInt("MOVIE_PRICE"));
				vo.setMovie_poster(rs.getString("MOVIE_POSTER"));
				vo.setMovie_remark(rs.getString("MOVIE_REMARK"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public Movie select(Integer primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
