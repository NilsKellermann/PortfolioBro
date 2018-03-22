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

public class ExcelToYearCourseShares {

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
/////////////////////////////
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println("start2");
				
/////////////////////////////				
				HashMap<Date, Double> countriesList = new HashMap<Date, Double>();
				Iterator<Row> rowIterator = sheet.iterator();
/////////////////////////////
				rowIterator.next();/////////////////////////zweite Zeile anfangen
//				rowIterator.next(); 
				while (rowIterator.hasNext()) 
		        {
					System.out.println("start3");
					Double course = 0.0;
					Date date = null;
					
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();  
		            while (cellIterator.hasNext()) 
		            {
		            	Cell cell = cellIterator.next();
		            	//check the cell type and process accordingly
		            	switch(cell.getCellType()){
		            	case Cell.CELL_TYPE_STRING:
//		            		if(shortCode.equalsIgnoreCase("")){
//		            			shortCode = cell.getStringCellValue().trim();
////		            		}else if(name.equalsIgnoreCase("")){
////		            			//2nd column
////		            			name = cell.getNumericCellValue();
//		            		}else{
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getStringCellValue());
//		            		}
		            		break;
		            	case Cell.CELL_TYPE_NUMERIC:
		            		if(date == null){
		            			DataFormatter dataFormatter = new DataFormatter();
		            			String cellStringValue = dataFormatter.formatCellValue(row.getCell(0));
		            			System.out.println ("Is shows data as show in Excel file" + cellStringValue); 
//		            			
//		            			String string = "1/3/06";
//		            			DateFormat format = new SimpleDateFormat("d/m/''yy", Locale.ENGLISH);
//		            			Date date = null;
//								try {
//									date = format.parse(string);
//								} catch (ParseException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
		            			String string = "1/3/06";
		            			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy", Locale.ENGLISH);
		            			LocalDate date2 = LocalDate.parse(cellStringValue, formatter);
		            			System.out.println(date2); // 2010-01-02
		            			date= Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());;
//		            		}else if(name == 0.0){
//		            			name = 12.0;
	//	            			risk1 = (long) cell.getNumericCellValue();
//		            		}else if(name.equalsIgnoreCase("")){
//		            			//2nd column
//		            			name = cell.getNumericCellValue();
		            		}else{
		            			course = cell.getNumericCellValue();
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getNumericCellValue());
		            		}
		            	}
		            	
		            } //end of cell iterator
		            if (date != null) {
		            countriesList.put(date,course);
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

	public static HashMap <String, HashMap<Date, Double>> getListS(){
		File f = new File("bla");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<Date, Double>> list = readExcelData("Files/ImportsheetShares.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	public static HashMap <String, HashMap<Date, Double>> getListC(){
		File f = new File("bla");
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