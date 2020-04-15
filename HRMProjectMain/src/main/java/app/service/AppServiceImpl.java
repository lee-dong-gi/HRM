package app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.persistence.AppDAO;
import user.domain.UserVO;
import user.domain.UserVOD;
import app.domain.AppDTO;

@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppDAO appDAO; 
	
	@Override
	public void insertapp(AppDTO appdto) throws Exception {
		appDAO.insertApp(appdto);
	}
	
	@Override
	public void updateapp(AppDTO appdto) throws Exception {
		appDAO.updateApp(appdto);
	}
	
	@Override
	public void deleteapp(int appnum) throws Exception {
		appDAO.deleteApp(appnum);
	}
	
	@Override
	public List<AppDTO> selapp(String userid) throws Exception {
		return appDAO.selApp(userid);
	}

	@Override
	public int appCo(String userid) throws Exception {
		return appDAO.appCount(userid);
	}

	@Override
	public List<AppDTO> appsearchw(Map<String, String> map) throws Exception {
		return appDAO.appSearchw(map);
	}

	@Override
	public List<AppDTO> appsearchs(Map<String, String> map) throws Exception {
		return appDAO.appSearchs(map);
	}

	@Override
	public List<AppDTO> appsearchf(Map<String, String> map) throws Exception {
		return appDAO.appSearchf(map);
	}

	@Override
	public AppDTO appcontent(Map<String, String> map) throws Exception {
		return appDAO.appContent(map);
	}

	@Override
	public int appSearchwco(Map<String, String> map) throws Exception {
		return appDAO.appSearchwco(map);
	}

	@Override
	public int appSearchsco(Map<String, String> map) throws Exception {
		return appDAO.appSearchsco(map);
	}

	@Override
	public int appSearchfco(Map<String, String> map) throws Exception {
		return appDAO.appSearchfco(map);
	}

	@Override
	public List<AppDTO> appboardall(String userid) throws Exception {
		return appDAO.appBoardAll(userid);
	}

	@Override
	public int appcoall(String userid) throws Exception {
		return appDAO.appcountAll(userid);
	}

	@Override
	public AppDTO appcontentm(int appnum) throws Exception {
		return appDAO.appContentm(appnum);
	}

	@Override
	public void okApp(int appnum) throws Exception {
		appDAO.okapp(appnum);
		
	}

	@Override
	public void noApp(int appnum) throws Exception {
		appDAO.noapp(appnum);
	}
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public List<AppDTO> appsearchwadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchwadmin(map);
	}

	@Override
	public List<AppDTO> appsearchsadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchsadmin(map);
	}

	@Override
	public List<AppDTO> appsearchfadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchfadmin(map);
	}

	@Override
	public int appSearchwcoadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchwcoadmin(map);
	}

	@Override
	public int appSearchscoadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchscoadmin(map);
	}

	@Override
	public int appSearchfcoadmin(Map<String, String> map) throws Exception {
		return appDAO.appSearchfcoadmin(map);
	}
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	@Override
	public List<UserVOD> seluserAll(String userid) throws Exception {
		return appDAO.seluserall(userid);
	}

	@Override
	public List<UserVOD> seldeptUp(Map<String, String> map) throws Exception {
		return appDAO.seldeptup(map);
	}

	@Override
	public List<UserVOD> seldeptDown(Map<String, String> map) throws Exception {
		return appDAO.seldeptdown(map);
	}

	@Override
	public List<UserVOD> selnameDown(Map<String, String> map) throws Exception {
		return appDAO.selnamedown(map);
	}

	@Override
	public List<UserVOD> selempnoDown(Map<String, String> map) throws Exception {
		return appDAO.selempnodown(map);
	}

	@Override
	public void Appoint(List<Integer> list) throws Exception {
		appDAO.appoint(list);
	}

	@Override
	public void Revoke(List<Integer> list) throws Exception {
		appDAO.revoke(list);	
	}

	@Override
	public int seluserAllco(String userid) throws Exception {
		return appDAO.seluserallco(userid);
	}

	@Override
	public int seldeptuUpco(Map<String, String> map) throws Exception {
		return appDAO.seldeptupco(map);
	}

	@Override
	public int seldeptDownco(Map<String, String> map) throws Exception {
		return appDAO.seldeptdownco(map);
	}

	@Override
	public int selnameDownco(Map<String, String> map) throws Exception {
		return appDAO.selnamedownco(map);
	}

	@Override
	public int selempnoDownco(Map<String, String> map) throws Exception {
		return appDAO.selempnodownco(map);
	}

	@Override
	public int progressapp(String userid) throws Exception {
		return appDAO.progressApp(userid);
	}
	
	@Override
	public int progressappcoall(String userid) throws Exception {
		return appDAO.progressappcoAll(userid);
	}

}