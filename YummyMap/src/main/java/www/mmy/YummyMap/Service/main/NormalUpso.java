package www.mmy.YummyMap.Service.main;
/**
 * 업소 서비스를 구현한 클래스입니다.
 * 
 * @author	김종형
 * @see		www.mmy.YummyMap.Service.main.UpSoService
 */

import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.vo.SearchInfoVO;
import www.mmy.YummyMap.vo.UpsoVO;


@Service
public class NormalUpso implements UpSoService {
	private MainDAO mainDao;

	public NormalUpso(MainDAO mainDao) {
		this.mainDao = mainDao;
	}
	@Override
	public UpsoVO getUpSoDetailInfo(String upso_id) {
		UpsoVO upSoVo = mainDao.getUpSoDetailInfo(upso_id);
		return upSoVo;
	}

	@Override
	public boolean isShowUpSo(String upso_id) {
		int resultCnt = mainDao.isShowUpSo(upso_id);
		boolean bool = resultCnt > 0 ? true : false;
		return bool;
	}

	@Override
	public int insertUpSo(UpsoVO upSoVo) {
		int resultCnt = mainDao.insertUpSo(upSoVo);
		return resultCnt;
	}
	@Override
	public int insertKeyword(SearchInfoVO searchInfoVo) {
		int resultCnt = mainDao.insertKeyword(searchInfoVo);
		return resultCnt;
	}
	

}

