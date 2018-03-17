package p4_excelPOI;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db_objects.Assetclass;

public class ExcelToYearCourseShares3 {
	
	public static HashMap <String, HashMap<String, Double>> readExcelData(String fileName) {
		HashMap <String, HashMap<String, Double>> result= new HashMap <String, HashMap<String, Double>>();
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
				HashMap<String, Double> countrieslist= new HashMap<String, Double>();				
				Double risikopa= 0.0;
				Double renditepa= 0.0;
//////////////////////////////
				
				Row row = rowIterator.next();
				if(row.getCell(5) != null)risikopa = row.getCell(5).getNumericCellValue();
				row = rowIterator.next();
				if(row.getCell(5) != null)renditepa = row.getCell(5).getNumericCellValue();
				
				countrieslist.put("Risikopa",risikopa);
				countrieslist.put("Renditepa",renditepa);
/////////////////////////////

			result.put(workbook.getSheetName(i), countrieslist);
		} //end of sheets for loop
		//close file input stream
		fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static HashMap <String, HashMap<String, Double>> getListS(){
		File f = new File("bla");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, Double>> list = readExcelData("Files/ImportsheetShares.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	
	public static HashMap <String, HashMap<String, Double>> getListC(){
		File f = new File("bla");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, Double>> list = readExcelData("Files/ImportsheetCommodities.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	
	public static void main(String args[]){
		getListC();
	}

}