import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xml_parser{

	public void readXML(String fileName)
	{
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("section");

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element sectionNode = (Element) fstNode;
					
					NodeList classidElementList = sectionNode.getElementsByTagName("ClassId");
					Element classidElmnt = (Element) classidElementList.item(0);
					NodeList classidNodeList = classidElmnt.getChildNodes();
					System.out.println("ClassId : "  + ((Node) classidNodeList.item(0)).getNodeValue().trim());

					NodeList secnoElementList = sectionNode.getElementsByTagName("SecNo");
					Element secnoElmnt = (Element) secnoElementList.item(0);
					NodeList secno = secnoElmnt.getChildNodes();
					System.out.println("Section number : "  + ((Node) secno.item(0)).getNodeValue().trim());

					NodeList semesterElementList = sectionNode.getElementsByTagName("Semester");
					Element semElmnt = (Element) semesterElementList.item(0);
					NodeList sem = semElmnt.getChildNodes();
					System.out.println("Semester : "  + ((Node) sem.item(0)).getNodeValue().trim());

					NodeList empnoElementList = sectionNode.getElementsByTagName("Emp_no");
					Element empElmnt = (Element) empnoElementList.item(0);
					NodeList emp = empElmnt.getChildNodes();
					int emp_no = Integer.parseInt(((Node) emp.item(0)).getNodeValue().trim());
					System.out.println("Professor : "  + emp_no);

					System.out.println();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String args[]){
		try {

			xml_parser showXML = new xml_parser();
			showXML.readXML ("newsemester.xml");
		}catch( Exception e ) {
			e.printStackTrace();

		}//end catch

	}//end main

}//end class
