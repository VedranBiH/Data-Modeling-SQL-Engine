import java.text.DecimalFormat;
import java.util.List;

public class functions
{
   //print view
   public void genPrinter(ArrayList<String> outputCol, ArrayList<ArrayList<String>> outputVal)
   {
      //outputCol - arraylist of column names
      //outputVal - tabled arraylist or values
      //for each colname
      for(int i = 0; i < outputCol.size(); i++)
      {
         //print the column name formated to 10 spaces
         System.out.printf("%-10s", outputCol.get(i));
      }  //end for each colname, colnames
      //print new line
      System.out.print("\n");
      //for each col
      for(int i = 0; i < outputCol.size(); i++)
      {
         //for each char in name
         for(int j = 0; j < outputCol.get(i).length(); j++)
         {
            //add a '-'
            System.out.print("-");
            //if not the last column
            if(j == outputCol.get(i).length()-1)
            {
               //for extra spaces
               for(int k = 0; k < 10-outputCol.get(i).length(); k++)
               {
                  //space afterwards
                  System.out.print(" ");
               }  //end for extra spaces
            }  //end if last
         }  //end for each char
      }  //end for each col, dashes
      //print new line
      System.out.print("\n");
      //for each row
      for (int i = 0; i < outputVal.get(0).size(); i++)
      {
         //for each col in that row
         for(int j = 0; j < outputVal.get(i).size(); j++)
         {
            //print that value
            System.out.printf("%-10s", outputVal.get(j).get(i));
         }  //end for each col in row
         //print new line
         System.out.print("\n");
      }  //end for each row
   }  //end genPrinter func

