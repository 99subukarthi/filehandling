package com.xml_to_db;
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class XML_TO_DB  
	{  
	public static void main(String argv[])   
		{  
			try   
				{  
				//creating a constructor of file class and parsing an XML file  
				File file = new File("D:\\Karthikeyan S\\02_Working_Directory\\Work Space\\Java\\XMLProject\\WebContent\\WEB-INF\\web.xml");  
				//an instance of factory that gives a document builder  
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
				//an instance of builder to parse the specified xml file  
				DocumentBuilder db = dbf.newDocumentBuilder();  
				Document doc = db.parse(file);  
				doc.getDocumentElement().normalize();  
				NodeList nodeList = doc.getElementsByTagName("data"); 
				List<Double> Alist=new ArrayList<Double>();
				List<Double> Blist=new ArrayList<Double>();
				List<Double> Clist=new ArrayList<Double>();
				// nodeList is not iterable, so we are using for loop  
				for (int itr = 0; itr < nodeList.getLength(); itr++)   
				{  
					Node node = nodeList.item(itr);  
					
					if (node.getNodeType() == Node.ELEMENT_NODE)   
					{  
						Element eElement = (Element) node;  
						Alist.add(Double.parseDouble(eElement.getElementsByTagName("A").item(0).getTextContent()));  
						Blist.add(Double.parseDouble(eElement.getElementsByTagName("B").item(0).getTextContent()));  
						Clist.add(Double.parseDouble(eElement.getElementsByTagName("C").item(0).getTextContent()));  
					}  
				}
				Write_Into_Database(Alist,Blist,Clist);
				}   
			catch (Exception e)   
			{  
				e.printStackTrace();  
			}  
		}

		private static void Write_Into_Database(List<Double> alist, List<Double> blist, List<Double> clist) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@10.1.4.78:1621:OD12CR21","DEV_TRAINING_2023","DEV_TRAINING_2023");
		
		String iquery="INSERT INTO DATA VALUES(?,?,?)";
		PreparedStatement pstmt =con.prepareStatement(iquery);
		
		for(int i=0;i<4;i++) {
		pstmt.setDouble(1,alist.get(i));
		pstmt.setDouble(2, blist.get(i));
		pstmt.setDouble(3, clist.get(i));		
		int nori =pstmt.executeUpdate();
		if(nori==1) {
			System.out.println("successfully added....");
				}
		else {
				System.out.println("Something else");
				}
			}
		Statement stmt=con.createStatement();
		ResultSet Ars=stmt.executeQuery("SELECT MAX(A),MIN(A),SUM(A),AVG(A) FROM DATA");
		if(Ars.next()) {
			System.out.println("Column A MAX Value :"+Ars.getDouble(1));
			System.out.println("Column A MIN Value :"+Ars.getDouble(2));
			System.out.println("Column A SUM Value :"+Ars.getDouble(3));
			System.out.println("Column A AVG Value :"+Ars.getDouble(4));
		}
		else {
			System.out.println("Somthing Else....");
		}
		ResultSet Brs=stmt.executeQuery("SELECT MAX(B),MIN(B),SUM(B),AVG(B) FROM DATA");
		if(Brs.next()) {
			System.out.println("Column B MAX Value :"+Brs.getDouble(1));
			System.out.println("Column B MIN Value :"+Brs.getDouble(2));
			System.out.println("Column B SUM Value :"+Brs.getDouble(3));
			System.out.println("Column B AVG Value :"+Brs.getDouble(4));
		}
		else {
			System.out.println("Somthing Else....");
		}
		ResultSet Crs=stmt.executeQuery("SELECT MAX(C),MIN(C),SUM(C),AVG(C) FROM DATA");
		if(Crs.next()) {
			System.out.println("Column C MAX Value :"+Crs.getDouble(1));
			System.out.println("Column C MIN Value :"+Crs.getDouble(2));
			System.out.println("Column C SUM Value :"+Crs.getDouble(3));
			System.out.println("Column C AVG Value :"+Crs.getDouble(4));
		}
		else {
			System.out.println("Somthing Else....");
		}
	}  
	}  