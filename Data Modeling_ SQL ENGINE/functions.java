import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class functions {

	public int count(TableXML table, String str) {
		int counter = 0;
		List<RecordXML> RecordStorage = table.getRecordStorage();
		List<CellXML> cellStorage;
		if (str.equals("*")) {
			return table.getRecordStorage().size();
		} else {
			for (int i = 0; i < RecordStorage.size(); i++) {
				for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++) {
					cellStorage = RecordStorage.get(i).getCellStorage();
					if (cellStorage.get(y).getColName().equals(str)) {
						counter++;
					}
				}
			}
		}
		return counter;
	}

	public String min(TableXML table, String str) {
		String min = "";

		List<RecordXML> RecordStorage = table.getRecordStorage();
		List<CellXML> cellStorage;

		// stores the first value to min
		for (int i = 0; i < RecordStorage.size(); i++) {
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++) {
				cellStorage = RecordStorage.get(i).getCellStorage();
				if (cellStorage.get(y).getColName().equals(str)) {
					min = cellStorage.get(y).getOrigValue();
					break;
				}
			}
		}

		// does the comparison to find the min
		for (int i = 0; i < RecordStorage.size(); i++) {
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++) {
				cellStorage = RecordStorage.get(i).getCellStorage();
				if (cellStorage.get(y).getColName().equals(str)) {
					if (cellStorage.get(y).getOrigValue().compareTo(min) < 0) {
						min = cellStorage.get(y).getOrigValue();
					}
				}
			}
		}

		return min;
	}

	public String max(TableXML table, String str) {
		String max = "";
		List<RecordXML> RecordStorage = table.getRecordStorage();
		List<CellXML> cellStorage;

		// Sets max to first value
		for (int i = 0; i < RecordStorage.size(); i++) {
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++) {
				cellStorage = RecordStorage.get(i).getCellStorage();
				if (cellStorage.get(y).getColName().equals(str)) {
					max = cellStorage.get(y).getOrigValue();
					break;
				}
			}
		}

		// finds the max
		for (int i = 0; i < RecordStorage.size(); i++) {
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++) {
				cellStorage = RecordStorage.get(i).getCellStorage();
				if (cellStorage.get(y).getColName().equals(str)) {
					if (cellStorage.get(y).getOrigValue().compareTo(max) > 0) {
						max = cellStorage.get(y).getOrigValue();
					}
				}
			}
		}

		return max;
	}

	public String avg(TableXML currTable, String colName) {
		DecimalFormat df = new DecimalFormat("##.##");
		String avg = "Not a number";
		float sum;// = Integer.parseInt(sum(currTable, colName));
		float count = count(currTable, colName);

		if (!sum(currTable, colName).equals("Not a number")) {
			sum = Float.parseFloat(sum(currTable, colName));
			avg = Float.toString(Float.parseFloat(df.format(sum / count)));
		}

		return avg;
	}

	public String sum(TableXML currTable, String colName) {
		List<RecordXML> RecordStorage = currTable.getRecordStorage();
		int sum = 0;
		boolean checked = false;

		for (int i = 0; i < RecordStorage.size(); i++) // for each record
		{
			for (int j = 0; j < RecordStorage.get(i).getCellStorage().size(); j++) {
				List<CellXML> cellStorage = RecordStorage.get(i).getCellStorage();
				if (cellStorage.get(j).getColName().equals(colName) && typeCheck(cellStorage.get(j).getType())) {
					sum += Integer.parseInt(cellStorage.get(j).getOrigValue());
					checked = true;
				}
			}
		}
		if (checked) {
			return Integer.toString(sum);
		} else {
			return "Not a number";
		}
	}

	boolean typeCheck(String cellType) {
		if (cellType.equalsIgnoreCase("int") || cellType.equalsIgnoreCase("integer")
				|| cellType.equalsIgnoreCase("number") || cellType.equalsIgnoreCase("decimal")) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String []> groupBy(TableXML table, String func, String aggCol, String newCol, String colName1, String colName2){
		
		//SELECT colName, SUM(aggCol) AS newCol FROM table GROUP BY (colName1) or (colName2) or (colName1, colName2) or () HAVING function(haveCol) operator literal;
		
		List<RecordXML> RecordStorage = table.getRecordStorage();
		ArrayList<String []> list = new ArrayList<String []>();
		
		String currentCell = "";
		String runningNum = "0";
		int sum = 0;
		
		if((colName2.equals("") && !colName1.equals("")) | (!colName2.equals("") && colName1.equals(""))){
			String[] names = new String[2];
			names[0] = colName1;
			names[1] = func;
			list.add(names);
			
		for(int i = 0; i < RecordStorage.size(); i++){		//for each record
			String[] record = new String[2]; 
			
			for(int j = 0; j < RecordStorage.get(i).getCellStorage().size(); j++){		//look at each cell
				List<CellXML> cellStorage = RecordStorage.get(i).getCellStorage();
				if(cellStorage.get(j).getColName().equals(colName1)){	//find column
					if(currentCell.equals("")){							//find first value
						currentCell = cellStorage.get(j).getOrigValue();
						record[0] = currentCell;						//set value into record array
						
						for(int k = 0; k < cellStorage.size(); k++){	//look at each cell
							if(cellStorage.get(k).getColName().equals(aggCol)){		//find aggregate column
								sum = Integer.parseInt(runningNum) + Integer.parseInt(cellStorage.get(k).getOrigValue());	//add to total
							}
						}
						
					}
					else if(!cellStorage.get(j).getOrigValue().equals(currentCell)){	//found a new value
						record[1] = String.valueOf(sum);								//record total
						list.add(record);
						currentCell = cellStorage.get(j).getOrigValue();				//new value
						record[0] = currentCell;
						runningNum = "0";
						
						for(int m = 0; m < cellStorage.size(); m++){
							if(cellStorage.get(m).getColName().equals(aggCol)){
								sum = Integer.parseInt(runningNum) + Integer.parseInt(cellStorage.get(m).getOrigValue());
							}
						}
					}
					else{		//value does not change, continue to add
						for(int k = 0; k < cellStorage.size(); k++){
							if(cellStorage.get(k).getColName().equals(aggCol)){
								sum = Integer.parseInt(runningNum) + Integer.parseInt(cellStorage.get(k).getOrigValue());
							}
						}
					}
				}
			}
		}
	
	}
	
		else if(colName1.equals("") && colName2.equals("")){
			String[] record = new String[1];
			record[0] = newCol;
			record[1] = sum(table, aggCol);
			list.add(record);
			
			return(list);
			
		}
		
		else{
			String[] names = new String[3];
			names[0] = colName1;
			names[1] = colName2;
			names[2] = func;
			list.add(names);
			for(int i = 0; i < RecordStorage.size(); i++){		//for each record
				String[] record = new String[3]; 
				
				for(int j = 0; j < RecordStorage.get(i).getCellStorage().size(); j++){		//look at each cell
					List<CellXML> cellStorage = RecordStorage.get(i).getCellStorage();
					
					if(cellStorage.get(j).getColName().equals(colName1)){
						record[0] = cellStorage.get(j).getOrigValue();
					}
					else if(cellStorage.get(j).getColName().equals(colName2)){
						record[1] = cellStorage.get(j).getOrigValue();
					}
					else if(cellStorage.get(j).getColName().equals(aggCol)){
						record[2] = cellStorage.get(j).getOrigValue();
					}
					
					list.add(record);
				}
	
			}
		}
		return(list);
	}
	
	public ArrayList<String []> groupingSets(TableXML table, String func, String aggCol, String newCol, String colName1, String colName2){
		
		//SELECT colName1, colName2, func(aggCol) AS newCol FROM table GROUP BY GROUPING SETS ( (colName1), (colName2) );
		
		
		ArrayList<String []> list1 = new ArrayList<String []>(); 
		ArrayList<String []> list2 = new ArrayList<String []>(); 
		
		list1 = groupBy(table, func, aggCol, newCol, colName1, "");//group by (colName1);
		list2 = groupBy(table, func, aggCol, newCol, "", colName2);//group by (colName2);
		
		ArrayList<String []> listFinal = new ArrayList<String []>();
		String[] record = new String[3];
		
		record[0] = colName1;
		record[1] = colName2;
		record[2] = newCol;
		listFinal.add(record);
		
		for(int i = 1; i < list1.size(); i++){
			String[] temp = new String[2];
			temp = list1.get(i);
			record[0] = temp[0];
			record[1] = "null";
			record[2] = temp[1];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list2.size(); i++){
			String[] temp = new String[2];
			temp = list2.get(i);
			record[0] = "null";
			record[1] = temp[0];
			record[2] = temp[1];
			listFinal.add(record);
		}
		
		return(listFinal);
	}
	
	public ArrayList<String []> rollUp(TableXML table, String func, String aggCol, String newCol, String colName1, String colName2){
		
		//SELECT colName1, colName2, func(aggCol) AS newCol FROM table GROUP BY ROLLUP (colName1, colName2);
		
		
		ArrayList<String []> list1 = new ArrayList<String []>(); 
		ArrayList<String []> list2 = new ArrayList<String []>(); 
		ArrayList<String []> list3 = new ArrayList<String []>(); 
		
		list1 = groupBy(table, func, aggCol, newCol, colName1, colName2);	//group by (colName1, colName2);
		list2 = groupBy(table, func, aggCol, newCol, colName1, "");		//group by (colName1);
		list3 = groupBy(table, func, aggCol, newCol, "", "");		//group by ();
		
		ArrayList<String []> listFinal = new ArrayList<String []>();
		String[] record = new String[3];
		
		record[0] = colName1;
		record[1] = colName2;
		record[2] = newCol;
		listFinal.add(record);
		
		for(int i = 1; i < list1.size(); i++){
			String[] temp = new String[3];
			temp = list1.get(i);
			record[0] = temp[0];
			record[1] = temp[1];
			record[2] = temp[2];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list2.size(); i++){
			String[] temp = new String[2];
			temp = list2.get(i);
			record[0] = temp[0];
			record[1] = "null";
			record[2] = temp[1];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list3.size(); i++){
			String[] temp = new String[1];
			temp = list3.get(i);
			record[0] = "null";
			record[1] = "null";
			record[2] = temp[0];
			listFinal.add(record);
		}
		
		return(listFinal);
	}
	
	public ArrayList<String []> cube(TableXML table, String func, String aggCol, String newCol, String colName1, String colName2){
		
		//SELECT colName1, colName2, func(aggCol) AS newCol FROM table GROUP BY CUBE (colName1, colName2);
		
		ArrayList<String []> list1 = new ArrayList<String []>(); 
		ArrayList<String []> list2 = new ArrayList<String []>(); 
		ArrayList<String []> list3 = new ArrayList<String []>(); 
		ArrayList<String []> list4 = new ArrayList<String []>(); 
		
		list1 = groupBy(table, func, aggCol, newCol, colName1, colName2);	//group by (colName1, colName2);
		list2 = groupBy(table, func, aggCol, newCol, colName1, "");		//group by (colName1);
		list3 = groupBy(table, func, aggCol, newCol, "", colName2);		//group by (colName2);
		list4 = groupBy(table, func, aggCol, newCol, "", "");		//group by ();
		
		ArrayList<String []> listFinal = new ArrayList<String []>();
		String[] record = new String[3];
		
		record[0] = colName1;
		record[1] = colName2;
		record[2] = newCol;
		listFinal.add(record);
		
		for(int i = 1; i < list1.size(); i++){
			String[] temp = new String[3];
			temp = list1.get(i);
			record[0] = temp[0];
			record[1] = temp[1];
			record[2] = temp[2];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list2.size(); i++){
			String[] temp = new String[2];
			temp = list2.get(i);
			record[0] = temp[0];
			record[1] = "null";
			record[2] = temp[1];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list3.size(); i++){
			String[] temp = new String[2];
			temp = list3.get(i);
			record[0] = "null";
			record[1] = temp[0];
			record[2] = temp[1];
			listFinal.add(record);
		}
		
		for(int i = 1; i < list4.size(); i++){
			String[] temp = new String[1];
			temp = list4.get(i);
			record[0] = "null";
			record[1] = "null";
			record[2] = temp[0];
			listFinal.add(record);
		}
		
		return(listFinal);
	}
	public ArrayList<String []> having(ArrayList<String[]> list, String function, String operator, String colName){
		
		ArrayList<String []> listFinal = new ArrayList<String []>();
		
		int colLocation;
		
			String[] temp = new String[3];
			temp = list.get(0);
			
			for(int i = 0; i < temp.length; i++){
				if(temp[i].equals(colName)){
					colLocation = i;
					break;
				}
			}
		
		
		if(function.equals("COUNT")){
			for(int i = 1; i < list.size(); i++){
				String[] temp = new String[3];
				temp = list.get(i);
				
				if(operator.equals("<")){
					temp[colLocation].compareTo(anotherString)
				}
				else if(operator.equals(">")){
					
				}
				else if(operator.equals("!=")){
					
				}
				else if(operator.equals("=")){
					
				}
			}
			
			
		}
	}
}