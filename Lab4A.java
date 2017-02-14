import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.*;

public class Lab4A{

        public void Main_Bitch(String fileName, Connection con)
        {
			System.out.println("THIS");
                try {
                        File file = new File(fileName);
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        Document doc = db.parse(file);
                        doc.getDocumentElement().normalize();
                        NodeList nodeLst = doc.getElementsByTagName("section");
                        Statement stmt = con.createStatement();

                        for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);	


                                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                                        Element sectionNode = (Element) fstNode;

										//ClassID
                                        NodeList classidElementList = sectionNode.getElementsByTagName("ClassId");
                                        Element classidElmnt = (Element) classidElementList.item(0);
                                        NodeList classidNodeList = classidElmnt.getChildNodes();
                                        String ClassId = ((Node) classidNodeList.item(0)).getNodeValue().trim();
					
										//Section Number
                                        NodeList secnoElementList = sectionNode.getElementsByTagName("SecNo");
                                        Element secnoElmnt = (Element) secnoElementList.item(0);
                                        NodeList secno = secnoElmnt.getChildNodes();
                                        String Section = ((Node) secno.item(0)).getNodeValue().trim();

										//Semester
                                        NodeList semesterElementList = sectionNode.getElementsByTagName("Semester");
                                        Element semElmnt = (Element) semesterElementList.item(0);
                                        NodeList sem = semElmnt.getChildNodes();
                                        String Semester = ((Node) sem.item(0)).getNodeValue().trim();
										
										//Professor
                                        NodeList empnoElementList = sectionNode.getElementsByTagName("Emp_no");
                                        Element empElmnt = (Element) empnoElementList.item(0);
                                        NodeList emp = empElmnt.getChildNodes();
                                        String emp_no = ((Node) emp.item(0)).getNodeValue().trim();
                                        

										//Classroom
										NodeList crElementList = sectionNode.getElementsByTagName("ClassRoom");
                                        Element crElmnt = (Element) crElementList.item(0);
                                        NodeList cr = crElmnt.getChildNodes();
                                        String classroom = ((Node) emp.item(0)).getNodeValue().trim();

										//Times and Days
										NodeList TDElementList = sectionNode.getElementsByTagName("Time");
                                        Element TDElmnt = (Element) TDElementList.item(0);
                                        NodeList TD = TDElmnt.getChildNodes();
                                        String TiDa = ((Node) TD.item(0)).getNodeValue().trim();
										String delims = "[ ]+";
										String[] tokens = TiDa.split(delims);
										String Begin_time = tokens[0]; 
										String End_time = tokens[2];
										String Days = tokens[3];
                                        
											
										

										try{
										ResultSet rs;
										rs = stmt.executeQuery("SELECT * FROM prof_qual WHERE Emp_ID = '" + emp_no + "' and " + "c_ID= '"+ ((Node) classidNodeList.item(0)).getNodeValue().trim() + "'; ");
										rs.last();
											if (rs.getRow() > 0)
											{
												try{
												
												Boolean result = stmt.execute("INSERT INTO Class_Section VALUES ('" + ClassId + "','" + Section + "','" + Semester + "','" + Begin_time +"','" +End_time+ "','" +Days+ "','" +classroom+"'); ");
												if (result == false)
												{
													System.out.println("Added A Section");
												}
												Boolean result2 = stmt.execute("INSERT INTO Teaches VALUES ('" + ClassId +"','" + Section + "','" + Semester + "','" + emp_no + "');");
												if (result2 == false)
												{
													System.out.println("Added A New Relation between a teacher and a class");
												}
												}catch(Exception e) {
												System.out.println(e);
												System.out.println("Failed to Insert");
												}
											

											}
											else{
											   System.out.println("The Teacher "+ emp_no+ " is not qualified to teach " + ClassId);
											}
										}catch(Exception e){
										System.out.print(e);
										System.out.println(
										"No Class table to query");
										}
										System.out.println();
										
                                }             
                              

                        }
				ResultSet result3 = stmt.executeQuery("Select Class_Section.c_ID, Classes.credits, Class_Section.SecNO, Class_Section.StartTime, Class_Section.EndTime, Class_Section.DAYS, Teaches.Emp_ID, Class_Section.Classroom From Class_Section, Classes, Teaches Where Class_Section.c_ID = Classes.c_ID and Class_Section.c_ID=Teaches.c_ID and Class_Section.Semester ='SP16' ORDER BY Class_Section.c_ID, Class_Section.SecNO ");
                                 ResultSetMetaData rsmd = result3.getMetaData();
                                 int cN = rsmd.getColumnCount();
                                  while (result3.next()) {
                                  for (int i = 1; i <= cN; i++) {
                                                      if (i > 1) System.out.print(",  ");
                                                        String columnValue = result3.getString(i);
                                                       System.out.print(columnValue + " " + rsmd.getColumnName(i));
                                                                                                        }
                                                       System.out.println("");
                                                                                                }

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }


        public static void main(String args[]){
		    Connection con = null;

			try {
			Statement stmt;
			ResultSet rs;

			// Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for
			// database named 'user' on the faure.
			String url ="jdbc:mysql://faure/prayfran";

			// Get a connection to the database for a
			// user named 'user' with the password
			// 123456789.
			con = DriverManager.getConnection(
            url,"prayfran", "829638867");

			// Display URL and connection information
			System.out.println("URL: " + url);
			System.out.println("Connection: " + con);

			// Get a Statement object
			stmt = con.createStatement();

			try{
				rs = stmt.executeQuery("SELECT * FROM Classes");
				
				}catch(Exception e){
				System.out.print(e);
				System.out.println(
                  "No Class table to query");
			}//end catch

			}catch( Exception e ) {
				e.printStackTrace();

			}//end catch

            try {
				Lab4A showXML = new Lab4A();
                showXML.Main_Bitch("newsemester.xml", con);
            }catch( Exception e ) {
                  e.printStackTrace();

             }//end catch
			
        }//end main

}//end class
