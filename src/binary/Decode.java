package binary;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
public class Decode {

	private String encodedNumber;
	private ArrayList<Node> myChars = new ArrayList<Node>();
	private double smallestRange;
	private ArrayList<Double> myRanges = new ArrayList<Double>(2);
	public Decode(String encodedNumber, ArrayList<Node> myChars, double smallestRange) {
		this.encodedNumber = encodedNumber;
		this.myChars = myChars;
		this.smallestRange = smallestRange;
	}
	
	
	String decode() {
		
		String myDecodedString = "", subStr = "";
		int k = 0;
		int targetToK = (int) (1/smallestRange);
		
		
		for(int i=0;i<encodedNumber.length();i++)
		{
			if(((int)(Math.pow(2, i))) > targetToK ) {
				k = i;
				break;
			}
		}
		
		int startSubString = 0;
		
		subStr = encodedNumber.substring(startSubString, startSubString+k);
		double codeToCheckInRange = calculateBinarySubString(subStr);
		char charToAddToDecodedString = findChar(codeToCheckInRange);
		
		myDecodedString += charToAddToDecodedString;
		
		for (Node node : myChars) {
			if(node.getChar() == charToAddToDecodedString) {
				myRanges.add(node.getLowerBound());
				myRanges.add(node.getUpperBound());
			}
		}
		int res = 0;
		int cn = -1;
		do {
			
			res = fixRange(myRanges);
			cn++;
			
		}while(res != -1);
		
		if(cn > 0) {

			if(startSubString+k < encodedNumber.length())
				subStr = encodedNumber.substring(startSubString,startSubString+k);
			else
				subStr = encodedNumber.substring(startSubString,startSubString+k-1);
			
			codeToCheckInRange = calculateBinarySubString(subStr);
		}
		

		do {

			codeToCheckInRange = (codeToCheckInRange - myRanges.get(0))/(myRanges.get(1) - myRanges.get(0));
			charToAddToDecodedString = findChar(codeToCheckInRange);
			myDecodedString += charToAddToDecodedString;

			double newLR = 0.0,newUR = 0.0;
			
			for (Node node : myChars) {
				if(node.getChar() == charToAddToDecodedString) {

					newLR =(myRanges.get(0)+ (myRanges.get(1) - myRanges.get(0)) )* node.getLowerBound() ;
					newUR= (myRanges.get(0)+ (myRanges.get(1) - myRanges.get(0)) )* node.getUpperBound() ;
				}
			}
			
			myRanges.removeAll(myRanges);
			
			myRanges.add(newLR);
			myRanges.add(newUR);
			

			int result = 0,cnt=-1;
			do {
					
				result = fixRange(myRanges);
				cnt++;
				
			}while(result != -1);

			if(cnt > 0) {
				startSubString +=cnt;
				
				if(startSubString+k < encodedNumber.length()) {
					subStr = encodedNumber.substring(startSubString,startSubString+k);
					
				}
				else {
					subStr = encodedNumber.substring(startSubString,startSubString+k-1);
				}
				
				codeToCheckInRange = calculateBinarySubString(subStr);
				
			}
			
		}while(codeToCheckInRange != 0.5);
		
		
		codeToCheckInRange = (codeToCheckInRange - myRanges.get(0))/(myRanges.get(1) - myRanges.get(0));
		charToAddToDecodedString = findChar(codeToCheckInRange);
		myDecodedString += charToAddToDecodedString;
		
		
		return myDecodedString;	
	}
	
	 int fixRange(ArrayList<Double> myRanges) {

		double lB = myRanges.get(0);
		double uB = myRanges.get(1);
		
		myRanges.removeAll(myRanges);
		
		int tyypeOfFixed = -1;
		
		if(lB < 0.5 && uB < 0.5) {
			lB *= 2;
			uB *= 2;
			tyypeOfFixed = 0;
		}
		else if(lB > 0.5 && uB > 0.5) {
			lB = (lB-0.5)*2;
			uB = (uB-0.5)*2;
			tyypeOfFixed = 1;
		}
		
		myRanges.add(lB);
		myRanges.add(uB);
		
		return tyypeOfFixed;
		
	}
	 
	 double calculateBinarySubString(String subString) {
		 
		 int upperCode = 0;
		 
		 ArrayList<Character> subs = new ArrayList<Character>();
		 
		 for(int i=0;i<subString.length();i++) {
			 subs.add(subString.charAt(i));
		 }
		 
		 
		 for(int i=subs.size()-1;i>=0;i--) {
			 if(subs.get(i) == '1')
				 break;
			 else
				 subs.remove(i);
		 }
		 
		 subString = "";
		 
		 for(int i=0;i<subs.size();i++)
		 {
			 subString+=subs.get(i);
		 }
		 
		 
		 int lowerCode = (int) (Math.pow(2, subString.length()));
		 
		 for(int i=subString.length()-1,j=0;i>=0;i--,j++) {
			 if(subString.charAt(i) == '1') {
				 upperCode += (int) (Math.pow(2,j));
			 }
		 }
		 return ((double)upperCode)/lowerCode;
		 
	 }
	 
	 char findChar(double code) {
		 for (Node node : myChars) {
			 if( node.getLowerBound()<=code && node.getUpperBound()>=code) {
				 return node.getChar();
			 }
		}
		 return '0';
	 }

}



