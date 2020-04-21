package attd.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import attd.model.AttdDto;

public class PDFView extends AbstractITextPdfView {
	
	@SuppressWarnings({ "unchecked" })
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// pdfwriter 생성
		PdfWriter.getInstance(document, response.getOutputStream());
		List<AttdDto> fileName = (List<AttdDto>)model.get("fileName");
		// 파일 다운로드 설정
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + "pdftest" + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		document.open(); // 웹페이지에 접근하는 객체를 연다

		BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// pdf가 기본적으로 한글처리가 안되기 때문에 한글폰트 처리를 따로 해주어야 한다.
		// createFont메소드에 사용할 폰트의 경로 (malgun.ttf)파일의 경로를 지정해준다.
		// 만약에 이 경로에 없을 경우엔 java파일로 만들어서 집어넣어야 한다.

		Font font = new Font(baseFont, 11); // 폰트의 사이즈를 11픽셀로 한다.

		PdfPTable table = new PdfPTable(7); // 7개의 셀을 가진 테이블 객체를 생성 (pdf파일에 나타날 테이블)
		Chunk chunk = new Chunk("근태", font); // 타이틀 객체를 생성 (타이틀의 이름을 장바구니로 하고 위에 있는 font를 사용)
		Paragraph ph = new Paragraph(chunk);
		ph.setAlignment(Element.ALIGN_CENTER); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)
		document.add(ph); 

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE); // 줄바꿈 (왜냐하면 타이틀에서 두줄을 내린후에 셀(테이블)이 나오기 때문)

		PdfPCell cell1 = new PdfPCell(new Phrase("번호", font));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell2 = new PdfPCell(new Phrase("사번", font));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell3 = new PdfPCell(new Phrase("부서명", font));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell4 = new PdfPCell(new Phrase("사원명", font));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell5 = new PdfPCell(new Phrase("출근 시간", font));
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell6 = new PdfPCell(new Phrase("퇴근 시간", font));
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell7 = new PdfPCell(new Phrase("지각", font));
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		table.addCell(cell7);
		
		List<AttdDto> list = fileName;// 서비스로부터 id값을 매개값으로 주어서 장바구니목록을 가져온다.
		
		for (int i = 0; i < list.size(); i++) {
			AttdDto dto = list.get(i);
			PdfPCell cellNum = new PdfPCell(new Phrase("" + dto.getAttd_no(), font));
			cellNum.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellEmpno = new PdfPCell(new Phrase("" + dto.getEmpno(), font));
			cellEmpno.setHorizontalAlignment(Element.ALIGN_CENTER);			
			PdfPCell cellDname = new PdfPCell(new Phrase("" + dto.getDname(), font));
			cellDname.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellName = new PdfPCell(new Phrase("" + dto.getName(), font));
			cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellAttdTime = new PdfPCell(new Phrase("" + dto.getAttd_time(), font));
			cellAttdTime.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellOffTime = new PdfPCell(new Phrase("" + dto.getOff_time(), font));
			cellOffTime.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cellEmpLate = new PdfPCell(new Phrase("" + dto.getEmp_late(), font));
			cellEmpLate.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cellNum);
			table.addCell(cellEmpno);
			table.addCell(cellDname);
			table.addCell(cellName);
			table.addCell(cellAttdTime);
			table.addCell(cellOffTime);
			table.addCell(cellEmpLate);
			
	}
		document.add(table);
		document.close();
		writer.close();
	}
}