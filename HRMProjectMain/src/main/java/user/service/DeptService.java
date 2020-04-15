package user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.DeptDTO;
import user.domain.NoticeDto;
import user.persistence.DeptDao;

@Service
public class DeptService {
	@Autowired
	private DeptDao deptDao;
	
		// 부서전체 목록
		public List<DeptDTO> seldeptAll() throws Exception {
			return deptDao.seldeptall();
		}
		public DeptDTO seldeptone(int deptno) throws Exception {
			return deptDao.seldeptone(deptno);
		}
		public int seldeptAllco() throws Exception {
			return deptDao.seldeptallco();
		}
		
		public List<DeptDTO> seldeptno(Map map) throws Exception {
			return deptDao.seldeptno(map);
		}
		public int seldeptnoco(Map map) throws Exception {
			return deptDao.seldeptnoco(map);
		}
		
		public List<DeptDTO> seldeptname(Map map) throws Exception {
			return deptDao.seldeptname(map);
		}
		public int seldeptnameco(Map map) throws Exception {
			return deptDao.seldeptnameco(map);
		}
		
		public List<DeptDTO> seldeptloc(Map map) throws Exception {
			return deptDao.seldeptloc(map);
		}
		public int seldeptlocco(Map map) throws Exception {
			return deptDao.seldeptlocco(map);
		}
		
		public int insertdept(DeptDTO dd) throws Exception {
			return deptDao.insertdept(dd);
		}
		public int deptupdate(DeptDTO dd) throws Exception {
			return deptDao.deptupdate(dd);
		}
		
		public int deletedept(int deptno) throws Exception {
			return deptDao.deletedept(deptno);
		}
}
