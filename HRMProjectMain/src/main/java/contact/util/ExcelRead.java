package contact.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption){
		
		Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());
		Sheet sheet = wb.getSheetAt(0);
		
		System.out.println("Sheet 이름 : "+ wb.getSheetName(0));
		System.out.println("데이터가 있는 sheet의 수 :"+wb.getNumberOfSheets());
		
		//시트에서 데이터가 있는 행의 개수를 가져옴 
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		Row row = null;
		Cell cell = null;
		
		String cellName = "";
		//map에 하나의 row를 저장(하나의 row는 여러개의 cell로 이루어져 있음)  
		Map<String, String> map = null;
		//list는 map을 포함 
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
		
		for(int rowIndex = excelReadOption.getStartRow() -1; rowIndex< numOfRows; rowIndex++) {
			row = sheet.getRow(rowIndex);
			if(row != null) {
				//가져온 cell의 개수 구하기 
				numOfCells = row.getPhysicalNumberOfCells(); 
				//-> 셀 중간에 빈칸이 있는 경우 빈칸은 생략하고 숫자를 셈 
				
				//셀 중간에 빈칸이 있더라도 마지막 셀의 숫자를 가져옴 
				//numOfCells = row.getLastCellNum();
			
				
				//row값 저장할 map 
				//put("A", "이름"), put("B", "직급/부서"),...	
				map = new HashMap<String, String>();
				
				
				for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					//row에서 cellIndex에 해당하는 cell을 가지고옴 
					cell = row.getCell(cellIndex);
					//cell의 이름을 가지고 옴(A,B,c,D,E)
					cellName = ExcelCellRef.getName(cell, cellIndex);
					
					if(!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					//cell이름을 키로 데이터를 담는다 
					map.put(cellName, ExcelCellRef.getValue(cell));
				}
				//map객체 list에 넣기 
				result.add(map);
			}
		}
		return result;
	}
	
}
