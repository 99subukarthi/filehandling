package com.xls_to_xml;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element; 


public class XLS_TO_XML  
	{  
		public static void main(String args[]) throws Exception  
				{ 
	File f=new File("D:\\Karthikeyan S\\03_Documents\\Second Task\\secondtable.xls");
	FileInputStream fis=new FileInputStream(f);  
	HSSFWorkbook wb=new HSSFWorkbook(fis);   
	HSSFSheet sheet=wb.getSheetAt(0);  
	CreationHelper ch=wb.getCreationHelper();
	FormulaEvaluator formulaEvaluator=ch.createFormulaEvaluator(); 
	List<String> title=new ArrayList<String>();
	List<Double> values=new ArrayList<Double>();
	for(Row row: sheet)     //iteration over row using for each loop  
		{  
			for(Cell cell: row)    //iteration over cell using for each loop  
				{  
					switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
					{  
					case Cell.CELL_TYPE_NUMERIC:  //field that represents numeric cell type  
												//getting the value of the cell as a number  
						values.add(cell.getNumericCellValue()); 
						break;  
					case Cell.CELL_TYPE_STRING:    
												//field that represents string cell type  
												//getting the value of the cell as a string  
						title.add(cell.getStringCellValue());  
						break;  
					}  
				}  
		}
	
	Thread.sleep(2000);
	System.out.println("Excel File Readed....");
	Write_To_Xml(title,values);
	} 

	public static void Write_To_Xml(List<String> s,List<Double> d) throws Exception {
		
		// Create a new XML document
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document doc = docBuilder.newDocument();
	        
	     // Create a root element and add it to the document
	        Element rootElement = doc.createElement("datas");
	        doc.appendChild(rootElement);
	        
	       // Create a new employee element with some data and add it to the root element
	       
	        int count=0;
	       for(int i=1;i<=4;i++) {
	    	Element data = doc.createElement("data");
		    rootElement.appendChild(data);
	        Element A = doc.createElement(s.get(0));
	        A.appendChild(doc.createTextNode(""+d.get(count)+""));
	        data.appendChild(A);
	        count++;
	        Element B = doc.createElement(s.get(1));
	        B.appendChild(doc.createTextNode(""+d.get(count)+""));
	        data.appendChild(B);
	        count++;
	        
	        Element C = doc.createElement(s.get(2));
	        C.appendChild(doc.createTextNode(""+d.get(count)+""));
	        data.appendChild(C);
	        count++;
	       }
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new FileOutputStream(new File("D:\\Karthikeyan S\\02_Working_Directory\\Work Space\\Java\\XMLProject\\WebContent\\WEB-INF\\web.xml")));
	        transformer.transform(source, result);
	        System.out.println("Excel Data Added Into XML File.....");
        
	}
}  