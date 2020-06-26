package www.mmy.YummyMap.Service.main;

import www.mmy.YummyMap.vo.SearchInfoVO;

public interface KeywordService {
	
	public SearchInfoVO analyzeKeyword(SearchInfoVO searchInfoVo);
	
	public boolean insertKeyword(SearchInfoVO searchInfoVo);
}
