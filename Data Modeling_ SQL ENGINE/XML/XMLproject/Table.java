import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement

public class Table {
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
	
	
	
}
