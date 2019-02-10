import java.util.ArrayList;

public class test
{

   public static void main(String[] args)
   {
      ArrayList<ArrayList<String>> outputVal = new ArrayList<ArrayList<String>>();
      ArrayList<String> outputCol = new ArrayList<String>();
      
      outputCol.add("A");
      outputCol.add("Col2");
      outputCol.add("Date");
      
      outputVal.add(new ArrayList<String>());
      outputVal.add(new ArrayList<String>());
      outputVal.add(new ArrayList<String>());
      
      outputVal.get(0).add("5");
      outputVal.get(0).add("7");
      outputVal.get(0).add("");
      
      outputVal.get(1).add("7");
      outputVal.get(1).add("");
      outputVal.get(1).add("3");
      
      outputVal.get(2).add("date");
      outputVal.get(2).add("date");
      outputVal.get(2).add("date");
      
      genPrinter(outputCol, outputVal);
      
      /*for(int i = 0; i < outputCol.size(); i++)    //col.size()       //for each colname
      {
            System.out.printf("%-10s", outputCol.get(i));
      }     //end for each colname, colnames
      System.out.print("\n");
      //working ^
      for(int i = 0; i < outputCol.size(); i++)    //col.size()    //for each col
      {
         //System.out.println(outputCol.get(i).length());
         for(int j = 0; j < outputCol.get(i).length(); j++)  //col.get(i).size()  //for each char in name
         {
            System.out.print("-");                          //add a '-'
            if(j == outputCol.get(i).length()-1)              //col.get(i).length()-1         //if not the last column
            {
               for(int k = 0; k < 10-outputCol.get(i).length(); k++)    //10(spacing)-col.get(i).size()     //for extra spaces
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
      }  //end for each row*/
   }  //end main
   
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

}  //end class