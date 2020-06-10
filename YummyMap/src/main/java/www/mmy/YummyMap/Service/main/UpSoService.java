package www.mmy.YummyMap.Service.main;

import www.mmy.YummyMap.vo.UpSoVO;

public interface UpSoService {
	
	public UpSoVO getUpSoDetailInfo(String upso_id);
	
	public boolean isShowUpSo(String upso_id);
	
	public int insertUpSo(UpSoVO upSoVo);
}
