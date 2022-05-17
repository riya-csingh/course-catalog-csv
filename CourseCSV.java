package cs101_final;


public class CourseCSV implements Comparable {

	private String name; 
	private String instructor; 
	private int section; 
	private int students; 
	private String location;
	
	public CourseCSV() {
		
	}
	
	public CourseCSV(String name, String instructor, int section, int students, String location) {
		this.name = name; 
		this.instructor = instructor; 
		this.section = section; 
		this.students = students; 
		this.location = location; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getStudents() {
		return students;
	}
	public void setStudents(int students) {
		this.students = students;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	} 
	
	public void printme() {
		System.out.println("Course Name: " + this.name + " Couse Instructor: " + this.instructor 
				+ " Course Section: " + this.section + " Number of Students: " + this.students
				+ " Course Location: " + this.location);  
	}
	
	//reference: https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
	public int compareTo(CourseCSV course) {
		int comparestudents = ((CourseCSV)course).getStudents(); 
		return this.students-comparestudents; 
	}
}


/*
while ((line = bufferedReader.readLine()) != null) {
	//System.out.println(line); 
	StringTokenizer st = new StringTokenizer(line, ","); 
	
	while (st.hasMoreTokens()) {
		String example = st.nextToken(); 
		if (!example.equals("Course_Name") && !example.equals("Course_Instructor") 
				&& !example.equals("Course_Section_Number") && !example.equals("Current_Students")
				&& !example.equals("Course_Location")) {
			CourseCSV temp = new CourseCSV(); 
			
			temp.setName(st.nextToken()); 
			temp.setInstructor(st.nextToken()); 
			
			temp.setSection(st.nextToken()); 
			temp.setStudents(st.nextToken()); 
			
			temp.setLocation(st.nextToken()); 

			int a = Integer.parseInt(st.nextToken()); 
			
			schedule.add(temp); 						
		}
	}	
}
*/ 


