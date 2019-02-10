import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
public class Record {
	private Cell cell;
	
	
	 private List<Cell> cellStorage;
	
	public void setCellStorage(List<Cell> cellStorage){
		
		this.cellStorage = cellStorage;
	}
	@XmlElement(name = "Cell", type = Cell.class)
	public List<Cell> getCellStorage(){
		return cellStorage;
	}
	
}
