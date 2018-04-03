package p4_excelPOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExcelPOI_HalfyearCourses_S_C {
	
	public static HashMap <String, HashMap<Date, Double>> readExcelData(String fileName) {
		HashMap <String, HashMap<Date, Double>> result= new HashMap <String, HashMap<Date, Double>>();
		System.out.println("start");
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			
			//Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
//////////////////////////////			
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("start2");
//////////////////////////////				
				Iterator<Row> rowIterator = sheet.iterator();
				HashMap<Date, Double> countriesList = new HashMap<Date, Double>();
//////////////////////////////
				
				rowIterator.next();/////////////////////////zweite Zeile anfangen
//				rowIterator.next();
				while (rowIterator.hasNext()) 
		        {
					System.out.println("start3");
					Double halfYearCourse = 0.0;
					Date halfYearDate = null;
					
					Row row = rowIterator.next();
					if(row.getCell(3) != null)halfYearCourse = row.getCell(3).getNumericCellValue();
					if(row.getCell(2) != null)halfYearDate = row.getCell(2).getDateCellValue();
		            if (halfYearDate != null) {
		            countriesList.put(halfYearDate,halfYearCourse);  
		            }
		        } //end of rows iterator 
				result.put(workbook.getSheetName(i), countriesList);
			} //end of sheets for loop
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static HashMap <String, HashMap<Date, Double>> getListS() {
		File f = new File("file1");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<Date, Double>> list = readExcelData("Files/ImportsheetShares.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}

	public static HashMap <String, HashMap<Date, Double>> getListC() {
		File f = new File("file1");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<Date, Double>> list = readExcelData("Files/ImportsheetCommodities.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	
	public static void main(String args[]){
		getListC();
	}	
	
}