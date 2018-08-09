package movie.vo;

public class MovieList {
	private String movie_name;
	private String genre_name;
	private String grade_name;
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	@Override
	public String toString() {
		return movie_name + "\t" + genre_name + "\t" + grade_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre_name == null) ? 0 : genre_name.hashCode());
		result = prime * result + ((grade_name == null) ? 0 : grade_name.hashCode());
		result = prime * result + ((movie_name == null) ? 0 : movie_name.hashCode());
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
		MovieList other = (MovieList) obj;
		if (genre_name == null) {
			if (other.genre_name != null)
				return false;
		} else if (!genre_name.equals(other.genre_name))
			return false;
		if (grade_name == null) {
			if (other.grade_name != null)
				return false;
		} else if (!grade_name.equals(other.grade_name))
			return false;
		if (movie_name == null) {
			if (other.movie_name != null)
				return false;
		} else if (!movie_name.equals(other.movie_name))
			return false;
		return true;
	}
	
}
