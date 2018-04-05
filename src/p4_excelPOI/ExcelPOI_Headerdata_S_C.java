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

import p0_model.db_objects.AnlageKlasse;

public interface ExcelPOI_Headerdata_S_C {
	
	public static HashMap <String, HashMap<String, String>> readExcelDataS(String fileName) {
		HashMap <String, HashMap<String, String>> result= new HashMap <String, HashMap<String, String>>();
		 
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
				 

				Iterator<Row> rowIterator = sheet.iterator();

//////////////////////////////
				
				rowIterator.next();/////////////////////////zweite Zeile anfangen
//				rowIterator.next();
				while (rowIterator.hasNext()) 
		        {
					HashMap<String, String> countriesList = new HashMap<String, String>();
					 
					String aktienname = "";
					String branche = "";
					String index = "";
					//String kategorie = "";
					
					Row row = rowIterator.next();
					
					if(row.getCell(0) != null)aktienname = row.getCell(0).getStringCellValue();
					if(row.getCell(1) != null)branche = row.getCell(1).getStringCellValue();
					if(row.getCell(2) != null)index = row.getCell(2).getStringCellValue();
		            if (aktienname != null && aktienname != "") {
		            countriesList.put("Branche",branche);
		            countriesList.put("Index",index);
		            result.put(aktienname, countriesList);
		            }
		        } //end of rows iterator 

			} //end of sheets for loop
		//close file input stream
		fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
		
		public static HashMap <String, HashMap<String, String>> readExcelDataC(String fileName) {
			HashMap <String, HashMap<String, String>> result= new HashMap <String, HashMap<String, String>>();
			 
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
					 

					Iterator<Row> rowIterator = sheet.iterator();

	//////////////////////////////
					
					rowIterator.next();/////////////////////////zweite Zeile anfangen
//					rowIterator.next();
					while (rowIterator.hasNext()) 
			        {
						HashMap<String, String> countriesList = new HashMap<String, String>();
						 
						String rohstoffname = "";
//						String branche = "";
//						String index = "";
						String kategorie = "";
						
						Row row = rowIterator.next();
						
						if(row.getCell(0) != null)rohstoffname = row.getCell(0).getStringCellValue();
						if(row.getCell(1) != null)kategorie = row.getCell(1).getStringCellValue();
//						if(row.getCell(2) != null)index = row.getCell(2).getStringCellValue();
			            if (rohstoffname != null && rohstoffname != "") {
			            countriesList.put("Kategorie", kategorie);
//			            countriesList.put("Index",index);
			            result.put(rohstoffname, countriesList);
			            }
			        } //end of rows iterator 

				} //end of sheets for loop
			//close file input stream
			fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}

	public static HashMap <String, HashMap<String, String>> getListS(){
		File f = new File("bla");
		 
		HashMap <String, HashMap<String, String>> list = readExcelDataS("Files/DatenUebersicht.xlsx");
		 
		 
		return list;
	}
	
	public static HashMap <String, HashMap<String, String>> getListC(){
		File f = new File("bla");
		 
		HashMap <String, HashMap<String, String>> list = readExcelDataC("Files/DatenUebersichtC.xlsx");
		 
		 
		return list;
	}
	
	public static void main(String args[]){
		getListS();
	}

}