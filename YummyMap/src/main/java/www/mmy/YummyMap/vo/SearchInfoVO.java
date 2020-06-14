package www.mmy.YummyMap.vo;
/**
 * 유저가 검색을 요청할때 요구되는 데이터들을 관리하는 클래스입니다.
 * @author	김종형
 *
 */
public class SearchInfoVO {
	double x; // 검색 요청자의 경도값
	double y; // 검색 요청자의 위도값
	String keyword; // 현재 검색어
	String query_location; // 검색어에 장소키워드가 포함되었는지 여부
	String query_keyword; // 검색어에 카테고리 혹은 음식 키워드가 포함되었는지 여부
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
	public String getQuery_location() {
		return query_location;
	}
	public void setQuery_location(String query_location) {
		this.query_location = query_location;
	}
	public String getQuery_keyword() {
		return query_keyword;
	}
	public void setQuery_keyword(String query_keyword) {
		this.query_keyword = query_keyword;
	}
	@Override
	public String toString() {
		return "SearchInfoVO [x=" + x + ", y=" + y + ", keyword=" + keyword + ", query_location=" + query_location
				+ ", query_keyword=" + query_keyword + "]";
	}

}
