package contact.exception;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
//@ControllerAdvice
public class CommonException extends Exception{
	//예외발생 
//	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(SQLException se) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sqlException", se);
		mav.setViewName("contact/common_error");
		return mav;
	}
}
