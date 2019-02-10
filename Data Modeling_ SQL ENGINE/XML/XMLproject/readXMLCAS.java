import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

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

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class readXML
{
	
   public static void main(String[] args)
   {
	   
	   /*
	    * TODO:
       *
       * Does newest value add to top or bottom?
       *
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
	   
	   try
      {
         File file = new File("file.xml");
         JAXBContext jaxbContext = JAXBContext.newInstance(database.class);

         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

         database outputDB = (database) jaxbUnmarshaller.unmarshal(file);

         System.out.println(outputDB);

         System.out.println(outputTable);

         System.out.println(sum(outputTable, "B"));

         System.out.println(avg(outputTable, "A"));
         
         ArrayList<Table> table = new ArrayList<>();
         ArrayList<String> cols = new ArrayList<>();
         ArrayList<String>[] equations = new ArrayList[3];
         
         table.add(outputTable);
         
         cols.add("A");
         cols.add("B");
         
         equations[0].add("Test_Table");
         equations[0].add("Test_Table2");
         
         equations[1].add("=");
         equations[1].add("=");
         
         equations[2].add("-4");
         equations[2].add("10");
         
         join2(table, cols, equations);
         
         //test();
	   }
	   catch(JAXBException e)
      {
		   e.printStackTrace();
	   }
      catch(Exception ex)
      {
      }
	   
	   /*//Use objects to create xml file
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
	   }*/
	   
	   
      
	   
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
   
   static int count(Table t)
   {
	   return t.getRecordStorage().size();
   }
   
   public static String avg(Table currTable, String colName)
   {
      DecimalFormat df = new DecimalFormat("##.##");
      String avg = "Not a number";
      float sum;// = Integer.parseInt(sum(currTable, colName));
      float count = count(currTable);
      
      if(!sum(currTable, colName).equals("Not a number"))
      {
         sum = Float.parseFloat(sum(currTable, colName));
         avg = Float.toString(Float.parseFloat(df.format(sum/count)));
      }
      
      return avg;
   }
   
   //psuedo for now
   public static String sum(Table currTable, String colName)
   {
   List<Record> RecordStorage = currTable.getRecordStorage();
      int sum = 0;
      boolean checked = false;
      
      for(int i = 0; i < RecordStorage.size(); i++)    //for each record
      {
         for(int j = 0; j < RecordStorage.get(i).getCellStorage().size(); j++)    //for each cell
         {
            List<Cell> cellStorage = RecordStorage.get(i).getCellStorage();
            if(cellStorage.get(j).getColName().equals(colName) && typeCheck(cellStorage.get(j).getType()))    //logic: curr.colname == colName && typeCheck()
            {
               sum += Integer.parseInt(cellStorage.get(j).getOrigValue());       //logic of right: currRec.currColName.origVal().toInt();
               checked = true;
            }
         }
      }
      if(checked)
      {
         return Integer.toString(sum);
      }
      else
      {
         return "Not a number";
      }
   }
   
   static boolean typeCheck(String cellType)
   {
      if(cellType.equalsIgnoreCase("int") || cellType.equalsIgnoreCase("integer") || cellType.equalsIgnoreCase("number") || cellType.equalsIgnoreCase("decimal"))    //Here be ye olde number types
      {
         return true;
      }
      return false;
   }
   
   static void join(ArrayList<Table> table, ArrayList<String> cols, ArrayList<String>[] equations)
   {
      String equation = "a > A";
      /*
      for each(size of arraylists) equations[0].get(i).trim() + "` " + equations[2].get(i).trim() + " " + equations[1].get(i).trim()
         check table for the col's
      */
      
      /*
      for(int i = 0; i < equations[0].size(); i++)
      {
         equation = equations[0].get(i) + equations[2].get(i) + equations[1].get(i);
         if(test(equation))      //(prob needs more modification) if true, then:
         {
            
         }
      }*/
      try
      {
         System.out.println(test(equation));
      }
      catch(Exception e)
      {
      }
   }
   
   static boolean test(String origEq) throws Exception
   {
      String[] pieces = origEq.split(" ");   //[0] = left, [1] = operator, [2] = right
      int result;
      
      switch(pieces[1])
      {
         case "=":
            return (pieces[0].equals(pieces[2]));
         case "!=":
         case "<>":
            return (!pieces[0].equals(pieces[2]));
         case ">":
            result = pieces[0].compareTo(pieces[2]);
            if(result>0)
            {            
               return true;//(Integer.parseInt(pieces[0]) > Integer.parseInt(pieces[2]));
            }
            break;
         case "<":
            result = pieces[0].compareTo(pieces[2]);
            if(result<0)
            {            
               return true;//(Integer.parseInt(pieces[0]) > Integer.parseInt(pieces[2]));
            }
            break;
            //return (Integer.parseInt(pieces[0]) < Integer.parseInt(pieces[2]));
         case "<=":
         case "!>":
            result = pieces[0].compareTo(pieces[2]);
            if(result<=0)
            {            
               return true;//(Integer.parseInt(pieces[0]) > Integer.parseInt(pieces[2]));
            }
            break;
            //return (Integer.parseInt(pieces[0]) <= Integer.parseInt(pieces[2]));
         case ">=":
         case "!<":
            result = pieces[0].compareTo(pieces[2]);
            if(result>=0)
            {            
               return true;//(Integer.parseInt(pieces[0]) > Integer.parseInt(pieces[2]));
            }
            break;
            //return (Integer.parseInt(pieces[0]) >= Integer.parseInt(pieces[2]));
      }
      return false;
   } 

   static void join2(ArrayList<Table> table, ArrayList<String> cols, ArrayList<String>[] equation)
   {
      String eq;
      String[] tabCol;// = equation[0].get(i).split();
      String output = "";
      Table currTab = null;
      
      for(int i = 0; i < equation[0].size(); i++)     //for each equation
      {
         tabCol = equation[0].get(i).split(".");      //split lift side
         for(int j = 0; j < table.size(); j++)        //check each table for table in eq
         {
            if(table.get(j).getName().equals(tabCol[0]))    //found table
            {
               currTab = table.get(j);    //set to current table
               break;                     //stop searching when found
            }
         }//end search for table
         if(currTab == null)
         {
            System.out.println("Didn't find table.");
            break;
         }
         for(int j = 0; j < currTab.getRecordStorage().size(); j++)    //for each record
         {
            for(int k = 0; k < currTab.getRecordStorage().get(j).getCellStorage().size(); k++)      //for each cell
            {
               if(currTab.getRecordStorage().get(j).getCellStorage().get(k).equals(tabCol[1]))    //find(found) column
               {
                  //need to create eq based on curr value/col
                  eq = currTab.getRecordStorage().get(j).getCellStorage().get(k).getOrigValue() + " " + equation[1].get(i) + " " + equation[2].get(i);
                  try
                  {
                     if(test(eq))      //if equation true
                     {
                        output += eq;
                        System.out.println(output);
                     }
                  }
                  catch(Exception e)
                  {
                  }
               }
            }
         }
      }
   }
   
   static void groupBy(ArrayList<String> selected, String asThis, ArrayList<String> cols)
   {
      if(cols.size() == 0)
      {
         //run select statement
      }
      else
      {
         //find based on cols, then do select into asThis
      }
   }
}