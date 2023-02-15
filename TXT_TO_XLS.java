package com.txt_xls;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TXT_TO_XLS {
  public static void main(String[] args) {
    String textFilePath = "D:\\Karthikeyan S\\03_Documents\\Second Task\\tabletext.txt";
    String excelFilePath = "D:\\Karthikeyan S\\03_Documents\\Second Task\\tableexcel.xls";
    
    try {
      FileInputStream fis = new FileInputStream(textFilePath);
      XSSFWorkbook wb = new XSSFWorkbook();
      XSSFSheet sheet = wb.createSheet();
      String line;
      InputStreamReader isr=  new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      int rownum = 0;
      while ((line = br.readLine()) != null) {
    	  
        String[] tokens = line.split(",");
        Row row = sheet.createRow(rownum++);
        int cellnum = 0;
        for (String token : tokens) {
        Cell cell = row.createCell(cellnum++);
        String n[]=new String[100];
        for(int i=0;i<100;i++) {
        	n[i]=""+i+"";
        	 if(token.equals(n[i])) {
        	        double num=Double.parseDouble(token);
        	        cell.setCellValue(num);
        	        break;
        }
        	 else {
        		 cell.setCellValue(token);
        	 }
        }
      }
      }
      br.close();
      //Calculate Sum,Avg,Min,Max
      calculation(fis,wb,sheet);
      
      // Autofit columns
      for (int i = 0; i < rownum; i++) {
        sheet.autoSizeColumn(i);
      }
      // Write data to output file
      FileOutputStream outputStream = new FileOutputStream(excelFilePath);
      wb.write(outputStream);

      outputStream.close();
      System.out.println("Excel file has been created successfully from " + textFilePath);
    } 
      catch (IOException e) {
      e.printStackTrace();
    }
  }

private static void calculation(FileInputStream fi,XSSFWorkbook wb,XSSFSheet sheet) {
	 // Calculate SUM
    Row Row5 = sheet.createRow(5);
    Cell sCell0 = Row5.createCell(0);
    sCell0.setCellValue("SUM");
    Cell sCell1 = Row5.createCell(1);
    sCell1.setCellFormula("SUM(B2:B5)");
    Cell sCell2 = Row5.createCell(2);
    sCell2.setCellFormula("SUM(C2:C5)");
    Cell sCell3 = Row5.createCell(3);
    sCell3.setCellFormula("SUM(D2:D5)");
    //Calculate MAX
    Row Row6 = sheet.createRow(6);
    Cell maxCell0 = Row6.createCell(0);
    maxCell0.setCellValue("MAX");
    Cell maxCell1 = Row6.createCell(1);
    maxCell1.setCellFormula("MAX(B2:B5)");
    Cell maxCell2 = Row6.createCell(2);
    maxCell2.setCellFormula("MAX(C2:C5)");
    Cell maxCell3 = Row6.createCell(3);
    maxCell3.setCellFormula("MAX(D2:D5)");
  //Calculate Min
    Row Row7 = sheet.createRow(7);
    Cell minCell0 = Row7.createCell(0);
    minCell0.setCellValue("MIN");
    Cell minCell1 = Row7.createCell(1);
    minCell1.setCellFormula("MIN(B2:B5)");
    Cell minCell2 = Row7.createCell(2);
    minCell2.setCellFormula("MIN(C2:C5)");
    Cell minCell3 = Row7.createCell(3);
    minCell3.setCellFormula("MIN(D2:D5)");
    //Calculate Avg
    Row Row8 = sheet.createRow(8);
    Cell avgCell0 = Row8.createCell(0);
    avgCell0.setCellValue("AVG");
    Cell avgCell1 = Row8.createCell(1);
    avgCell1.setCellFormula("AVERAGE(B2:B5)");
    Cell avgCell2 = Row8.createCell(2);
    avgCell2.setCellFormula("AVERAGE(C2:C5)");
    Cell avgCell3 = Row8.createCell(3);
    avgCell3.setCellFormula("AVERAGE(D2:D5)");	
}
}

