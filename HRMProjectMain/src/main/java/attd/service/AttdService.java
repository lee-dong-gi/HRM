package attd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import attd.model.AttdDto;

@Service
public interface AttdService {

	// 근태 출근
	void insertAttd(AttdDto attdDto) throws Exception;
	
	// 지각률
	int countLate(String name) throws Exception;

	// 근태 조회
	List<AttdDto> selAttd(String name) throws Exception;

	// 부서명
	String getDname(int deptno) throws Exception;

	// 근태 퇴근
	int uptOff(int attd_no) throws Exception;

}