package contact.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class CommonException {
	//예외발생 
	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("contact/common_error");
		return mav;
	}
}
