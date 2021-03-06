import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class readXML {
	
   public static void main(String[] args) {
	   
	   /*
	    * TODO:
	    * Functions using the object methods and displaying
	    * information
	    * 
	    * Semantic analyzer
	    * 
	    * Going over previous code and figuring out where to
	    * implement new code.
	    * 
	    * Test basic functionality
	    * 
	    * Test new functionality
	    * 
	    * Displaying Errors
	    * 
	    * Debugging
	    */
	   
	   
	   //Read an XML file and serialized it into objects
	   
	   try{
		   File file = new File("file.xml");
	   JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
	   
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		Table outputTable = (Table) jaxbUnmarshaller.unmarshal(file);
		
		System.out.println(outputTable);
	   }
	   catch(JAXBException e){
		   e.printStackTrace();
	   }
	   
	   //Use objects to create xml file
	   List<Record> recordStorage = new ArrayList<>();
	   List<Cell> cellStorage = new ArrayList<>();
	   ArrayList<String> additionalValue = new ArrayList<>();
	   
	   Table table = new Table();
	   table.setName("Test Table");
	   Record record = new Record();
	   Cell cell = new Cell();
	   
	   additionalValue.add("Extra value");
	   
	   additionalValue.add("Second extra value");
	   
	   
	   cell.setColName("A");
	   cell.setOrigValue("Hello");
	   cell.setAdditionalValue(additionalValue);
	   cell.setType("VARCHAR");
	   
	   recordStorage.add(record);
	   cellStorage.add(cell);
	   
	   record.setCellStorage(cellStorage);
	   table.setRecordStorage(recordStorage);
	   
	   try{
		 File file = new File("file.xml");
		 
		 JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
		 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		jaxbMarshaller.marshal(table, file);
		 
	   }
	   
	   	catch(JAXBException e){
		   e.printStackTrace();
	   }
	   
	   
      
	   
	   	/*	try {
         File inputFile = new File("file.xml");
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbFactory.newDocumentBuilder();

         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();

         XPath xPath =  XPathFactory.newInstance().newXPath();

         String expression = "/table[@name = 'Test Table']/record/cell[@colName = 'A']";	        
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
         for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nCurrent Element :" 
               + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("original value is " 
                  + eElement
                     .getElementsByTagName("origValue")
                     .item(0)
                     .getTextContent());
               System.out.println("Additional value : "
                  + eElement
                     .getElementsByTagName("additionalValue")
                     .item(0)
                     .getTextContent());
               System.out.println("The type is " 
                  + eElement
                     .getElementsByTagName("type")
                     .item(0)
                     .getTextContent());
            }
         }
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (XPathExpressionException e) {
         e.printStackTrace();
      }*/
   }
}
