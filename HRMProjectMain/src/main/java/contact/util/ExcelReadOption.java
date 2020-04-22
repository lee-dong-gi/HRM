package contact.util;

import java.util.ArrayList;
import java.util.List;
//엑셀파일을 읽을 때 읽어올 옵션 설정
//파일 경로, 추출할 컬럼, 추출을 시작할 행 번호 
public class ExcelReadOption {
	
	private String filePath;

	//추출할 컬럼명
	private List<String> outputColumns;

    private int startRow;
    
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public List<String> getOutputColumns() {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        return temp;
    }
    public void setOutputColumns(List<String> outputColumns) {
        
        List<String> temp = new ArrayList<String>();
        temp.addAll(outputColumns);
        
        this.outputColumns = temp;
    }
    
    public void setOutputColumns(String ... outputColumns) {
        
        if(this.outputColumns == null) {
            this.outputColumns = new ArrayList<String>();
        }
        
        for(String ouputColumn : outputColumns) {
            this.outputColumns.add(ouputColumn);
        }
    }
    
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
