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

import p0_db_objects.AnlageKlasse;

public class ExcelPOI_Headerdata_S_C {
	
	public static HashMap <String, HashMap<String, String>> readExcelDataS(String fileName) {
		HashMap <String, HashMap<String, String>> result= new HashMap <String, HashMap<String, String>>();
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

				Iterator<Row> rowIterator = sheet.iterator();

//////////////////////////////
				
				rowIterator.next();/////////////////////////zweite Zeile anfangen
//				rowIterator.next();
				while (rowIterator.hasNext()) 
		        {
					HashMap<String, String> countriesList = new HashMap<String, String>();
					System.out.println("start3");
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

					Iterator<Row> rowIterator = sheet.iterator();

	//////////////////////////////
					
					rowIterator.next();/////////////////////////zweite Zeile anfangen
//					rowIterator.next();
					while (rowIterator.hasNext()) 
			        {
						HashMap<String, String> countriesList = new HashMap<String, String>();
						System.out.println("start3");
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
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, String>> list = readExcelDataS("Files/DatenUebersicht.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	
	public static HashMap <String, HashMap<String, String>> getListC(){
		File f = new File("bla");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<String, String>> list = readExcelDataC("Files/DatenUebersichtC.xlsx");
		System.out.println("Assetclass List\n"+list);
		System.out.println("abc");
		return list;
	}
	
	public static void main(String args[]){
		getListS();
	}

}