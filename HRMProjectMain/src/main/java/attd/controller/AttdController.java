package attd.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import attd.model.AttdDto;
import attd.service.AttdService;

import user.domain.UserVO;

@Controller
@RequestMapping("/attd")
public class AttdController implements ApplicationContextAware {
	@Autowired
	private AttdService attdService;

	// 엑셀 다운용 리스트
	private List<AttdDto> excelList;

	// 근태 메인
	// 메인에서 attd.do요청을 받음
	@RequestMapping("attd.do")
	public String list(HttpSession hs, Model m) throws Exception {
		UserVO uv = (UserVO) hs.getAttribute("login");
		m.addAttribute("empno", uv.getEmpno());
		m.addAttribute("name", uv.getName());
		return "attd/main";
	}

	// 근태 목록
	@RequestMapping(value = "/attdlist", method = RequestMethod.POST)
	@ResponseBody
	public String attdlist(HttpSession hs) throws Exception {
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		List<AttdDto> list = attdService.selAttd(name);
		excelList = list;
		Gson json = new Gson();
		return json.toJson(list);
	}

	// 근태 조회
	@RequestMapping("/list")
	public String listAll() throws Exception {
		return "attd/list";
	}

	// 기간 조회
	@RequestMapping(value = "/searchDate", method = RequestMethod.POST)
	@ResponseBody
	public String searchDate(HttpSession hs, String startDate, String endDate, Model m) throws Exception {
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		List<AttdDto> list = attdService.selAttd(name);
		// 기간값 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 대시(-)로 문자열 잘라서 배열에 넣기
		String[] array = startDate.split("-");
		String[] array2 = endDate.split("-");
		// 문자열 합치기
		String s1 = array[0].concat(array[1]).concat(array[2]);
		String s2 = array2[0].concat(array2[1]).concat(array2[2]);
		// 기간 조건 설정
		int i1 = Integer.parseInt(s1);
		int i2 = Integer.parseInt(s2);
		List<AttdDto> searchList = new ArrayList<AttdDto>();
		for (int i = 0; i < list.size(); i++) {
			String s3 = sdf.format(list.get(i).getAttd_time());
			int i3 = Integer.parseInt(s3);
			if (i1 <= i3 && i3 <= i2) {
				searchList.add(list.get(i));
			}
		}
		excelList = searchList;
		Gson json = new Gson();
		return json.toJson(searchList);
	}

