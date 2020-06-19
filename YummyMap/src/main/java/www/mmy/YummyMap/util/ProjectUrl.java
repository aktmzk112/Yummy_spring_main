package www.mmy.YummyMap.util;

/**
 * redirect URL을 상수화하여 관리해주는 열거형클래스이다.
 * @author	김종형
 *
 */
public enum ProjectUrl {
	MAIN_LIST_VIEW ("/YummyMap/main.mmy"),
	UPSO_DETAIL_VIEW("/YummyMap/main/getDetail.mmy"),
	LOGIN_VIEW ("/YummyMap/member/loginView.mmy");
	
	private String url;
	
	private ProjectUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}

