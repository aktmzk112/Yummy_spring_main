package www.mmy.YummyMap.Service.main;

import www.mmy.YummyMap.vo.UpSoVO;

public interface UpSoService {
	
	public UpSoVO getUpSoDetailInfo();
	
	public boolean isShowUpSo(UpSoVO upSoVo);
	
	public int insertUpSo(UpSoVO upSoVo);
}
