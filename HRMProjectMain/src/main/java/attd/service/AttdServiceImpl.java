package attd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import attd.dao.AttdDao;
import attd.model.AttdDto;

@Service
public class AttdServiceImpl implements AttdService {

	@Autowired
	private AttdDao attdDao;

	// 근태 출근
	@Override
	public void insertAttd(AttdDto attdDto) throws Exception {
		attdDao.insertAttd(attdDto);
	}
	
	// 지각률
	@Override
	public int countLate(String name) throws Exception {
		return attdDao.countLate(name);
	}

	// 근태 조회
	@Override
	public List<AttdDto> selAttd(String name) throws Exception {
		return attdDao.selAttd(name);
	}

	// 부서명
	@Override
	public String getDname(int deptno) throws Exception {
		return attdDao.getDname(deptno);
	}

	// 근태 퇴근
	@Override
	public int uptOff(int attd_no) throws Exception {
		return attdDao.uptOff(attd_no);
	}
}