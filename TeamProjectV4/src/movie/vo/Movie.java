package movie.vo;

public class Movie {
	private int movie_id;
	private String movie_name;
	private int genre_id;
	private int grade_id;
	private int movie_runtime;
	private String movie_openday;
	private int movie_price;
	private String movie_poster;
	private String movie_remark;
	
	
	
	public int getMovie_id() {
		return movie_id;
	}



	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}



	public String getMovie_name() {
		return movie_name;
	}



	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}



	public int getGenre_id() {
		return genre_id;
	}



	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}



	public int getGrade_id() {
		return grade_id;
	}



	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}



	public int getMovie_runtime() {
		return movie_runtime;
	}



	public void setMovie_runtime(int movie_runtime) {
		this.movie_runtime = movie_runtime;
	}



	public String getMovie_openday() {
		return movie_openday;
	}



	public void setMovie_openday(String movie_openday) {
		this.movie_openday = movie_openday;
	}



	public int getMovie_price() {
		return movie_price;
	}



	public void setMovie_price(int movie_price) {
		this.movie_price = movie_price;
	}



	public String getMovie_poster() {
		return movie_poster;
	}



	public void setMovie_poster(String movie_poster) {
		this.movie_poster = movie_poster;
	}



	public String getMovie_remark() {
		return movie_remark;
	}



	public void setMovie_remark(String movie_remark) {
		this.movie_remark = movie_remark;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + genre_id;
		result = prime * result + grade_id;
		result = prime * result + movie_id;
		result = prime * result + ((movie_name == null) ? 0 : movie_name.hashCode());
		result = prime * result + ((movie_openday == null) ? 0 : movie_openday.hashCode());
		result = prime * result + ((movie_poster == null) ? 0 : movie_poster.hashCode());
		result = prime * result + movie_price;
		result = prime * result + ((movie_remark == null) ? 0 : movie_remark.hashCode());
		result = prime * result + movie_runtime;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre_id != other.genre_id)
			return false;
		if (grade_id != other.grade_id)
			return false;
		if (movie_id != other.movie_id)
			return false;
		if (movie_name == null) {
			if (other.movie_name != null)
				return false;
		} else if (!movie_name.equals(other.movie_name))
			return false;
		if (movie_openday == null) {
			if (other.movie_openday != null)
				return false;
		} else if (!movie_openday.equals(other.movie_openday))
			return false;
		if (movie_poster == null) {
			if (other.movie_poster != null)
				return false;
		} else if (!movie_poster.equals(other.movie_poster))
			return false;
		if (movie_price != other.movie_price)
			return false;
		if (movie_remark == null) {
			if (other.movie_remark != null)
				return false;
		} else if (!movie_remark.equals(other.movie_remark))
			return false;
		if (movie_runtime != other.movie_runtime)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return movie_name;
	}
	
	
}
