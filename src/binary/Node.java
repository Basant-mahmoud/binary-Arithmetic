package binary;
public class Node{
	
	private char myChar;
	private double lowerBound, upperBound;
	
	Node(char myChar, double lowerBound, double upperBound){
		this.myChar = myChar;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	char getChar() {
		return myChar;
	}
	
	double getLowerBound() {
		return lowerBound;
	}
	
	double getUpperBound() {
		return upperBound;
	}
	
}
