import java.util.*;
public class Lex {
	
	public static char[] holdArray = new char[100];
	public static int index;
	public static int currentState;
	public static Queue theQueue = new Queue(256); 
	
	public static void main(String[] args){
		while(true){
			currentState = 0;
			Scanner input = new Scanner(System.in);
		
			String command = input.nextLine();
			analyze(command);
		}		
	}
		public static void analyze(String str){
			int check = 0;
			while (str.charAt(check) == ' ' | str.charAt(check) == '\t'){
				check++;
			}
				
			for(int i = check; i < str.length(); i++){
				if(Character.isAlphabetic(str.charAt(i))){
					holdArray[index] = str.charAt(i);
					index++;
				}		
				else if(Character.isDigit(str.charAt(i))){
					holdArray[index] = str.charAt(i);
					index++;
				}
				else if(str.charAt(i) == ' ' && holdArray[0] != ' ')
					createWord();
				
				else if(str.charAt(i) == '*'){
					holdArray[index] = str.charAt(i);
					createWord();
				}
				
				else if(str.charAt(i) == ',' | str.charAt(i) == '(' | str.charAt(i) == ')' | str.charAt(i) == ';' | str.charAt(i) == '/' | str.charAt(i) == ':'){
					if(holdArray[0] == ' '){
						holdArray[index] = str.charAt(i);
						createWord();
					}
					else{
						createWord();
						holdArray[index] = str.charAt(i);
						createWord();
					}
				}
				else if(str.charAt(i) == '\''){
					if(holdArray[0] == ' '){
						holdArray[index] = str.charAt(i);
						createWord();
					}
					else{
						createWord();
						holdArray[index] = str.charAt(i);
						createWord();
					}				
				}
				else if(str.charAt(i) == '<' | str.charAt(i) == '>' | str.charAt(i) == '=' | str.charAt(i) == '!'){
					if(holdArray[0] != ' '){
						createWord();
						holdArray[index] = str.charAt(i);
						createWord();
					}
					else{
						holdArray[index] = str.charAt(i);
						createWord();
					}	
				}
			}
		}
		public static void cleanArray(){
			for(int i = 0; i < holdArray.length; i++){
				holdArray[i] = ' ';
				index = 0;
			}
		}
		
		public static void cleanQueue(){
			theQueue.setFinalSize(theQueue.getSize()); 
			for(int i = theQueue.getFinalSize() - 1;  i > 0; i--){
				theQueue.reset(i);
			}
		}
		
		public static void createWord(){
			String word = new String(holdArray).trim();
			cleanArray();
			direct(word);
		}
		
