package p4_excelPOI;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db_objects.Assetclass;

public class ExcelToAssetclassList2 {

	public static HashMap <String, HashMap<Integer, Assetclass>> readExcelData(String fileName) {
		
		HashMap <String, HashMap<Integer, Assetclass>> result= new HashMap <String, HashMap<Integer, Assetclass>>();
		
		
		
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
			
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				HashMap<Integer, Assetclass> countriesList = new HashMap<Integer, Assetclass>();
				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);
				
				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				int i1= 1;
				
				rowIterator.next(); /////////////Mit zweiter Zeile starten.
				while (rowIterator.hasNext()) 
		        {
					Double name = 0.0;
					Double risk1 = 0.0;
					String shortCode = "";
					
					//Get the row object
					Row row = rowIterator.next();
					
					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();
		             
		            while (cellIterator.hasNext()) 
		            {
		            	//Get the Cell object
		            	Cell cell = cellIterator.next();
		            	
		            	//check the cell type and process accordingly
		            	switch(cell.getCellType()){
		            	case Cell.CELL_TYPE_STRING:
		            		if(shortCode.equalsIgnoreCase("")){
		            			shortCode = cell.getStringCellValue().trim();
//		            		}else if(name.equalsIgnoreCase("")){
//		            			//2nd column
//		            			name = cell.getNumericCellValue();
		            		}else{
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getStringCellValue());
		            		}
		            		break;
		            	case Cell.CELL_TYPE_NUMERIC:
		            		if(name == 0.0){
		            			name = cell.getNumericCellValue();
		            		}else if(risk1 == 0.0){
			            			risk1 = cell.getNumericCellValue();
//		            		}else if(name.equalsIgnoreCase("")){
//		            			//2nd column
//		            			name = cell.getNumericCellValue();
		            		}else{
		            			//random data, leave it
		            			System.out.println("Random data::"+cell.getNumericCellValue());
		            		}
		            	}
		            	
		            } //end of cell iterator
		            Assetclass c = new Assetclass(i1, shortCode, name, risk1);
		            countriesList.put(i1, c);
		            i1= i1 +1;
		            result.put(workbook.getSheetName(i) + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", countriesList);
		        } //end of rows iterator
			} //end of sheets for loop
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//HashMap<Integer, Assetclass> assetclasses
	public static void main(String args[]){
		File f = new File("bla");
		System.out.println(f.getAbsolutePath());
		HashMap <String, HashMap<Integer, Assetclass>> list = readExcelData("Files/Assetklassen.xlsx");
		System.out.println("Assetclass List\n"+list);
	}

}