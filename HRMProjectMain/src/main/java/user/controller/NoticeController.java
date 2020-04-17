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
	/*
	 * now : 현재 페이지 / count : 전체 글의 갯수 / pageCount : 전체 페이지 갯수 /
	 * offset : 몇번째 글번호부터 출력될지(0부터) / maxNum : 최상위 글번호
	 */
	@RequestMapping("notice/list")
	public String getAll(Model model, int now) throws Exception {
		// 총 페이지 갯수
		int count = service.count();
		int pageCount;
		if(count % 10 == 0) {
			pageCount = count/10;
		}else {
			pageCount = count / 10 +1;
		}
		model.addAttribute("alll",pageCount);
		
		int offset = (now - 1) * 10;
		int maxNum = service.count() - (now - 1)*10;
		
		List<NoticeDto> list = service.getAll(offset);
		
		model.addAttribute("all", list);
		model.addAttribute("count", maxNum);
		
		int startPage;
		int endPage;
		if (now < 3) {
			startPage = 1;
			endPage = 5;
		}else {
			startPage = now - 2;
			endPage = now + 2;
		}
		if(pageCount == now) {
			endPage = now;
		}
		if ((pageCount-1) == (now)) {
			endPage = now + 1;
		}
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "notice/list";
	}
	
	// 검색
	@RequestMapping("notice/search")
	public String searchForm(Model model, String s) throws Exception {
		List<NoticeDto> search = service.search(s);
		int scount = service.searchCount(s);
		model.addAttribute("ser",search);
		model.addAttribute("scount",scount);
		return "notice/search";
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
	
	// 댓글 삭제하기
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
