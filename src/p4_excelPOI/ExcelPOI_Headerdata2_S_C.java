package p4_excelPOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelPOI_Headerdata2_S_C {
	
	public static HashMap <String, HashMap<String, Double>> readExcelData(String fileName) {
		HashMap <String, HashMap<String, Double>> result= new HashMap <String, HashMap<String, Double>>();
		System.out.println("start");
		try {
			//Input stream von xlsx/xls Datei erzeugen
			FileInputStream fis = new FileInputStream(fileName);
			//Workbook erzeugen
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			
			int numberOfSheets = workbook.getNumberOfSheets();
//////////////////////////////			
			//Excel-Sheets durchlaufen
			for(int i=0; i < numberOfSheets; i++){
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("start2");

//////////////////////////////
				Iterator<Row> rowIterator = sheet.iterator();
				HashMap<String, Double> c_list= new HashMap<String, Double>();				
				Double risikopa= 0.0;
				Double renditepa= 0.0;
//////////////////////////////
				
				Row row = rowIterator.next();
				if(row.getCell(5) != null)risikopa = row.getCell(5).getNumericCellValue();
				row = rowIterator.next();
				if(row.getCell(5) != null)renditepa = row.getCell(5).getNumericCellValue();
				
				c_list.put("Risikopa",risikopa);
				c_list.put("Renditepa",renditepa);
/////////////////////////////

			result.put(workbook.getSheetName(i), c_list);
		} //Sheets durchlaufen ENDE
		fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static HashMap <String, HashMap<String, Double>> getListS(){
		File f = new File("excel");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, Double>> list = readExcelData("Files/ImportsheetShares.xlsx");
		System.out.println("Assetclass List\n"+list);
		return list;
	}
	
	public static HashMap <String, HashMap<String, Double>> getListC(){
		File f = new File("excel");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, Double>> list = readExcelData("Files/ImportsheetCommodities.xlsx");
		System.out.println("Assetclass List\n"+list);
		return list;
	}
	
	public static void main(String args[]){
		getListC();
	}

}