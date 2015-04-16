import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class SortingLab11_Matthew_Pearce {
	public static void main(String args[]) throws IOException {
		List studentArray = new List(100);
		studentArray.getList();
		studentArray.display("UNSORTED LIST OF STUDENTS");
		studentArray.pause();

		studentArray.gpaSort();
		studentArray.display("STUDENTS SORTED IN DESCENDING ORDER BY GPA");
		studentArray.pause();

		studentArray.ageSort();
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY AGE");
		studentArray.pause();

		studentArray.idSort();
		studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID#");
		studentArray.pause();

		int studentID = getID();
		int index = studentArray.search(studentID);

		if (index == -1)
			System.out.println("There is no student with an ID# of "
					+ studentID + ".\n");
		else {
			studentArray.displayStudent(index); // displays the information
												// forthe found student
			studentArray.delete(index); // remove the same student from the
										// array
			studentArray
					.display("STUDENTS SORTED IN ASCENDING ORDER BY ID# WITHOUT STUDENT# "
							+ studentID);
			studentArray.pause();
		}
	}

	public static int getID() {
		Scanner input = new Scanner(System.in);
		System.out
				.print("\nEnter the 6-digit ID of the student.  { 100000 - 999999 }  -->  ");
		return input.nextInt();
	}
}

class Person {
	public String name;
	public int id;
	public int age;
	public double gpa;

	Person(String n, int ID, int a, double g) {
		name = n;
		id = ID;
		age = a;
		gpa = g;
	}

	public double getGpa() {
		return gpa;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;

	}
	public String getName(){
		return name;
		
	}
}

class List {
	private Person student[]; // stores array elements
	private int capacity; // actual array capacity
	private int size; // number of elements in the array

	public List(int c) {
		capacity = c;
		size = 0;
		student = new Person[capacity];
	}

	public void getList() throws IOException {
		FileReader inFile = new FileReader("students2.dat");
		BufferedReader inStream = new BufferedReader(inFile);
		String s1, s2, s3, s4;
		int age, id;
		double gpa;
		int index = 0;
		while (((s1 = inStream.readLine()) != null)
				&& ((s2 = inStream.readLine()) != null)
				&& ((s3 = inStream.readLine()) != null)
				&& ((s4 = inStream.readLine()) != null)) {
			String name = s1;
			id = Integer.parseInt(s2);
			age = Integer.parseInt(s3);
			gpa = Double.parseDouble(s4);

			student[index] = new Person(name, id, age, gpa);
			index++;
		}
		inStream.close();
		size = index;
	}

	private String spaces(String name) {
		int tab = 24 - name.length();
		String temp = "";
		for (int j = 1; j <= tab; j++)
			temp += " ";
		return temp;
	}

	public void display(String listInfo) {
		DecimalFormat output = new DecimalFormat("0.000");
		System.out.println("\nDISPLAYING " + listInfo);
		System.out
				.println("\nStudent ID#     Student Name            Age         GPA");
		System.out
				.println("============================================================");

		for (int k = 0; k < size; k++)
			System.out.println(student[k].id + "          " + student[k].name
					+ spaces(student[k].name) + student[k].age + "          "
					+ output.format(student[k].gpa));
	}

	public void pause() {
		Scanner input = new Scanner(System.in);
		String dummy;
		System.out.println("\nPress <Enter> to continue.");
		dummy = input.nextLine();
	}

	public void displayStudent(int index) {

		if (student[index] != null);
		System.out.println("\nStudent Record for ID #" + student[index].getId() + "\nName: " + student[index].getName() + "\nAge: " + student[index].getAge() + "\nGpa: " + student[index].getGpa());
	}

	private void swap(int x, int y) {

		Person temp = student[x];
		student[x] = student[y];
		student[y] = temp;
	}

	public void gpaSort() {

		boolean sorted;
		int p = 1;
		do {
			sorted = true;
			for (int q = 0; q < size - p; q++)
				if (student[q].getGpa() < student[q + 1].getGpa()) {
					swap(q, q + 1);
					sorted = false;
				}
			p++;
		} while (!sorted);
	}

	public void ageSort() {

		boolean sorted;
		int p = 1;
		do {
			sorted = true;
			for (int q = 0; q < size - p; q++)
				if (student[q].getAge() > student[q + 1].getAge()) {
					swap(q, q + 1);
					sorted = false;
				}
			p++;
		} while (!sorted);
	}

	public void idSort() {

		boolean sorted;
		int p = 1;
		do {
			sorted = true;
			for (int q = 0; q < size - p; q++)
				if (student[q].getId() > student[q + 1].getId()) {
					swap(q, q + 1);
					sorted = false;
				}
			p++;
		} while (!sorted);
	}

	public int search(int studentID) {

		boolean found = false;
		int lo = 0;
		int hi = size - 1;
		int mid = 0;
		while (lo <= hi && !found) {
			mid = (lo + hi) / 2;
			if (student[mid].getId() == studentID)
				found = true;
			else {
				if (studentID > student[mid].getId())
					lo = mid + 1;
				else
					hi = mid - 1;
			}
		}
		if (found)
			return mid;
		else
			return -1;
	}

	public void delete(int index) {
		// Precondition: "index" stores the index of a student object that
		// exists in the "student" array.
		// Postcondition: The student object at index "index" is removed from
		// the "student" array.
		// All other objects in the "student" array are unaffected.
		
		int location = search(index);
		student[index] = null;
		Person[] tempArray = null; 
		System.arraycopy(student, 0, tempArray, 0, student.length );
		

	}

}
