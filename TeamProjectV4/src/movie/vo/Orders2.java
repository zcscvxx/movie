package movie.vo;

public class Orders2 {
	private int order_id;
	private String cus_name;
	private String cus_tel;
	private String movie_name;
	private String order_date;
	private String order_time;
	private int order_amount;
	private String order_seat;
	private String movie_price;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(String cus_tel) {
		this.cus_tel = cus_tel;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public int getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}
	public String getOrder_seat() {
		return order_seat;
	}
	public void setOrder_seat(String order_seat) {
		this.order_seat = order_seat;
	}
	public String getMovie_price() {
		return movie_price;
	}
	public void setMovie_price(String movie_price) {
		this.movie_price = movie_price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cus_name == null) ? 0 : cus_name.hashCode());
		result = prime * result + ((cus_tel == null) ? 0 : cus_tel.hashCode());
		result = prime * result + ((movie_name == null) ? 0 : movie_name.hashCode());
		result = prime * result + ((movie_price == null) ? 0 : movie_price.hashCode());
		result = prime * result + order_amount;
		result = prime * result + ((order_date == null) ? 0 : order_date.hashCode());
		result = prime * result + order_id;
		result = prime * result + ((order_seat == null) ? 0 : order_seat.hashCode());
		result = prime * result + ((order_time == null) ? 0 : order_time.hashCode());
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
		Orders2 other = (Orders2) obj;
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
		if (movie_name == null) {
			if (other.movie_name != null)
				return false;
		} else if (!movie_name.equals(other.movie_name))
			return false;
		if (movie_price == null) {
			if (other.movie_price != null)
				return false;
		} else if (!movie_price.equals(other.movie_price))
			return false;
		if (order_amount != other.order_amount)
			return false;
		if (order_date == null) {
			if (other.order_date != null)
				return false;
		} else if (!order_date.equals(other.order_date))
			return false;
		if (order_id != other.order_id)
			return false;
		if (order_seat == null) {
			if (other.order_seat != null)
				return false;
		} else if (!order_seat.equals(other.order_seat))
			return false;
		if (order_time == null) {
			if (other.order_time != null)
				return false;
		} else if (!order_time.equals(other.order_time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Orders2 [order_id=" + order_id + ", cus_name=" + cus_name + ", cus_tel=" + cus_tel + ", movie_name="
				+ movie_name + ", order_date=" + order_date + ", order_time=" + order_time + ", order_amount="
				+ order_amount + ", order_seat=" + order_seat + ", movie_price=" + movie_price + "]";
	}
	
	
}
