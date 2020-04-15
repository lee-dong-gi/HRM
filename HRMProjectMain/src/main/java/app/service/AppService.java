package app.service;

import java.util.List;
import java.util.Map;

import app.domain.AppDTO;
import user.domain.UserVO;
import user.domain.UserVOD;

public interface AppService {
	void insertapp(AppDTO appdto) throws Exception;
	
	void updateapp(AppDTO appdto) throws Exception;
	
	void deleteapp(int appnum) throws Exception;
	
	void okApp(int appnum) throws Exception;
	
	void noApp(int appnum) throws Exception;
	
	List<AppDTO> selapp(String userid) throws Exception;
	
	int progressapp(String userid) throws Exception;
	
	int progressappcoall(String userid) throws Exception;
	
	List<AppDTO> appsearchw(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchs(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchf(Map<String,String> map) throws Exception;
	
	int appCo(String userid) throws Exception;
	
	int appcoall(String userid) throws Exception;
	
	AppDTO appcontent(Map<String,String> map) throws Exception;
	
	AppDTO appcontentm(int appnum) throws Exception;
	
	int appSearchwco(Map<String,String> map) throws Exception;
	
	int appSearchsco(Map<String,String> map) throws Exception;
	
	int appSearchfco(Map<String,String> map) throws Exception;
	
	List<AppDTO> appboardall(String userid) throws Exception;
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	List<AppDTO> appsearchwadmin(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchsadmin(Map<String,String> map) throws Exception;
	
	List<AppDTO> appsearchfadmin(Map<String,String> map) throws Exception;
	
	int appSearchwcoadmin(Map<String,String> map) throws Exception;
	
	int appSearchscoadmin(Map<String,String> map) throws Exception;
	
	int appSearchfcoadmin(Map<String,String> map) throws Exception;
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	List<UserVOD> seluserAll(String userid) throws Exception;
	
	int seluserAllco(String userid) throws Exception;
	
	List<UserVOD> seldeptUp(Map<String,String> map) throws Exception;
	
	int seldeptuUpco(Map<String,String> map) throws Exception;
	
	List<UserVOD> seldeptDown(Map<String,String> map) throws Exception;
	
	int seldeptDownco(Map<String,String> map) throws Exception;
	
	List<UserVOD> selnameDown(Map<String,String> map) throws Exception;
	
	int selnameDownco(Map<String,String> map) throws Exception;
	
	List<UserVOD> selempnoDown(Map<String,String> map) throws Exception;
	
	int selempnoDownco(Map<String,String> map) throws Exception;
	
	void Appoint(List<Integer> list) throws Exception;
	
	void Revoke(List<Integer> list) throws Exception;
}
