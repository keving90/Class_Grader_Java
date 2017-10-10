/*****************************************************************************************

File Name: LetterGrader.java
By: Kevin Geiszler

Description: This file contains several classes that are used in the TestLetterGrader
driver class found in the TestLetterGrader.java file. There are three classes in this file.
The Student class holds relevant information regarding each student. The SortedNames class
is used to sort each student's name alphabetically. The LetterGrader class is the main
class. It contains several methods that are used either within each other, or are used
in the TestLetterGrader.java file. The methods perform tasks such as reading the input
file, gathering the statistical information for the class, and either displaying information
to the console or writing information to the output file.

*****************************************************************************************/

import java.util.*;
import java.io.*;

/****************************************************************************************/
/****************************************************************************************/

//The Student class is used to instantiate each student's unique state. It has one method.

class Student {
	
	int id = 0;
	String fullName;
	double quiz1 = 0;
	double quiz2 = 0;
	double quiz3 = 0;
	double quiz4 = 0;
	double mid1 = 0;
	double mid2 = 0;
	double finalExam = 0;
	double classScore = 0;
	char letterGrade;
	
	//Constructor
	public Student(int id) {
		this.id = id;
	}

//Student class methods:

//The getStudentName() method is used to return the String that represents the student's
//full name. It is used for sorting purposes in the SortedNames class.

//Return type: String
		
	public String getStudentName() {
		return fullName;
	}
} //End of Student class

/****************************************************************************************/
/****************************************************************************************/

//The SortedNames class is used to sort the ArrayList that holds each student's information.
//This class implements Comparator the comparator interface.
//The compare method is used to compare the string representing each student's name for
//sorting. Its return value is type int.

class SortedNames implements Comparator<Student> {

	public int compare(Student s1, Student s2) {
		String str1 = ((Student)s1).getStudentName();
		String str2 = ((Student)s2).getStudentName();
		
		return (str1.compareTo(str2));
	}
} //End of SortedNames class

/****************************************************************************************/
/****************************************************************************************/

//The LetterGrader class is the main class. It gathers the information for each student,
//and stores this information into an ArrayList. A grade is determined for each student, 
//as well as the overall average, maximum, and minimum scores for each exam. The class
//statistics are printed to the console, and each student's grade is printed to a file
//next to their name. These names are sorted alphabetically in the file.

public class LetterGrader {

	//Instance variables:
	
	String arg1, arg2, fullName;
	
	//Set the max score of each exam to a low amount so it can be beaten
	double max[] = {-100, -100, -100, -100, -100, -100, -100};
	
	//Set the min score of each exam to a high amount so there will be exams with a lower score
	double min[] = {1000, 1000, 1000, 1000, 1000, 1000, 1000};
	
	double average[] = {0, 0, 0, 0, 0, 0, 0};
	int nextToken;
	String strToken;
	double numberToken;
	
	//Used to count and record the number of students in the class
	int count = 0;
	
	File inputFile = null;
	File outputFile = null;
	
	FileReader fileStream = null;
	BufferedReader myReader = null;
	StreamTokenizer myTokenizer = null;
	PrintStream sendOut = null;
	
	//This ArrayList of type Student will hold an object representing each student's info
	ArrayList<Student> classList = new ArrayList<Student>();
	
	//Constructor
	public LetterGrader(String arg1, String arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

//LetterGrader class methods:	
/****************************************************************************************/

//The readScores() method opens the input file, reads each line, gathers each student's
//information, and instantiates an object based on this information. A new object is
//created for each student, and each object is placed in an ArrayList.

//Return type: void
	
	public void readScores() {
	
		inputFile = new File(this.arg1);
		
		try {
			fileStream = new FileReader(inputFile);
			myReader = new BufferedReader(fileStream);
			myTokenizer = new StreamTokenizer(myReader);
			myTokenizer.whitespaceChars(',', ',');
			myTokenizer.wordChars(' ', ' ');	
		
			nextToken = myTokenizer.nextToken();
			
			while (nextToken != StreamTokenizer.TT_EOF) {
				
				//Add an instance of the Student class to the array list.
				//The class is instantiated based setting the student's ID number (starting at 0)
				classList.add(new Student(count));
				
				classList.get(count).fullName = myTokenizer.sval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).quiz1 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).quiz2 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).quiz3 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).quiz4 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).mid1 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).mid2 = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				classList.get(count).finalExam = myTokenizer.nval;
				nextToken = myTokenizer.nextToken();
				
				count++;
			} //while
		} //try
		catch (IOException e) {
			System.out.println("Error reading file: " + this.arg1);
		} //catch
	} //readScores()
	
/****************************************************************************************/

//The getMaxScores() method gathers the max score of each student for each exam in order
//to find the highest score for each exam in the class. This method is used inside the
//getClassStats() method.

//Return type: void
	
	void getMaxScores() {
	
		for (int i = 0; i < count; i++) {
		
			if (classList.get(i).quiz1 > max[0])
				max[0] = classList.get(i).quiz1;
				
			if (classList.get(i).quiz2 > max[1])
				max[1] = classList.get(i).quiz2;
				
			if (classList.get(i).quiz3 > max[2])
				max[2] = classList.get(i).quiz3;
				
			if (classList.get(i).quiz4 > max[3])
				max[3] = classList.get(i).quiz4;
				
			if (classList.get(i).mid1 > max[4])
				max[4] = classList.get(i).mid1;
				
			if (classList.get(i).mid2 > max[5])
				max[5] = classList.get(i).mid2;
				
			if (classList.get(i).finalExam > max[6])
				max[6] = classList.get(i).finalExam;
		}
	} //getMaxScores()
	
