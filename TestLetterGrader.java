/*****************************************************************************************

File Name: TestLetterGrader.java
By: Kevin Geiszler

Description: The file contains the driver class known as TestLetterGrader. It creates an
instance of the LetterGrader class and uses various methods to gather student information
for a university class from an input file, calculate statistics for the class, determine
a grade, display information to the console, and write information to the output file.

*****************************************************************************************/

import java.util.*;
import java.io.*;

/****************************************************************************************/

public class TestLetterGrader {
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
		
			System.out.println("Error, need the following file usage: TestLetterGrader " +
							   "inputFileName outputFileName");
			System.exit(2);
			
		} else {
			
			System.out.println("Input will be read from: " + args[0]);
			System.out.println("Output will be written to: " + args[1]);
			System.out.println();
			
		}
		
		LetterGrader classResults = new LetterGrader(args[0], args[1]);
		
		classResults.readScores();
		classResults.getClassStats();
		classResults.calculateClassScore();
		classResults.printScores();
		classResults.cleanUp();
	}
}

/****************************************************************************************/