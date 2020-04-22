package contact.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellReference;
//셀의 이름과 값을 가져옴 
public class ExcelCellRef {

	public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) {
            cellNum = cell.getColumnIndex();
        }
        else {
        	//셀이 num일 경우 index값을 가져옴 
            cellNum = cellIndex;
        }
        
        return CellReference.convertNumToColString(cellNum);
    }
    
    public static String getValue(Cell cell) {
        String value = "";
        
        if(cell == null) {
            value = "";
        }
        else {
            if( cell.getCellType() == CellType.FORMULA ) {
                value = cell.getCellFormula();
            }
            else if( cell.getCellType() == CellType.NUMERIC ) {
                value = cell.getNumericCellValue() + "";
            }
            else if( cell.getCellType() == CellType.STRING ) {
                value = cell.getStringCellValue();
            }
            else if( cell.getCellType() == CellType.BOOLEAN ) {
                value = cell.getBooleanCellValue() + "";
            }
            else if( cell.getCellType() == CellType.ERROR ) {
                value = cell.getErrorCellValue() + "";
            }
            else if( cell.getCellType() == CellType.BLANK ) {
                value = "";
            }
            else {
                value = cell.getStringCellValue();
            }
        }
        
        return value;
    }
}