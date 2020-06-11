package www.mmy.YummyMap.vo;

public class SearchInfoVO {
	double x; // 검색 요청자의 경도값
	double y; // 검색 요청자의 위도값
	String keyword; // 현재 검색어
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "SearchInfoVO [경도x=" + x + ", 위도y=" + y + ", keyword=" + keyword + "]";
	}
	
}