		public static void direct(String str){

			switch(currentState){
			
			case 0:	
				state0(str);
				break;
			case 1:
				state1(str);
				break;
			case 2:
				state2(str);
				break;
			case 3:
				state3(str);
				break;
			case 4:
				state4(str);
				break;
			case 5:
				state5(str);
				break;
			case 6:
				state6(str);
				break;
			case 7:
				state7(str);
				break;
			case 8:
				state8(str);
				break;
			case 9:
				state9(str);
				break;
			case 10:
				state10(str);
				break;
			case 11:
				state11(str);
				break;
			case 12:
				state12(str);
				break;
			case 13:
				state13(str);
				break;
			case 14:
				state14(str);
				break;
			case 15:
				state15(str);
				break;
			case 16:
				state16(str);
				break;
			case 17:
				state17(str);
				break;
			case 18:
				state18(str);
				break;
			case 19:
				state19(str);
				break;
			case 20:
				state20(str);
				break;
			case 21:
				state21(str);
				break;
			case 22:
				state22(str);
				break;
			case 23:
				state23(str);
				break;
			case 24:
				state24(str);
				break;
			case 25:
				state25(str);
				break;
			case 26:
				state26(str);
				break;
			case 27:
				state27(str);
				break;
			case 28:
				state28(str);
				break;
			case 29:
				state29(str);
				break;
			case 30:
				state30(str);
				break;
			case 31:
				state31(str);
				break;
			case 32:
				state32(str);
				break;
			case 33:
				state33(str);
				break;
			case 34:
				state34(str);
				break;
			case 35:
				state35(str);
				break;
			case 36:
				state36(str);
				break;
			case 37:
				state37(str);
				break;
			case 38:
				state38(str);
				break;
			case 39:
				state39(str);
				break;
			case 40:
				state40(str);
				break;
			}
		}
		
		
		public static void state0(String str){
			if(str.equals("SELECT")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 1;	
			}
			else if(str.equals("WSELECT")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 28;	
			}
			else if(str.equals("WUPDATE")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 30;
			}
		}
		public static void state1(String str){
			if(str.equals("ALL")| str.equals("DISTINCT")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 2;
			}
			else if(str.equals("*")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 2;
			}
			else{
				Token tok = new Token(str, false, true, false, false);
				theQueue.insert(tok);
				currentState = 5;
			}
		}
		public static void state2(String str){
			if(str.equals("FROM")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 3;
			}
			else if(str.equals("AS")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 15;
			}
		}
		public static void state3(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 4;
		}
		public static void state4(String str){
			if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 8;
			}
			else if(str.equals("WHERE")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 9;
			}
			else if(str.equals("GROUP")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 18;
			}
			else if(str.equals(";")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 7;
			}
		}
		public static void state5(String str){
			if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 6;
			}
			else if(str.equals("FROM")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 3;
			}
			else if(str.equals("AS")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 15;
			}
			else if(str.equals("<") | str.equals(">") | str.equals("=")| str.equals("!")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 10;
			}
		}
		public static void state6(String str){
			if(str.equals("COUNT") | str.equals("AVG") | str.equals("SUM") | str.equals("MIN") | str.equals("MAX")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 13;
			}
			else{
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 5;
			}
		}
		public static void state7(String str){
			cleanQueue();
			//this state would send queue to parser
		}
		public static void state8(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 4;
		}
		public static void state9(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 10;
		}
		public static void state10(String str){
			if(str.equals("<") | str.equals(">") | str.equals("=")| str.equals("!")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 10;
			}
			else if(str.equals("'")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 17;
			}
			else{
				Token tok = new Token(str, false, false, false, true);
				theQueue.insert(tok);
				currentState = 11;
			}
		}
		public static void state11(String str){
			if(str.equals("'")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 11;
			}
			else{
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 12;
			}
		}
		public static void state12(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 7;
		}
		public static void state13(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 14;
		}
		public static void state14(String str){
			if(str.equals("*")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 16;
			}
			else{
				Token tok = new Token(str, false, true, false, false);
				theQueue.insert(tok);
				currentState = 16;
			}
		}
		public static void state15(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 5;
		}
		public static void state16(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 5;
		}
		public static void state17(String str){
			Token tok = new Token(str, false, false, false, true);
			theQueue.insert(tok);
			currentState = 11;
		}
		public static void state18(String str){
			Token tok = new Token(str, false, false, true, false);
			theQueue.insert(tok);
			currentState = 19;
		}
		public static void state19(String str){
			if(str.equals("GROUPING")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 21;
			}
			else if(str.equals("ROLLUP")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 27;
			}
			else if(str.equals("CUBE")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 27;
			}
			else{
				Token tok = new Token(str, false, true, false, false);
				theQueue.insert(tok);
				currentState = 20;
			}
		}
		public static void state20(String str){
			if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 19;
			}
			else if(str.equals(";")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 7;
			}
			else if(str.equals("HAVING")){
				Token tok = new Token(str, false, false, true, false);			
				theQueue.insert(tok);
				currentState = 6;
			}		
		}
		public static void state21(String str){
			Token tok = new Token(str, false, false, true, false);
			theQueue.insert(tok);
			currentState = 22;
		}
		public static void state22(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 23;
		}
		public static void state23(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 24;
		}
		public static void state24(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 25;
		}
		public static void state25(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 26;
		}
		public static void state26(String str){
			if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 23;
			}
			else if(str.equals(")")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 26;
			}
			else if(str.equals(";")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 7;
			}
		}
		public static void state27(String str){
			Token tok = new Token(str, true, false, false, false);
			theQueue.insert(tok);
			currentState = 24;
		}
		public static void state28(String str){
			if(str.equals("*")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 29;
			}
			else{
				Token tok = new Token(str, false, true, false, false);
				theQueue.insert(tok);
				currentState = 29;
			}
		}
		public static void state29(String str){
			if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 28;
			}
			else if(str.equals("FROM")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 3;
			}
		}
		public static void state30(String str){
			Token tok = new Token(str, false, true, false, false);
			theQueue.insert(tok);
			currentState = 31;
		}
		public static void state31(String str){
			if(str.equals("SET")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 32;
			}
		}
		public static void state32(String str){
			if(str.equals("(")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 33;
			}
		}
		public static void state33(String str){
			Token tok = new Token(str, false, false, false, true);
			theQueue.insert(tok);
			currentState = 34;
		}
		public static void state34(String str){
			if(str.equals(")")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 35;
			}
			else if(str.equals("/")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 33;
			}	
		}
		public static void state35(String str){
			if(str.equals(":")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 36;
			}
		}
		public static void state36(String str){
			if(str.matches(".*\\d.*")){
				Token tok = new Token(str, false, false, false, true);
				theQueue.insert(tok);
				currentState = 35;
			}
			else{
				Token tok = new Token(str, false, true, false, false);
				theQueue.insert(tok);
				currentState = 37;
			}
		}
		public static void state37(String str){
			if(str.equals("=")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 38;
			}
		}
		public static void state38(String str){
			if(str.equals("'")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 39;
			}
			else{
				Token tok = new Token(str, false, false, false, true);
				theQueue.insert(tok);
				currentState = 40;
			}
		}
		public static void state39(String str){
			Token tok = new Token(str, false, false, false, true);
			theQueue.insert(tok);
			currentState = 40;
		}
		public static void state40(String str){
			if(str.equals("'")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 40;
			}
			else if(str.equals(",")){
				Token tok = new Token(str, true, false, false, false);
				theQueue.insert(tok);
				currentState = 32;
			}
			else if(str.equals("WHERE")){
				Token tok = new Token(str, false, false, true, false);
				theQueue.insert(tok);
				currentState = 9;
			}
		}
}
	 class Token{
		String token;
		boolean isSymbol;
		boolean isID;
		boolean isKeyword;
		boolean isLiteral;
		
	public Token(String name, boolean symbol, boolean ID, boolean keyword, boolean literal){
		token = name;
		isSymbol = symbol;
		isID = ID;
		isKeyword = keyword;
		isLiteral = literal;
		System.out.println("Token Made: " + name);
		System.out.println(isSymbol);
		System.out.println(isID);
		System.out.println(isKeyword);
		System.out.println(isLiteral);
	}
	public void setName(String name){
		token = name;
	}
	public void setIsSymbol(boolean symbol){
		isSymbol = symbol;
	}
	public void setIsID(boolean ID){
		isID = ID;
	}
	public void setIsKeyword(boolean keyword){
		isKeyword = keyword;
	}
	public void setIsLiteral(boolean literal){
		isLiteral = literal;
	}
}
	
	 class Queue{
		private int maxSize;
		private Token[] tokenArray;
		private int front;
		private int back;
		private int items;
		private int finalSize;
		
		public Queue(int size){
			maxSize = size;
			tokenArray = new Token[maxSize];
			front = 0;
			back = -1;
			items = 0;
		}
		public void insert(Token t){
			if(back == maxSize - 1)
				back = -1;
			tokenArray[++back] = t;
			items++;
		}
		
		public int getSize(){
			return items;
		}
		public void reset(int i){
			tokenArray[i] = null;
			items--;
			back--;
			
		}
		public void setFinalSize(int i){
			finalSize = i;
		}
		public int getFinalSize(){
			return finalSize;
		}
	}

