package contact.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contact.domain.ExcelDto;
import contact.persistence.ExcelDao;
import contact.util.ExcelRead;
import contact.util.ExcelReadOption;
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	private ExcelDao exceldao;


	@Override
	public void excelUpload(File destFile) {
		  ExcelReadOption excelReadOption = new ExcelReadOption();
		  excelReadOption.setFilePath(destFile.getAbsolutePath());
	      excelReadOption.setOutputColumns("A","B","C","D","E");
	      excelReadOption.setStartRow(2);
	      List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);
	      Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("excelContet", excelContent);
	      
	      ExcelDto ed = new ExcelDto();
	      
	      try {
	    	  for(Map<String, String> article: excelContent){
	    	//	  System.out.println(article.get("A")); 
	    	//	  System.out.println(article.get("B")); 
	    	//	  System.out.println(article.get("C")); 
	    	//	  System.out.println(article.get("D"));
	    	//	  System.out.println(article.get("E"));     
	    		  //DTO로 만들어줘서 insert로 삽입 
	    		  ed.setName(article.get("A"));
	    		  ed.setLevel(article.get("B"));
	    		  ed.setCompname(article.get("C"));
	    		  ed.setEmail(article.get("D"));
	    		  ed.setPhonenum(article.get("E"));
	    		  ed.setExcelContent(excelContent);
	    		  System.out.println(ed);
	    	  }
//	    	  exceldao.insertExcel(paramMap);
	    	  exceldao.insertExcel(ed);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	   
		  
	}

}