	// 근태 출근 & 지각
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpSession hs, Model m) throws Exception {
		List<AttdDto> list=null;
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		list = attdService.selAttd(name);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date); // 오늘 날짜 스트링으로

		if (list.isEmpty()) {
			AttdDto attdDto = new AttdDto();
			attdDto.setEmpno(uv.getEmpno());
			attdDto.setDname(attdService.getDname(uv.getDeptno()));
			attdDto.setName(uv.getName());
			attdDto.setAttd_time(new Date());
			attdDto.setOff_time(new Date());

			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			long time1 = attdDto.getAttd_time().getTime();
			long time2 = 0;
			String str = "09:00:00";
			try {
				time2 = dateFormat.parse(str).getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String rs = "";
			if (time1 > time2) {
				rs = "지각";
			} else {
				rs = "";
			}
			attdDto.setEmp_late(rs);
			attdService.insertAttd(attdDto); // 디비 저장
			return "attd/insert";

		} else {

			for (int i = 0; i < list.size(); i++) {
				Date attdTime = list.get(i).getAttd_time(); // 리스트에서 출근 시간들을 뽑아서
				String attdDate = sdf.format(attdTime); // 그 출근 시간들에서 출근날짜만 저장

				if (today.equals(attdDate)) { // 오늘 날짜와 같은 값이 출근 날짜에 있으면 msg.jsp로
					return "attd/msg";
				} else { // 아니면 출근 입력
					AttdDto attdDto = new AttdDto();
					attdDto.setEmpno(uv.getEmpno());
					attdDto.setDname(attdService.getDname(uv.getDeptno()));
					attdDto.setName(uv.getName());
					attdDto.setAttd_time(new Date());
					attdDto.setOff_time(new Date());

					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					long time1 = attdDto.getAttd_time().getTime();
					long time2 = 0;
					String str = "09:00:00";
					try {
						time2 = dateFormat.parse(str).getTime();
					} catch (Exception e) {
						e.printStackTrace();
					}
					String rs = "";
					if (time1 > time2) {
						rs = "지각";
					} else {
						rs = "";
					}
					attdDto.setEmp_late(rs);
					attdService.insertAttd(attdDto); // 디비 저장
					return "attd/insert";
				}
			}
		}
		return "attd/main";
	}

	// 지각률
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	@ResponseBody
	public String lateRate(HttpSession hs, Model m) throws Exception {
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		List<AttdDto> list = attdService.selAttd(name);
		Gson json = new Gson();
		if (list.isEmpty()) {
			return json.toJson(0);
		} else {
			double lateRate = ((double) attdService.countLate(name) / list.size()) * 100;
			m.addAttribute("lateRate", lateRate);
			return json.toJson(lateRate);
		}
	}

	// 근태 퇴근
	@RequestMapping("/off") /* off요청을 받음 */
	public String off(HttpSession hs, ModelAndView mav) throws Exception {
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		List<AttdDto> list = attdService.selAttd(name);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date); // 오늘 날짜 스트링으로
		List<AttdDto> todayList = new ArrayList<AttdDto>(); // 출근 날짜가 오늘 날짜인 레코드들 저장할 리스트
		for (int i = 0; i < list.size(); i++) {
			Date attdTime = list.get(i).getAttd_time(); // 리스트에서 출근 시간들을 뽑아서
			String attdDate = sdf.format(attdTime); // 그 출근 시간들에서 출근날짜만 저장
			int attdDateNum = Integer.parseInt(attdDate); // 그 값을 int로
			int todayDateNum = Integer.parseInt(today); // 오늘 날짜도 int로
			if (attdDateNum == todayDateNum) {
				todayList.add(list.get(i)); // 출근날짜가 오늘 날짜와 같은 레코드들만 todayList에 저장
			}
		}
		int offTime_attdNo = todayList.get(0).getAttd_no();
		attdService.uptOff(offTime_attdNo); // 가장 마지막 출근 시간이 찍힌 레코드의 퇴근 시간 입력
		return "attd/off"; /* attd/off.jsp를 실행 */
	}

	// 엑셀 다운
	@RequestMapping("/excelDown")
	public void excelDown(HttpServletResponse response, HttpSession hs) throws Exception {

		// 목록조회
		UserVO uv = (UserVO) hs.getAttribute("login");
		String name = uv.getName();
		List<AttdDto> list = excelList;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// 워크북 생성
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("근태");
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(name);
		int rowNo = 0;

		// 테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
		// 경계선
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		// 헤더 배경색
		headStyle.setFillForegroundColor(HSSFColorPredefined.LAVENDER.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 라인 정렬
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cell.setCellStyle(headStyle);

		// 테이블 데이터용 스타일
		CellStyle bodyStyle = wb.createCellStyle();
		// 라인 정렬
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);
		bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 데이터용 경계 스타일 테두리
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);

		// 헤더 생성
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("사번");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("부서명");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("사원명");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("출근 시간");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("퇴근 시간");
		cell = row.createCell(6);
		cell.setCellStyle(headStyle);
		cell.setCellValue("지각");

		// 데이터 부분 생성
		for (AttdDto attdDto : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(attdDto.getAttd_no());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(attdDto.getEmpno());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(attdDto.getDname());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(attdDto.getName());

			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(sdf.format(attdDto.getAttd_time()));

			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			if (sdf.format(attdDto.getAttd_time()).equals(sdf.format(attdDto.getOff_time()))) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(sdf.format(attdDto.getOff_time()));
			}

			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(attdDto.getEmp_late());
			
			// 셀 너비 자동 조절
			for (int i = 0; i < 7; i++) {
				sheet.autoSizeColumn(i);
				sheet.setColumnWidth(i, (sheet.getColumnWidth(i)) + 1000);
			}			
		}

		// 셀 자동 줄바꿈
		headStyle.setWrapText(true);
		bodyStyle.setWrapText(true);

		// 컨텐츠 타입과 파일명 지정
		response.setContentType("application/vnd.ms-excel"); // response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "HRMAttd.xls");

		// 엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	}

	public void setAttdService(AttdService attdService) {
		this.attdService = attdService;
	}
}