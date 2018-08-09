package movie.vo;

public class Customer {
	private int cus_id;
	private String cus_name;
	private int cus_age;
	private String cus_gender;
	private String cus_tel;
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public int getCus_age() {
		return cus_age;
	}
	public void setCus_age(int cus_age) {
		this.cus_age = cus_age;
	}
	public String getCus_gender() {
		return cus_gender;
	}
	public void setCus_gender(String cus_gender) {
		this.cus_gender = cus_gender;
	}
	public String getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(String cus_tel) {
		this.cus_tel = cus_tel;
	}
	@Override
	public String toString() {
		return cus_name+"("+cus_age+")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cus_age;
		result = prime * result + ((cus_gender == null) ? 0 : cus_gender.hashCode());
		result = prime * result + cus_id;
		result = prime * result + ((cus_name == null) ? 0 : cus_name.hashCode());
		result = prime * result + ((cus_tel == null) ? 0 : cus_tel.hashCode());
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
		Customer other = (Customer) obj;
		if (cus_age != other.cus_age)
			return false;
		if (cus_gender == null) {
			if (other.cus_gender != null)
				return false;
		} else if (!cus_gender.equals(other.cus_gender))
			return false;
		if (cus_id != other.cus_id)
			return false;
		if (cus_name == null) {
			if (other.cus_name != null)
				return false;
		} else if (!cus_name.equals(other.cus_name))
			return false;
		if (cus_tel == null) {
			if (other.cus_tel != null)
				return false;
		} else if (!cus_tel.equals(other.cus_tel))
			return false;
		return true;
	}

	
}
