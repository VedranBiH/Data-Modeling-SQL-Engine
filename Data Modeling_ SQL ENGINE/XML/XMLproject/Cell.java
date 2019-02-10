import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.math.BigDecimal;
import java.util.ArrayList;

@XmlRootElement
public class Cell {
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
	
	
	
}