/****************************************************************************************/

//The getMinScores() method gathers the min score of each student for each exam in order
//to find the lowest score for each exam in the class. This method is used inside the
//getClassStats() method.

//Return type: void	

	void getMinScores() {
	
		for (int i = 0; i < count; i++) {
		
			if (classList.get(i).quiz1 < min[0])
				min[0] = classList.get(i).quiz1;
				
			if (classList.get(i).quiz2 < min[1])
				min[1] = classList.get(i).quiz2;
				
			if (classList.get(i).quiz3 < min[2])
				min[2] = classList.get(i).quiz3;
				
			if (classList.get(i).quiz4 < min[3])
				min[3] = classList.get(i).quiz4;
				
			if (classList.get(i).mid1 < min[4])
				min[4] = classList.get(i).mid1;
				
			if (classList.get(i).mid2 < min[5])
				min[5] = classList.get(i).mid2;
				
			if (classList.get(i).finalExam < min[6])
				min[6] = classList.get(i).finalExam;
		}
	} //getMinScores()
	
/****************************************************************************************/

//The getMinScores() method gathers the total score of each student for each exam and
//calculates the average score for each student based on the number of students in the
//class. This method is used inside the getClassStats() method.

//Return type: void	
	
	void getAverageScores() {
	
		for (int i = 0; i < count; i++) {
			average[0] += classList.get(i).quiz1;
			average[1] += classList.get(i).quiz2;
			average[2] += classList.get(i).quiz3;
			average[3] += classList.get(i).quiz4;
			average[4] += classList.get(i).mid1;
			average[5] += classList.get(i).mid2;
			average[6] += classList.get(i).finalExam;
		}
		
		for (int j = 0; j < 7; j++) {
			average[j] = average[j] / count;
		}
	} //getScores()
	
/****************************************************************************************/
	
//The getClassStats() method uses getMaxScores(), getMinScores(), and getAverageScores()
//to calculate these class statistics and display them to the console.

//Return value: void
	
	void getClassStats() {
	
		getMaxScores();
		getMinScores();
		getAverageScores();	
		
		System.out.println("A letter grade has been calculated for all students " +
				   "listed in the input file (" + this.arg1 + "), and written " +
				   "to the output file (" + this.arg2 + ").");
		
		System.out.printf("\nHere are the statistics for the class:\n\n");
		System.out.printf("\t\tQ1\tQ2\tQ3\tQ4\tMid1\tMid2\tFinal\n");
		
		//Print average for each quiz or test
		System.out.printf("Average:\t%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\t%5.2f\n",
				average[0], average[1], average[2], average[3], average[4], average[5], average[6]);
	
		//Print maximum scores
		System.out.printf("Maximum:\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\n",
								  max[0], max[1], max[2], max[3], max[4], max[5], max[6]);
		
		//Print minimum scores
		System.out.printf("Minimum:\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\t%.0f\n\n",
								  min[0], min[1], min[2], min[3], min[4], min[5], min[6]);
		
	} //getClassStats()
	
/****************************************************************************************/
	
//The calculateLetterGrade() returns a letter grade based on a score. It is used in the
//calculateClassScore() method.

//Return value: char
	
	char calculateLetterGrade(double score) {
	
		if (score >= 90)
			return 'A';
		
		else if (score < 90 & score >= 80)
			return 'B';
			
		else if (score < 80 & score >= 70)
			return 'C';
			
		else if (score < 70 & score >= 60)
			return 'D';
			
		else
			return 'F';
	} //calculateLetterGrade()
	
/****************************************************************************************/

//The calculateClassScore() method calculates the overall grade for each student in the
//class based on the weights of each exam, and uses the calculateLetterGrade() method
//to assign a letter grade based on the class score.

//Return type: void

	void calculateClassScore() {
	
		for (int i = 0; i < count; i++) {
			classList.get(i).classScore = classList.get(i).quiz1	  * .10 +
										  classList.get(i).quiz2	  * .10 +
										  classList.get(i).quiz3   	  * .10 +
										  classList.get(i).quiz4	  * .10 +
										  classList.get(i).mid1  	  * .20 +
										  classList.get(i).mid2  	  * .15 +
										  classList.get(i).finalExam  * .25;
										  
			classList.get(i).letterGrade = calculateLetterGrade(classList.get(i).classScore);
		}
	} //calculateClassScore()
	
/****************************************************************************************/

//The printScores() method prints each student's name, along with their letter grade for
//the class, to the output file.

//Return type: void
	
	public void printScores() {
	
		try {
			outputFile = new File(this.arg2);
			sendOut = new PrintStream(outputFile);
			
			Collections.sort(classList, new SortedNames());
			for (int i = 0; i < count; i++) {
				//Append a colon to the end of each studen't name
				classList.get(i).fullName += ":";
				sendOut.printf("%-25s %c\n", classList.get(i).fullName, classList.get(i).letterGrade);
			} //for
		} //try
		catch (IOException e) {
			System.out.println("Error writing to file: " + this.arg1);
		} //catch
	} //printScores()
	
/****************************************************************************************/

//The cleanUp() method closes any lingering resources at the end of the program.

//Return type: void

	public void cleanUp() {
		try {
			fileStream.close();
			myReader.close();
			sendOut.close();
		}
		catch (IOException e) {
			System.out.println("Error closing resources.");
		}
	} //cleanUp()

/****************************************************************************************/

} // End of LetterGrader class

/****************************************************************************************/
/****************************************************************************************/
























