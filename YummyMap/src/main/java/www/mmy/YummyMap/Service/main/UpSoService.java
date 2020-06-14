package www.mmy.YummyMap.Service.main;
/**
 * 업소 서비스를 위해 필요한 최소 기능이 담긴 인터페이스입니다.
 * @author	김종형
 */
import www.mmy.YummyMap.vo.UpSoVO;

public interface UpSoService {
	
	public UpSoVO getUpSoDetailInfo(String upso_id);
	
	public boolean isShowUpSo(String upso_id);
	
	public int insertUpSo(UpSoVO upSoVo);
	
	public int insertKeyword(UpSoVO upSoVo);
}
