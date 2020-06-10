package www.mmy.YummyMap.vo;

public class UpSoVO {
	private double x, y;
	private String id, place_name, category_name, phone, road_address_name, place_url;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoad_address_name() {
		return road_address_name;
	}
	public void setRoad_address_name(String road_address_name) {
		this.road_address_name = road_address_name;
	}
	public String getPlace_url() {
		return place_url;
	}
	public void setPlace_url(String place_url) {
		this.place_url = place_url;
	}
	@Override
	public String toString() {
		return "UpSoVO [x=" + x + ", y=" + y + ", id=" + id + ", place_name=" + place_name + ", category_name="
				+ category_name + ", phone=" + phone + ", road_address_name=" + road_address_name + ", place_url="
				+ place_url + "]";
	}
		
}
