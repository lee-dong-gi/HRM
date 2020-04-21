package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.EmpDto;
import user.persistence.ManageDao;

@Service
public class ManageService {

	@Autowired
	ManageDao dao;
	
	public List<EmpDto> sel() throws Exception {
		return dao.sel();
	}
	
	public EmpDto emp(String id) throws Exception {
		return dao.emp(id);
	}
	
	public int upd(String id, String level, int salary, int deptno) {
		return dao.upd(id, level, salary, deptno);
	}
	
	public int del(String id) {
		return dao.del(id);
	}
	
}
