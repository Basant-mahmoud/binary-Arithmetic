package binary;

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.util.Map;
//import javax.awt.*;
public class Main {
public static void main(String[] args) throws IOException {
		File file1=new File("out.txt");
    	FileWriter fw=new FileWriter(file1);
    	PrintWriter pw=new PrintWriter(fw);
		String text = "";
		File file=new File("input.txt");
        try (Scanner s = new Scanner(file)) {
			     text+=s.next();
		}
        encoding obj=new encoding();
        obj.encodedData(text,fw,pw);
        ArrayList<Node> myNode = new ArrayList<Node>(3);
		myNode.add(new Node('A', 0,  0.75));
		myNode.add(new Node('B', 0.75, 1.0));
		//myNode.add(new Node('C', 0.75, 1.0));
		
		//decode constructor that take (the encoded number, the array of the characters, the smallest range)
		Decode d = new Decode("10100", myNode, 0.25);
		
		// replace it and make the code write to the file
		pw.println("Data AFter Decoding is");
		pw.println(d.decode());
		pw.close();
	}

}

