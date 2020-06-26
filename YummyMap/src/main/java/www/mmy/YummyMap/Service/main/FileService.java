package www.mmy.YummyMap.Service.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import www.mmy.YummyMap.dao.MainDAO;
import www.mmy.YummyMap.util.FileUtil;
import www.mmy.YummyMap.vo.ImageFileVO;

@Service
public class FileService {
	
	private MainDAO mainDao;
	
	private final Logger logger = LoggerFactory.getLogger(FileService.class);
	
	public FileService(MainDAO mainDao) {
		this.mainDao = mainDao;
	}
	
	public List<ImageFileVO> uploadProcess(HttpSession session, MultipartFile[] files) {
		List<ImageFileVO> imageFileVoList = new ArrayList<ImageFileVO>();
		String path = session.getServletContext().getRealPath("resources");
		path = path + "/reviewImg";
		for(int i=0; i<files.length - 1; i++) {
			ImageFileVO imageFileVo = new ImageFileVO();
			String original_name = files[i].getOriginalFilename();
			if(original_name == null || original_name.length() == 0) {
				continue;
			}
			String save_name = FileUtil.rename(path, original_name);
			try {
				File saveFile = new File(path, save_name);
				files[i].transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			imageFileVo.setOriginal_name(original_name);
			imageFileVo.setSave_name(save_name);
			imageFileVoList.add(imageFileVo);
		}
		return imageFileVoList;
	}
	
	public boolean insertReviewImgFile(ImageFileVO imageFileVo) {
		boolean result = false;
		if(imageFileVo == null)
			return false;
		try {
			int rev_no = imageFileVo.getRev_no();
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			logger.info("imageFile Insert 작업도중 예외 발생 {}", exceptionMessage);
			return false;
		}
		int upso_id = imageFileVo.getRes_no();
		String is_main = isFirstImg(upso_id);
		imageFileVo.setIs_main(is_main);
		int insertCount = mainDao.insertReviewImg(imageFileVo);
		if(insertCount == 0)
			result = false;
		else
			result = true;
		
		return result;
	}
	
	public String isFirstImg(int upso_id) {
		int count = mainDao.getCountImgGroupByUpso(upso_id);
		String result = (count == 0) ? "Y" : "N";
		return result;
	}
}
