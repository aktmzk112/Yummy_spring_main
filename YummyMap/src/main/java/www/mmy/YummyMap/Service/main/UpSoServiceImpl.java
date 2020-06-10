package www.mmy.YummyMap.Service.main;


import org.springframework.stereotype.Service;

import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.vo.UpSoVO;

@Service
public class UpSoServiceImpl implements UpSoService {
	private MainDAO mainDao;

	public UpSoServiceImpl(MainDAO mainDao) {
		this.mainDao = mainDao;
	}
	@Override
	public UpSoVO getUpSoDetailInfo(String upso_id) {
		UpSoVO upSoVo = mainDao.getUpSoDetailInfo(upso_id);
		return upSoVo;
	}

	@Override
	public boolean isShowUpSo(String upso_id) {
		int resultCnt = mainDao.isShowUpSo(upso_id);
		boolean bool = resultCnt > 0 ? true : false;
		return bool;
	}

	@Override
	public int insertUpSo(UpSoVO upSoVo) {
		int resultCnt = mainDao.insertUpSo(upSoVo);
		return resultCnt;
	}

}
