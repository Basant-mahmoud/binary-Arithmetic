package binary;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
public class encoding {
	 private Map<Character, Integer> freq = new HashMap<>();
	 private Map<Character, Double> lowerbound = new HashMap<>();
	 private Map<Character, Double> upperbound = new HashMap<>();
	 ArrayList<Double> probarity=new ArrayList<Double>();
	 ArrayList<Double> arr=new ArrayList<Double>();
	// ArrayList<Node> arr2=new ArrayList<Node>();
	 String result="";
	 double smallest ;
	 public void encodedData(String text,FileWriter fw,PrintWriter pw) {
		 pw.println("Orignal data is .........");
		 pw.println(text);
	        for (char ch : text.toCharArray()) {
	            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
	        }
	        pw.println("Frequancy of each character");
	        
	        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
	            pw.println("Key = " + entry.getKey() +
	                             ", Value = " + entry.getValue());
	        }
	        pw.println("................................................");
	        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
	        	probarity.add((double)entry.getValue()/text.length());
	        	 }
	        int i=0;
	        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
	        if(i==0) {
	        	lowerbound.put(entry.getKey(),0.0);
	        	upperbound.put(entry.getKey(),probarity.get(i));
	        	arr.add(probarity.get(i));
	        	}
	        else {
	        	lowerbound.put(entry.getKey(),arr.get(i-1));
	        	upperbound.put(entry.getKey(),(probarity.get(i)+arr.get(i-1)));
	        	arr.add(probarity.get(i)+arr.get(i-1));
	        }
	        i++;
	        }	
	        pw.println("lower Bound of each character");
	        for (Map.Entry<Character, Double> entry : lowerbound.entrySet()) {
	        	pw.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
	        }
	        pw.println("................................................");
	        pw.println("upper Bound of each character");
	        for (Map.Entry<Character, Double> entry : upperbound.entrySet()) {
	        	pw.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
                  }
//	        for (Map.Entry<Character, Double> entry : lowerbound.entrySet()) {
//	        	arr2.add(new Node(entry.getKey(),entry.getValue() ,upperbound.get(entry.getKey())));	
//	        }
//	        for ( int l=0;l<arr2.size();l++) {
//	        	System.out.println(arr2.get(l));	
//	        }
	        pw.println("................................................");
	        //System.out.println(probarity);
	        Collections.sort(probarity);
	        smallest=probarity.get(0);
	        int conunter =0 ;
	        while ((1 / Math.pow(2,conunter )) >= smallest) {
	        	conunter++;
	        }
	    pw.println("number of digit we can store minimum probability is");
       pw.println(conunter);
          pw.println("smallest range is ");
	      pw.println(smallest);
	        Object low ;
	        Object up ;
	      ArrayList<Double> currentLower=new ArrayList<Double>();
	      ArrayList<Double> currentUpper=new ArrayList<Double>();
        double lower;
        double upper;
	        for(int k=0;k<text.length();k++) {
	        	if(k==0) {
	        		pw.println("iteration number"+k);
	        		low=lowerbound.get(text.charAt(k));
	        		//Math.round(num * 1000) / 1000.0;
	        		up=upperbound.get(text.charAt(k));
	        		while((double)low>0.50&&(double)up>0.50) {
	        			///make estminate...E2
	        			result+='1';
	        			//pw.println("result    :"+result);
	        			low=((double)low-0.5)*2;
	        			up=((double)up-0.5)*2;
	        	    while((double)low<0.5&&(double)up<0.5) {
	        			///make estminate...E1
	        			result+='0';
	        			//pw.println("result    :"+result);
	        			low=(Double)low*2;
	        			up=(double)up*2;
	        			
	        		}
	        	}
	        		while((double)low<0.50&&(double)up<0.50) {
	        			///make estminate...E1
	        			result+='0';
	        			//pw.println("result    :"+result);
	        			low=(Double)low*2;
	        			up=(double)up*2;
	        	    while((double)low>0.5&&(double)up>0.5) {
	        			///make estminate...E2
	        			result+='1';
	        			System.out.println("result    :"+result);
	        			low=((double)low-0.5)*2;
	        			up=((double)up-0.5)*2;
	        			
	        		}
	        		}
	        		
	        		currentLower.add((double)low);
	        		currentUpper.add((double)up);
	        		pw.println((double)low);
	        		pw.println((double)up);
	        		//pw.println("estminate    :"+result);
	        	}
	        	//////////////////////////
	        	else {
	        		pw.println("number "+k);
	        		low=lowerbound.get(text.charAt(k)); 		
	        		up=upperbound.get(text.charAt(k));
	        		pw.println((double)low);
	        		pw.println((double)up);
	        		lower=currentLower.get(k-1)+(currentUpper.get(k-1)-currentLower.get(k-1))*(double)low;
	        		upper=currentLower.get(k-1)+(currentUpper.get(k-1)-currentLower.get(k-1))*(double)up;
	        		while(lower>0.5&&upper>0.5) {
	        			///make estminate...E2
	        			result+='1';
	        			//pw.println("result    :"+result);
	        			lower=(lower-0.5)*2;
	        			upper=(upper-0.5)*2;
	        		while(lower<0.5&&upper<0.5) {
	        			///make estminate...E1
	        			result+='0';
	        			//pw.println("result    :"+result);
	        			lower=lower*2;
	        			upper=upper*2;
	        			
	        		}
	        		}
	        		while(lower<0.5&&upper<0.5) {
	        			///make estminate...E1
	        			result+='0';
	        			//pw.println("estminate    :"+result);
	        			lower=lower*2;
	        			upper=upper*2;
	        		while(lower>0.5&&upper>0.5) {
	        			///make estminate...E
	        			result+='1';
	        			//pw.println("estminate    :"+result);
	        			lower=(lower-0.5)*2;
	        			upper=(upper-0.5)*2;
	        			
	        		}
	        		}
	        		currentLower.add(lower);
	        		currentUpper.add(upper);
	        	pw.println(lower);
	        		pw.println(upper);
	        		//pw.println("estminate    :"+result);
	        	}
//	        	lower=0.0;
//	        	upper=0.0;
	        }
	        result+='1';
	        for(int nn=0;nn<conunter-1;nn++) {
	        	result+='0';
	        	
	        }
	      
	       pw.println("encoding data is..");
	      pw.println(result);
	      //pw.close();
}
}

