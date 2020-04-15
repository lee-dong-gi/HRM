package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import user.domain.NoticeDto;
import user.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService service;
	
	// 공지사항 목록
	@RequestMapping("notice/list")
	public String getAll(Model model) throws Exception {
		List<NoticeDto> list = service.getAll();
		int count = service.count();
		model.addAttribute("all", list);
		model.addAttribute("count", count);
		return "notice/list";
	}
	
	// 공지사항 내용
	@RequestMapping("notice/notice")
	public String getNotice(Model model, int num) throws Exception {
		NoticeDto dto = service.getBoard(num);
		model.addAttribute("notice", dto);
		return "notice/notice";
	}
	
	// 등록 폼 부르기
	@RequestMapping(value = "notice/insert", method = RequestMethod.GET)
	public String insertForm() throws Exception {
		return "notice/insertForm";
	}
	
	// 등록하기
	@RequestMapping(value = "notice/insert", method = RequestMethod.POST)
	public String insert(Model model, NoticeDto dto) throws Exception {
		service.insertBoard(dto);
		return "redirect:list";
	}
	
	// 수정 폼 부르기
	@RequestMapping(value = "notice/update", method = RequestMethod.GET)
	public String updateForm(Model model, int num) throws Exception {
		model.addAttribute("notice", service.getBoard(num));
		return "notice/updateForm";
	}
	
	// 수정하기
	@RequestMapping(value = "notice/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(NoticeDto dto) throws Exception {
		String s = "no";
		int update = service.updateBoard(dto);
		if (update != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}
	
	// 삭제하기
	@RequestMapping("notice/delete")
	@ResponseBody
	public String delete(int num) throws Exception {
		String s = "no";
		int delete = service.deleteBoard(num);
		if (delete != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		System.out.println(gson.toJson(s));
		return gson.toJson(s);
	}
	
}
