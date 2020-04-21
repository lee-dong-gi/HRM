package contact.controller;
import java.io.File;
import java.io.OutputStream;
//연락처 ~ 
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import contact.domain.ContactDto;
import contact.domain.GroupDto;
import contact.service.ContactService;
import contact.service.ExcelService;
import contact.service.ExcelServiceImpl;
import contact.service.GroupService;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	ContactService service;
	
	@Autowired
	GroupService gservice;
	
	@Autowired
	ExcelService excelServive;
	
	//연락처 목록 
	@RequestMapping("/list.do")
	public ModelAndView list() throws Exception{
		List<ContactDto> list = service.list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact/list");
		mav.addObject("list", list);
		return mav;
	}
	
	//연락처 뷰 페이지 
	@RequestMapping(value="contact/view.do", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int num, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact/view");
		mav.addObject("dto", service.read(num));
		return mav;
	}
	
	//연락처 등록 페이지로 이동  
	@RequestMapping(value="contact/insert.do",method = RequestMethod.GET)
	public String write() {
		return "contact/insert";
	}

	//연락처 등록 처리
	@RequestMapping(value="contact/contact/insert.do", method = RequestMethod.POST)
	public String writeContact(@ModelAttribute ContactDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/contact/list.do";
	}


	//연락처 수정 페이지 이동 
	@RequestMapping(value="contact/contact/update.do", method = RequestMethod.GET)
	public String update(Model model, int num) {
		ContactDto dto;
		try {
			dto = service.read(num);
			model.addAttribute("dto", dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "contact/update";
	}

	//연락처 수정
	@RequestMapping(value="contact/contact/update.do", method = RequestMethod.POST)
	public String updateContact(@RequestParam int num, ContactDto dto, Model model) throws Exception {
		service.update(dto);
		model.addAttribute("dto", service.read(num));
		return "redirect:/contact/list.do";
	}

	//연락처 삭제
	@RequestMapping(value="contact/contact/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam int num) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/contact/list.do");
		service.delete(num);
		return mav;
	}
	//////////////////////////////////////////////////////
	//그룹관리 
	@RequestMapping(value = "contact/glist.do", method = RequestMethod.GET)
	public ModelAndView glist() throws Exception{
		List<GroupDto> glist = gservice.glist();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact/glist");
		mav.addObject("glist", glist);
		return mav;
	}
	
	//그룹 보기 
	@RequestMapping(value="contact/contact/gview.do", method = RequestMethod.GET)
	public ModelAndView gview(@RequestParam int gnum, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact/gview");
		mav.addObject("gdto", gservice.gread(gnum));
		return mav;
	}
	
	//그룹 추가 페이지로 이동  
	@RequestMapping(value="contact/contact/gwrite.do", method = RequestMethod.GET)
	public String gwrite() {
		return "contact/gwrite";
	}
		
	//그룹 추가 처리
	@RequestMapping(value="contact/contact/contact/gwrite.do", method = RequestMethod.POST)
	public String writeGroup(@ModelAttribute GroupDto gdto) throws Exception {
		gservice.ginsert(gdto);
		return "redirect:/contact/contact/glist.do";
	}
	
	//그룹 수정 페이지로 이동 
	@RequestMapping(value="contact/contact/contact/gupdate.do", method = RequestMethod.GET)
	public String gupdate(Model model, int gnum) {
		GroupDto gdto;
		try {
			gdto = gservice.gread(gnum);
			model.addAttribute("gdto", gdto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "contact/gupdate";
	}
	
	//그룹 수정 처리 
	@RequestMapping(value="contact/contact/contact/gupdate.do", method = RequestMethod.POST)
	public String gupdate(@ModelAttribute GroupDto gdto) throws Exception {
		gservice.gupdate(gdto);
		return "redirect:/contact/contact/glist.do";
	}
	
	
	//그룹 삭제 처리 
	@RequestMapping(value="contact/contact/contact/gdelete.do", method = RequestMethod.GET)
	public ModelAndView deleteGroup(@RequestParam int gnum) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/contact/contact/glist.do");
		gservice.gdelete(gnum);
		return mav;
	}

	

	
	//그룹 등록 - 선택 ajax
	@RequestMapping(value = "contact/groups.do", method = RequestMethod.POST)
	@ResponseBody
	public void groupList(HttpServletResponse resp) throws Exception{
		List<GroupDto> glist = gservice.glist();
		Gson json = new Gson();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toJson(glist));
	}
	
	@RequestMapping(value = "contact/contact/groups.do", method = RequestMethod.POST)
	@ResponseBody
	public void glistUpdate(HttpServletResponse resp) throws Exception{
		List<GroupDto> glist = gservice.glist();
		Gson json = new Gson();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(json.toJson(glist));
	}
	
	
	//엑셀 다운로드 
	@RequestMapping(value="contact/exceldown.do")
	public void ExcelDown(@RequestParam String fileName, HttpServletResponse response, Model md) throws Exception{
		  HSSFWorkbook objWorkBook = new HSSFWorkbook();
	      HSSFSheet objSheet = null;
	      HSSFRow objRow = null;
	      HSSFCell objCell = null;  
	      
	      HSSFCellStyle styleHd = objWorkBook.createCellStyle();    //제목 스타일
	      HSSFFont font = objWorkBook.createFont();
	      font.setFontHeightInPoints((short) 14);
	      // 글자 굵게 하기
	      font.setBold(true);
	      
	      //헤드 배경색 설정 
	      styleHd.setFillForegroundColor(HSSFColorPredefined.AQUA.getIndex());
	      styleHd.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	      //데이터 스타일  
	      HSSFCellStyle styleBD = objWorkBook.createCellStyle(); 
	      

	      objSheet = objWorkBook.createSheet("첫번째 시트");   
	      
	      objRow = objSheet.createRow(0);
	      objRow.setHeight ((short) 0x150);

	      objCell = objRow.createCell(0);
	      objCell.setCellValue("그룹 ");
	      objCell.setCellStyle(styleHd);

	      objCell = objRow.createCell(1);
	      objCell.setCellValue("이름");
	      objCell.setCellStyle(styleHd);
	      
	      objCell = objRow.createCell(2);
	      objCell.setCellValue("직급");
	      objCell.setCellStyle(styleHd);
	      
	      objCell = objRow.createCell(3);
	      objCell.setCellValue("회사명");
	      objCell.setCellStyle(styleHd);
	      
	      objCell = objRow.createCell(4);
	      objCell.setCellValue("이메일");
	      objCell.setCellStyle(styleHd);
	      
	      objCell = objRow.createCell(5);
	      objCell.setCellValue("전화번호");
	      objCell.setCellStyle(styleHd);
	      
	      List<ContactDto> list = service.list();
	      int index=1;
	      for(ContactDto dto : list) {
	    	  objRow = objSheet.createRow(index);
	          objRow.setHeight((short) 0x150);

	          objCell = objRow.createCell(0);
	          objCell.setCellValue((String)dto.getGname());
	          objCell.setCellStyle(styleBD);

	          objCell = objRow.createCell(1);
	          objCell.setCellValue((String)dto.getName());
	          objCell.setCellStyle(styleBD);

	          objCell = objRow.createCell(2);
	          objCell.setCellValue((String)dto.getLevel());
	          objCell.setCellStyle(styleBD);

	          objCell = objRow.createCell(3);
	          objCell.setCellValue((String)dto.getCompname());
	          objCell.setCellStyle(styleBD);
	          
	          
	          objCell = objRow.createCell(4);
	          objCell.setCellValue((String)dto.getEmail());
	          objCell.setCellStyle(styleBD);
	          
	          
	          objCell = objRow.createCell(5);
	          objCell.setCellValue((String)dto.getPhonenum());
	          objCell.setCellStyle(styleBD);
	          index++;
	          //셀 너비 설정 
	          for (int i=0; i<=12; i++) {
	        	  objSheet.autoSizeColumn(i);
	        	  objSheet.setColumnWidth(i, (objSheet.getColumnWidth(i))+(short)0124);
	          }
	          
	      }
	      
	      
	      response.setContentType("Application/Msexcel");
	      response.setHeader("Content-Disposition",
	    		  "ATTachment; Filename="+URLEncoder.encode(fileName,"UTF-8")
	    		  +".xls");

	      OutputStream fileOut  = response.getOutputStream();
	      objWorkBook.write(fileOut);
	      fileOut.close();

	      response.getOutputStream().flush();
	      response.getOutputStream().close();
	}
	
	
	//엑셀 업로드
	@RequestMapping(value="contact/excelUploadAjax.do", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ModelAndView excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
	
	System.out.println("업로드 진행");
	MultipartFile excelFile = request.getFile("excelFile");
	
	if(excelFile==null || excelFile.isEmpty()) {
		throw new RuntimeException("엑셀 파일을 선택해주세요");
	}
	
	File destFile = new File("D://"+excelFile.getOriginalFilename());
	try {
		excelFile.transferTo(destFile);
	} catch(Exception e) {
		throw new RuntimeException(e.getMessage(), e);
	}
	
	excelServive.excelUpload(destFile);
	
	destFile.delete();
	ModelAndView view = new ModelAndView();
	view.setViewName("contact/list");	
	return view;
	
	}

	
	
	
	
}
