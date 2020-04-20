package user.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import user.domain.NoticeDto;
import user.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	NoticeService service;

	// 공지사항 목록
	/*
	 * now : 현재 페이지 / count : 전체 글의 갯수 / pageCount : 전체 페이지 갯수 / offset : 몇번째 글번호부터
	 * 출력될지(0부터) / maxNum : 최상위 글번호
	 */
	@RequestMapping("notice/list")
	public String getAll(Model model, int now) throws Exception {
		// 총 페이지 갯수
		int count = service.count();
		int pageCount;
		if (count % 10 == 0) {
			pageCount = count / 10;
		} else {
			pageCount = count / 10 + 1;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("now", now);
		int offset = (now - 1) * 10;
		int maxNum = service.count() - (now - 1) * 10;

		List<NoticeDto> list = service.getAll(offset);

		model.addAttribute("list", list);
		model.addAttribute("maxNum", maxNum);

		return "notice/list";
	}

	// 검색
	@RequestMapping("notice/search")
	public String searchForm(Model model, String s, int sNow) throws Exception {
		int sCount = service.searchCount(s);
		int pageCount;
		if (sCount % 10 == 0) {
			pageCount = sCount / 10;
		} else {
			pageCount = sCount / 10 + 1;
		}
		int offset = (sNow - 1) * 10;
		int maxNum = service.searchCount(s) - (sNow - 1) * 10;
		List<NoticeDto> search = service.search(s, offset);
		model.addAttribute("s", s);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("sNow", sNow);
		model.addAttribute("ser", search);
		model.addAttribute("sCount", sCount);
		model.addAttribute("maxNum", maxNum);
		return "notice/search";
	}

	// 공지사항 내용
	@RequestMapping("notice/notice")
	public String getNotice(Model model, int num) throws Exception {
		NoticeDto dto = service.getBoard(num);
		model.addAttribute("notice", dto);
		return "notice/notice";
	}
	
	@RequestMapping("notice/file")
	public ModelAndView download(int num, Model model) throws Exception {
		 File downloadFile = getFile(num, model);
		 return new ModelAndView("noticedownload", "downloadFile", downloadFile);
	}
	
	private File getFile(int num, Model model) throws Exception {
		model.addAttribute("realfilename", service.getBoard(num).getRealfilename());
		return new File(service.getBoard(num).getPathname());
	}

	// 등록 폼 부르기
	@RequestMapping(value = "notice/insert", method = RequestMethod.GET)
	public String insertForm() throws Exception {
		return "notice/insertForm";
	}

	// 등록하기
	@RequestMapping(value = "notice/insert", method = RequestMethod.POST)
	public String insert(Model model, NoticeDto dto, @Nullable MultipartFile nFile) throws Exception {
		
		if (nFile.getSize() != 0) {
			String fileName = nFile.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid.toString() + extension;
			File file = new File("D://uploads/" + newFileName);
			nFile.transferTo(file);
			dto.setPathname("D://uploads/" + newFileName);
			dto.setRealfilename(nFile.getOriginalFilename());
		} else {
			dto.setRealfilename("");
			dto.setPathname("");
		}
		System.out.println(dto.getPathname());
		service.insertBoard(dto);
		return "redirect:list?now=1";
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
		return gson.toJson(s);
	}

}
