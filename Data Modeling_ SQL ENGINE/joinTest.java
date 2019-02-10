import java.util.ArrayList;

public class joinTest
{

   /*
   SELECT column_name(s)
   FROM table1
   JOIN table2
   ON table1.column_name=table2.column_name;
   */

   public static void main(String[] args)
   {
      ArrayList<ArrayList<String>> retVal;// = new ArrayList<ArrayList<String>>();
   
      ArrayList<String> cols = new ArrayList<String>();
      /*String tabO = "tabOne";
      String tabT = "tabTwo";*/
      String[] tables = {"tabOne", "TabTwo"};
      String[] equation = {"tabOne.col2", "=", "tabTwo.col3"};//new String[3];
      
      cols.add("col1");
      cols.add("col2");
      cols.add("col3");
      
      retVal = join(cols, tables, equation);
   }

   //join function
   public static ArrayList<ArrayList<String>> join(ArrayList<String> cols, String[] tables, String[] equation)
   {
      //create value to return
      ArrayList<ArrayList<String>> retVal = new ArrayList<ArrayList<String>>();
      
      //split to find table/col being compared
      String[] arg1 = equation[0].split("\\.");    //table name = 0, col name = 1
      String[] arg2 = equation[2].split("\\.");    //table name = 0, col name = 1
      
      //System.out.println(arg2[0] + " " + arg2[1]);
      
      //for each record in table 1 (arg1[0]),
      for(int i = 0; i < table1.getRecordStorage.size(); i++)
      {
         //for each cell in each record
         for(int j = 0; j < table1.getRecordStorage.get(i).getCellStorage.size(); j++)
         {
            //if the col matches arg1[1]
            if(table1.getRecordStorage.get(i).getCellStorage.get(j).equals(arge1[1]))
            {
               //for each record in table 2 (arg2[0]),
               for(int f = 0; f < table2.getRecordStorage.size(); f++)
               {
                  //for each cell in table 2,
                  for(in d = 0; d < table2.getRecordStorage.get(f).getCellStorage.size(); d++)
                  {
                     //if colName matches arg2[1]
                     if(table2.getRecordStorage.get(f).getCellStorage.get(d).equals(arg2[1]))
                     {
                        //check value of arg2[1] against value of arg1[1] using equation[1]
                        switch(equation[1])
                        {
                           case "=":
                              if(arg1[1].equals(arg2[1]))
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           case "<":
                              if(arg1[1].compareTo(arg2[1]) < 0)
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           case ">":
                              if(arg1[1].compareTo(arg2[1]) > 0)
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           case ">=":
                              if(arg1[1].compareTo(arg2[1]) >= 0)
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           case "<=":
                              if(arg1[1].compareTo(arg2[1]) <= 0)
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           case "!=":
                              if(arg1[1].equals(arg2[1]) != 0)
                              {
                                 //add every element for the tuple based on cols
                              }
                              break;
                           default:
                              System.out.println("Problem with equation.")
                              break;
                        }
                     }
                  }
               }
            }
         }
      {
      return retVal;
   }  //end join func

   public static void genPrinter(ArrayList<String> outputCol, ArrayList<ArrayList<String>> outputVal)
   {
      for(int i = 0; i < outputCol.size(); i++)    //for each colname
      {
            System.out.printf("%-10s", outputCol.get(i));
      }     //end for each colname, colnames
      System.out.print("\n");
      //working ^
      for(int i = 0; i < outputCol.size(); i++)    //for each col
      {
         //System.out.println(outputCol.get(i).length());
         for(int j = 0; j < outputCol.get(i).length(); j++)  //for each char in name
         {
            System.out.print("-");                          //add a '-'
            if(j == outputCol.get(i).length()-1)              //if not the last column
            {
               for(int k = 0; k < 10-outputCol.get(i).length(); k++)    //for extra spaces
               {
                  System.out.print(" ");           //space afterwards
               }  //end for extra spaces
            }  //end if last
         }  //end for each char
      }  //end for each col, dashes
      System.out.print("\n");
      for (int i = 0; i < outputVal.get(0).size(); i++)     //for each row
      {
         for(int j = 0; j < outputVal.get(i).size(); j++)   //for each col in that row
         {
            System.out.printf("%-10s", outputVal.get(j).get(i));  //print that value
         }  //end for each col in row
         System.out.print("\n");
      }  //end for each row
   }  //end genPrinter
}