   //count occurances
	public int count(TableXML table, String str)
   {
      //table = table to search, str = col name looking for
		//counter
      int counter = 0;
      //list of current table's records
		List<RecordXML> RecordStorage = table.getRecordStorage();
		//list of cells(to be assigned/initialized)
      List<CellXML> cellStorage;
      //if selecting all columns
		if (str.equals("*"))
      {
         //return size of array of records
			return table.getRecordStorage().size();
		}  //end if all
      //if not selecting all columns
      else
      {
         //check every record
			for (int i = 0; i < RecordStorage.size(); i++)
         {
            //check every cell in each record
				for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++)
            {
               //create a list of cells
					cellStorage = RecordStorage.get(i).getCellStorage();
               //if the column name for current cell is same as the column name being searched
					if (cellStorage.get(y).getColName().equals(str))
               {
                  //add to counter
						counter++;
					}  //end if matches
				}  //end for each cell
			}  //end for each record
		}  //end else (not all)
      //after every cell/record searched, return the count
		return counter;
	}  //end count func

   //minimum value
	public String min(TableXML table, String str)
   {
      //table - current table
      //str - column being searched
      
      //minimum/smallest value - start empty
		String min = "";

      //list of records in the table
		List<RecordXML> RecordStorage = table.getRecordStorage();
      //undeclared list of cells for each record
		List<CellXML> cellStorage;

      //stores the first value to min
      //for each element in the list of records
		for (int i = 0; i < RecordStorage.size(); i++)
      {
         //for each cell in that record
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++)
         {
            //create a list of cells
				cellStorage = RecordStorage.get(i).getCellStorage();
            //if the column of the cell matches the column being searched
				if (cellStorage.get(y).getColName().equals(str))
            {
               //set the first value to the minimum
					min = cellStorage.get(y).getOrigValue();
					//stop searching
               break;
				}  //end if matches
			}  //end for each cell
      }  //end for each record

		//does the comparison to find the min
      //for each record
		for (int i = 0; i < RecordStorage.size(); i++)
      {
         //for each cell
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++)
         {
            //create a list of the cells
				cellStorage = RecordStorage.get(i).getCellStorage();
            //if the column name matches the search
				if (cellStorage.get(y).getColName().equals(str))
            {
               //if the current value is less than the min
					if (cellStorage.get(y).getOrigValue().compareTo(min) < 0)
               {
                  //make the current value the new min
						min = cellStorage.get(y).getOrigValue();
					}  //end if less than
				}  //end if col name matches
			}  //end for each cell
		}  //end for each record
      //return the current/final min
		return min;
	}  //end min func

	public String max(TableXML table, String str)
   {
      //table - current table
      //str - col to search
      
      //create max variable (starts empty)
		String max = "";
      //list of records in current table
		List<RecordXML> RecordStorage = table.getRecordStorage();
		//empty list of cells
      List<CellXML> cellStorage;

		//Sets max to first value
      //for each record
		for (int i = 0; i < RecordStorage.size(); i++)
      {
         //for each cell
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++)
         {
            //create a list of the cells
				cellStorage = RecordStorage.get(i).getCellStorage();
            //if the current cell column name is what is being searched
				if (cellStorage.get(y).getColName().equals(str))
            {
               //set max
					max = cellStorage.get(y).getOrigValue();
					//stop searching
               break;
				}  //end if matches
			}  //end for each cell
		}  //end for each record

		// finds the max
      //for each record
		for (int i = 0; i < RecordStorage.size(); i++)
      {
         //for each cell
			for (int y = 0; y < RecordStorage.get(i).getCellStorage().size(); y++)
         {
            //create a list of the cells
				cellStorage = RecordStorage.get(i).getCellStorage();
            //if the current cell column name is what is being searched
				if (cellStorage.get(y).getColName().equals(str))
            {
               //if current value is greater than the max
					if (cellStorage.get(y).getOrigValue().compareTo(max) > 0)
               {
                  //set new max to current val
						max = cellStorage.get(y).getOrigValue();
					}  //end if greater than
				}  //end if matches
			}  //end for each cell
		}  //end for each record
      //return the current/final max
		return max;
	}  //end max func

	public String avg(TableXML currTable, String colName)
   {
      //create a formatting so decimals are only 2 decimal places
		DecimalFormat df = new DecimalFormat("##.##");
      //create average variable - initialize to error
		String avg = "Not a number";
      //create the sum var
		float sum;// = Integer.parseInt(sum(currTable, colName));
      //create count var
		float count = count(currTable, colName);
      
      //if sum doesn't returns back as "Not a number" (meaning it is a number)
		if (!sum(currTable, colName).equals("Not a number"))
      {
         //parse the sum to a float and put into sum variable
			sum = Float.parseFloat(sum(currTable, colName));
         //divide the sum and count and parse the string delimited to two decimal places to a float then to a string
			avg = Float.toString(Float.parseFloat(df.format(sum / count)));
		}  //end if !(not a number)
      //return current avg
		return avg;
	}  //end avg func

	public String sum(TableXML currTable, String colName)
   {
      //currTable - current table
      //colName - column name to be searched
      
      //create list of records from current table
		List<RecordXML> RecordStorage = currTable.getRecordStorage();
      //initialize sum variable to 0
		int sum = 0;
      //checked flag starting at false
		boolean checked = false;

      //for each record
		for (int i = 0; i < RecordStorage.size(); i++)
		{
         //for each cell
			for (int j = 0; j < RecordStorage.get(i).getCellStorage().size(); j++)
         {
            //create a list of cells
				List<CellXML> cellStorage = RecordStorage.get(i).getCellStorage();
            //if the column name matches the search and the types are fine,
				if (cellStorage.get(j).getColName().equals(colName) && typeCheck(cellStorage.get(j).getType()))
            {
               //add the value to the sum
					sum += Integer.parseInt(cellStorage.get(j).getOrigValue());
               //flag true
					checked = true;
				}  //end if matches + type ok
			}  //end for each cell
		}  //end for each record
		if (checked)   //if flag (is it set to true)
      {
         //if the flag is true, return the sum
			return Integer.toString(sum);
		}  //end if flag
      else
      {
         //if the flag is false, return "Not a number"
			return "Not a number";
		}  //end else flag
	}  //end sum func

	boolean typeCheck(String cellType)
   {
      //cellType - type for the cell that is passed
      //if the type is a form of number
		if (cellType.equalsIgnoreCase("int") || cellType.equalsIgnoreCase("integer") || cellType.equalsIgnoreCase("number") || cellType.equalsIgnoreCase("decimal"))
      {
         //return true
			return true;
		}  //end if is number
      //return false if reached
		return false;
	}  //end typeCheck func
}  //end functions class
