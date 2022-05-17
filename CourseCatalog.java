package cs101_final;

/**
 * 
 * Provide a full implementation of a Java application with the following menu: 
 * 
 *    1. Load data from the CSV file 
 *    	- read the data from a CSV file into an array list defined in the program 
 *    2. Add a new course 
 *    	- add new course to array list, ask user to enter info about course to be added 
 *    3. Edit a course 
 *    	- edit course name and instructor's name 
 *    4. Search for a course 
 *    	- search for course with a name and display all courses with the same name 
 *    		(display all info about the course (course name, location, section, ...) 
 *    5. Delete a course 
 *    6. Sort courses by number of students registered 
 *    7. Display all courses with high number of students 
 *    	- high number is above or equal to 30 students 
 *    8. Write data to a new CSV file 
 *    	- write updated array list to a file named: newdata.csv 
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class CourseCatalog {
	
	private ArrayList<CourseCSV> schedule; 
	
	//constructor of CourseCatalog class
	public CourseCatalog() {
		schedule = new ArrayList<CourseCSV>(); 
	}
	
	public static void menu() {
		System.out.println("1. Load data from CSV file"); 
		System.out.println("2. Add a new course"); 
		System.out.println("3. Edit a course"); 
		System.out.println("4. Search for a course"); 
		System.out.println("5. Delete a course"); 
		System.out.println("6. Sort courses by number of students registered"); 
		System.out.println("7. Display all courses with high number of students"); 
		System.out.println("8. Write data to a new CSV file"); 
		System.out.println("9. Exit."); 
	}
	
	public void LoadData() { 
		String fileName = "MyUniversityCourses_1_.csv"; 
		String line = null; 
		try {
			FileReader fileReader = new FileReader(fileName); 
			BufferedReader bufferedReader = new BufferedReader(fileReader); 
			while ((line = bufferedReader.readLine()) != null) {
				String[] list = new String[5]; 
				list = line.split(","); 
				int section = Integer.parseInt(list[2]); 
				int students = Integer.parseInt(list[3]); 
				schedule.add(new CourseCSV(list[0], list[1], section, students, list[4])); 
			}
			bufferedReader.close(); 
			schedule.remove(0); 
			System.out.println("Contents in Array: "); 
			for (int i=0; i<schedule.size(); i++)
				schedule.get(i).printme();  
		}
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'"); 
		}
		catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'"); 
		}	
		
	}
	
	public void Add(CourseCSV c) {
		schedule.add(c); 
	}
	
	public void Edit(String name, int section_num, int students) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName()== name && schedule.get(i).getSection() == section_num)  
				schedule.get(i).setStudents(students);
		}
	}
	
	public void Search(String name) {
		ArrayList<CourseCSV> samecourses = new ArrayList<CourseCSV>(); 
		
		for (int i=0; i < schedule.size(); i++) {
			if (schedule.get(i).getName() == name) {
				samecourses.add(new CourseCSV(schedule.get(i).getName(), schedule.get(i).getInstructor(), 
						schedule.get(i).getSection(), schedule.get(i).getStudents(), schedule.get(i).getLocation())); 
			}
		}
		System.out.println("Courses with same name: "); 
		for (int i=0; i<samecourses.size(); i++) {
			samecourses.get(i).printme(); 
		}
		
	}
	
	public int Delete(String name, int section_num) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).getName() == name && schedule.get(i).getSection() == section_num) {
				schedule.remove(i); 
				return 1; 
			}
		}
		return 0; 
	}
	
	public void SortByStudents() {
		Collections.sort(schedule);
		for (int i = 0; i < schedule.size(); i++) {
			schedule.get(i).printme(); 
		}
	}
	
	public void DisplayHighCourses() { 
		ArrayList<CourseCSV> highcourses = new ArrayList<CourseCSV>(); 
		
		for (int i=0; i<schedule.size(); i++) {
			if (schedule.get(i).getStudents() > 30) {
				highcourses.add(new CourseCSV(schedule.get(i).getName(), schedule.get(i).getInstructor(), 
						schedule.get(i).getSection(), schedule.get(i).getStudents(), schedule.get(i).getLocation())); 
			}
		}
		System.out.println("Courses with more than 30 students: "); 
		for (int i=0; i<highcourses.size(); i++) {
			highcourses.get(i).printme(); 
		}
	}
	
	public void WriteData() { 
		String fileName = "newdata.csv"; 
		try { 
			FileWriter fileWriter = new FileWriter(fileName); 
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); 
			
			for (int i=0; i<schedule.size(); i++) {
				bufferedWriter.write(schedule.get(i).getName() + " " + schedule.get(i).getInstructor() + 
						" " + schedule.get(i).getSection() + " " + schedule.get(i).getStudents() + 
						" " + schedule.get(i).getLocation());
				bufferedWriter.newLine(); 
			}
			bufferedWriter.close(); 	
		}
		catch (IOException ex) {
			System.out.println("Error writing to the file '" + fileName + "'"); 
		}
	}
	
	public static void main(String[] args) { 
		Scanner scan = new Scanner(System.in); 
		int input; 
		CourseCatalog coursecatalog = new CourseCatalog(); 
			
		do { 
			System.out.println("Choose an option: "); 
			menu(); 
			input = scan.nextInt();
			scan.nextLine(); 
			switch (input) {
			case 1: //load data
				System.out.println("Loading data from CSV file..."); 
				coursecatalog.LoadData();
				break; 
			
			case 2: //add
				System.out.println("Enter the course name: ");
				String courseinput = scan.nextLine(); 
				System.out.println("Enter the section number: "); 
				int sectioninput = scan.nextInt(); 
				System.out.println("Enter the instructor name: "); 
				String instructorinput = scan.nextLine(); 
				scan.nextLine(); 
				System.out.println("Enter the number of students: "); 
				int studentsinput = scan.nextInt(); 
				System.out.println("Enter the location: "); 
				String locationinput = scan.nextLine(); 
				scan.nextLine(); 
				
				CourseCSV course = new CourseCSV(courseinput, instructorinput, sectioninput, studentsinput, locationinput); 
				coursecatalog.Add(course); 
				System.out.println("The course has been added."); 
				break; 
				
			case 3: //edit
				System.out.println("Enter the course name: "); 
				courseinput = scan.nextLine(); 
				System.out.println("Enter the section number: "); 
				sectioninput = scan.nextInt(); 
				System.out.println("How many students are enrolled in the class now?: "); 
				studentsinput = scan.nextInt(); 
				
				coursecatalog.Edit(courseinput, sectioninput, studentsinput);
				System.out.println("The course has been edited."); 
				break; 
				
			case 4: //search for course and display all courses with same name 
				System.out.println("Enter the course name: "); 
				courseinput = scan.nextLine(); 
				coursecatalog.Search(courseinput);
				System.out.println("Displaying all courses with same name..."); 
				break;
				
			case 5: //delete course 
				System.out.println("Enter the course name: "); 
				courseinput = scan.nextLine(); 
				System.out.println("Enter the section number: "); 
				sectioninput = scan.nextInt(); 
				
				coursecatalog.Delete(courseinput, sectioninput); 
				System.out.println("The course has been deleted"); 
				break; 
				
			case 6: //sort courses
				System.out.println("Sorting courses by number of students registered..."); 
				
				coursecatalog.SortByStudents();
				System.out.println("Courses are sorted"); 
				break; 
				
			case 7: //display courses w/ >30 students 
				System.out.println("Displaying all courses with more than 30 students..."); 
				
				coursecatalog.DisplayHighCourses();
				System.out.println("Courses with more than 30 students displayed."); 
				break; 
				
			case 8: //write data to new csv file 
				System.out.println("Writing data to new CSV file..."); 
				
				coursecatalog.WriteData(); 
				System.out.println("Updated array list written to a file named: newdata.csv"); 
				break; 
				
			}
		} while (input != 9); 
		
		scan.close(); 
	}
}
