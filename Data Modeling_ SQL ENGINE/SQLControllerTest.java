/*Construct a Controller class to:
-	Perform basic SQL commands
*/

// Record.java imports //
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

// Table.java imports //
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

// Cell.java imports //
import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.math.BigDecimal;
import java.util.ArrayList;

// END OF imports


public class SQLControllerTest {

 // Keep track of the table names that have been created
 private ArrayList<Table> tables = new ArrayList<Table>();
 private ArrayList<String> tableNames = new ArrayList<String>();
 
 // Keep track of the which database is currently active
 private String activeDatabase = null;

 public static void main(String[] arg) throws IOException{
 
   // Start the controller test
   SQLControllerTest test = new SQLControllerTest();
   
   //TEST: creating a database
   String data = "myDatabase";
   test.create_database(data);
   
   //TEST: creating a table
   String tableNameTEST = "department";
   ArrayList<String> colNamesTEST = new ArrayList<String>();
   ArrayList<String> colTypesTEST = new ArrayList<String>();
   colNamesTEST.add("dname");
   colTypesTEST.add("char");
   colNamesTEST.add("dnumber");
   colTypesTEST.add("number");
   colNamesTEST.add("dmanager");
   colTypesTEST.add("char");
   test.create_table(tableNameTEST, colNamesTEST, colTypesTEST);
   
   //TEST: inserting into a table
   ArrayList<String> valuesTEST = new ArrayList<String>();
   valuesTEST.add("depart1");
   valuesTEST.add("20");
   valuesTEST.add("manager1");
   test.insert(tableNameTEST, colNamesTEST, colTypesTEST); 
   
   //TEST: saving changes in the database
   test.save();
   
   //TEST: loading a database that was previously created
   test.load_database(data);
 
 } // main

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public void create_database(String name) {
  // Do SQL CREATE DATABASE command
  // Make a new .xml file
      File file = new File(name + ".xml");
      
      // Set this xml file as the current active database
      activeDatabase = name;
      
  } // create_database
  
  
  public void create_table(String tableName, ArrayList<String> colNames, ArrayList<String> colTypes) {
  // Do SQL CREATE TABLE command
  // Check to see if the table was created before
  /*
  boolean duplicate = false;
  for(String table: tableNames) {
   if(tableName.equals(table)) {
     duplicate = true;
  }
  */ 
     // Make a new table
     Table table = new Table();
     table.setName(tableName);
     // Add the name of the table to the list
     tableNames.add(tableName);
     tables.add(table);
      
    // Create storage for records in the table
    List<Record> recordStorage = new ArrayList<>();
    Record record = new Record();
	
    // Create storage for cells in the record
	 List<Cell> cellStorage = new ArrayList<>();
    record.setCellStorage(cellStorage);
   
    // Set column names and types
    for(int i = 0; i < colNames.size(); i++) {
       Cell cell = new Cell();
       cell.setColName(colNames.get(i)); 
       cell.setType(colTypes.get(i));
       cellStorage.add(cell);   
    }
    
     // Store cell(s) in records and record(s) in tables
     record.setCellStorage(cellStorage);
     recordStorage.add(record);
     table.setRecordStorage(recordStorage);
    
    // Keep track of any additional values in wUPDATES
	 ArrayList<String> additionalValue = new ArrayList<>();
     
  } // create_table 
  

  public void insert(String tableName, ArrayList<String> colNames, ArrayList<String> values) {
  // Do SQL INSERT command
  
  // Keep track of the cell values and col names
  List<Cell> cellStorage = new ArrayList<>();
  // Make space for the new values to be inserted
  List<Record> recordStorage = new ArrayList<>();  
   
      // Find out which table the user is inserting into
      for(int i = 0; i < tables.size(); i++) {
        if(tableName.equals(tableNames.get(i))) {
          // Create a new record
          Record record = new Record();
      
          // Insert the values in the corresponding columns
          for(int j = 0; j < values.size(); j++) {
            Cell cell = new Cell();
            cell.setColName(colNames.get(j));
            cell.setOrigValue(values.get(j));
            cellStorage.add(cell);
          }
          
          // Store the new cells in the new record and the new record in the current table
          record.setCellStorage(cellStorage);
          recordStorage.add(record);
          tables.get(i).setRecordStorage(recordStorage);
          break;
        }
      }  
           
  } // insert
  
  
  public void save() {
  // Do SQL SAVE command
      try{
		 File file = new File(activeDatabase + ".xml");
		 
		 JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
		 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
      // Make sure every table that was created is saved to the database
      for(Table table: tables)
		 jaxbMarshaller.marshal(table , file);
		 
	   }	   
	   catch(JAXBException e){
		   e.printStackTrace();
	   }
 
  } // save 
  
  
  public void load_database(String name) {
  // Do SQL LOAD DATABASE command
     try{
		File file = new File(name + ".xml");
	   JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
	   
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		Table outputTable = (Table) jaxbUnmarshaller.unmarshal(file);
		
	   System.out.println(outputTable);
	   }
	   catch(JAXBException e){
		   e.printStackTrace();
	   }
  
  } // load_database

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@XmlRootElement
class Record {
	private Cell cell;
	
	
	 private List<Cell> cellStorage;
	
	public void setCellStorage(List<Cell> cellStorage){
		
		this.cellStorage = cellStorage;
	}
	@XmlElement(name = "Cell", type = Cell.class)
	public List<Cell> getCellStorage(){
		return cellStorage;
	}
	
} // Record

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@XmlRootElement

class Table {
	private String name;
	
	
	private List<Record> recordStorage;

	public String getName(){
		return name;
	}
	
	@XmlAttribute
	public void setName(String name){
		this.name = name;
	}
	
	public List<Record> getRecordStorage(){
		return recordStorage;
	}
	
	@XmlElement(name = "Record", type = Record.class)
	public void setRecordStorage(List<Record> recordStorage){
		this.recordStorage = recordStorage;
	}
	
	@Override
	public String toString(){
		String xml = "Table name: " + name;
		for(int i = 0; i < recordStorage.size();i++){
		for(int y = 0; y < recordStorage.get(i).getCellStorage().size();y++){
			xml += " Record: " + recordStorage.get(i).getCellStorage().get(y).toString(); 
		}
		}
		return xml;
	}
	
	
	
} // Table


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@XmlRootElement
class Cell {
	private String colName;
	private String origValue;
	private ArrayList<String> additionalValue;
	private String type;
	
	public String getColName(){
		return colName;
	}
	
	@XmlAttribute
	public void setColName(String colName){
		this.colName = colName;
	}
	
	public String getOrigValue(){
		return origValue;
	}
	
	@XmlElement
	public void setOrigValue(String origValue){
		this.origValue = origValue;
	}
	
	public ArrayList<String> getAdditionalValue(){
		return additionalValue;
	}
	
	@XmlElement
	public void setAdditionalValue(ArrayList<String> additionalValue){
		this.additionalValue = additionalValue;
	}
	
	public String getType(){
		return type;
	}
	@XmlElement
	public void setType(String type){
		this.type = type;
	}
	
	@Override
	public String toString(){
		return "Column name:" + colName +" Type:"+ type +" Original Value:"+ origValue +" Additional Values:" + additionalValue.toString();
	}
	
	
	
} // Cell

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

} // SQLControllerTest