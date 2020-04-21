package user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import user.domain.CommentDto;
import user.domain.FreeBoardDto;
import user.service.FreeService;

@Controller
public class FreeController {
	@Autowired
	FreeService service;

	// 글목록
	@RequestMapping("free/list")
	public String free(Model model, int now) throws Exception {
		// 총 페이지 갯수
		int count = service.count();
		int pageCount;
		if (count % 10 == 0) {
			pageCount = count / 10;
		} else {
			pageCount = count / 10 + 1;
		}
		int offset = (now - 1) * 10;
		int maxNum = service.count() - (now - 1) * 10;

		List<FreeBoardDto> list = service.all(offset);

		model.addAttribute("all", list);
		model.addAttribute("maxNum", maxNum);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("now", now);

		return "free/list";
	}

	// 검색
	@RequestMapping("free/search")
	public String search(Model model, String s, int sNow, int sel) throws Exception {

		int scount;
		List<FreeBoardDto> search;
		int pageCount;
		int maxNum;
		System.out.println(sel);
		if (sel == 1) {
			scount = service.searchCount(s);
			if (scount % 10 == 0) {
				pageCount = scount / 10;
			} else {
				pageCount = scount / 10 + 1;
			}
			int offset = (sNow - 1) * 10;
			maxNum = service.searchCount(s) - (sNow - 1) * 10;
			search = service.search(s, offset);
		} else {
			scount = service.searchCount2(s);
			if (scount % 10 == 0) {
				pageCount = scount / 10;
			} else {
				pageCount = scount / 10 + 1;
			}
			int offset = (sNow - 1) * 10;
			maxNum = service.searchCount2(s) - (sNow - 1) * 10;
			search = service.search2(s, offset);
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("maxNum", maxNum);
		model.addAttribute("ser", search);
		model.addAttribute("sNow", sNow);
		model.addAttribute("scount", scount);
		return "free/search";
	}

	// 글보기
	@RequestMapping("free/sel")
	public String one(Model model, int num) throws Exception {
		service.hit(num);
		FreeBoardDto dto = service.count(num);
		model.addAttribute("cma", service.comment(num));
		model.addAttribute("one", dto);
		return "free/sel";
	}

	// 글 작성 폼
	@RequestMapping(value = "free/ins", method = RequestMethod.GET)
	public String insForm(FreeBoardDto dto) throws Exception {
		return "free/ins";

	}

	// 글 업데이트 후 리스트
	@RequestMapping(value = "free/ins", method = RequestMethod.POST)
	public String ins(Model model, FreeBoardDto dto) throws Exception {
		FreeBoardDto a = service.ins(dto);
		model.addAttribute("ins", a);
		return "redirect:list?now=1";
	}

	// 글 수정 폼
	@RequestMapping(value = "free/up", method = RequestMethod.GET)
	public String upForm(Model model, int num) throws Exception {
		FreeBoardDto u = service.count(num);
		model.addAttribute("up", u);
		return "free/up";
	}

	@RequestMapping(value = "free/up", method = RequestMethod.POST)
	public String up(Model model, FreeBoardDto dto) throws Exception {
		System.out.println(dto.getNum());
		System.out.println(dto.getContent());
		int d = service.up(dto);
		model.addAttribute("up", d);
		return "redirect:list";

	}

	// 글 삭제
	@RequestMapping("free/delete")
	@ResponseBody
	public String delete(int num) throws Exception {
		String s = "no";
		service.commentDel2(num);
		int delete = service.del(num);
		if (delete != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		System.out.println(gson.toJson(s));
		return gson.toJson(s);
	}

	// 댓글 추가
	@RequestMapping("free/add")
	@ResponseBody
	public String addComment(CommentDto dto) throws Exception {
		service.commentIns(dto);
		List<CommentDto> list = service.comment(dto.getNum());
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}

	// 댓글 삭제
	@RequestMapping("free/cDel")
	@ResponseBody
	public String delComment(CommentDto dto) throws Exception {
		service.commentDel(dto.getCno());
		List<CommentDto> list = service.comment(dto.getNum());
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
		return gson.toJson(list);
	}

}
