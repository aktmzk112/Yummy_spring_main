package www.mmy.YummyMap.vo;
/**
 * 유저가 검색을 요청할때 요구되는 데이터들을 관리하는 클래스입니다.
 * @author	김종형
 *
 */
public class SearchInfoVO {
	private int keyword_id;
	private int keywordCountInTable;
	private double x; // 검색 요청자의 경도값
	private double y; // 검색 요청자의 위도값
	private String upso_id;
	private String keyword; // 현재 검색어
	private String query_location; // 검색어에 장소키워드가 포함되었는지 여부
	private String query_keyword; // 검색어에 카테고리 혹은 음식 키워드가 포함되었는지 여부
	private String category_name;
	public int getKeyword_id() {
		return keyword_id;
	}
	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
	}
	public int getKeywordCountInTable() {
		return keywordCountInTable;
	}
	public void setKeywordCountInTable(int keywordCountInTable) {
		this.keywordCountInTable = keywordCountInTable;
	}
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
	public String getUpso_id() {
		return upso_id;
	}
	public void setUpso_id(String upso_id) {
		this.upso_id = upso_id;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	@Override
	public String toString() {
		return "SearchInfoVO [keyword_id=" + keyword_id + ", keywordCountInTable=" + keywordCountInTable + ", x=" + x
				+ ", y=" + y + ", upso_id=" + upso_id + ", keyword=" + keyword + ", query_location=" + query_location
				+ ", query_keyword=" + query_keyword + ", category_name=" + category_name + "]";
	}
}
