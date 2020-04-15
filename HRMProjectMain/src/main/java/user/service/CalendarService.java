package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.CalendarDto;
import user.persistence.CalendarDao;

@Service
public class CalendarService {

	@Autowired
	private CalendarDao dao;
	
	public void register(CalendarDto dto) throws Exception {
		dao.register(dto);
	}
	
	public List<CalendarDto> check(String now) throws Exception {
		return dao.check(now);
	}
	
	public CalendarDto show(int num) throws Exception {
		return dao.show(num);
	}
	
	public int update(CalendarDto dto) throws Exception {
		return dao.update(dto);
	}
	
	public int delete(int num) throws Exception {
		return dao.delete(num);
	}
}
