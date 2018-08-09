package movie.vo;

public class Grade {
	private int grade_id;
	private String grade_name;
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	@Override
	public String toString() {
		return grade_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grade_id;
		result = prime * result + ((grade_name == null) ? 0 : grade_name.hashCode());
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
		Grade other = (Grade) obj;
		if (grade_id != other.grade_id)
			return false;
		if (grade_name == null) {
			if (other.grade_name != null)
				return false;
		} else if (!grade_name.equals(other.grade_name))
			return false;
		return true;
	}
	
	
}
