
    
import java.util.Scanner;

//  Book Class 
class Book {
    String bookId;
    String title;
    String author;
    int year;
    boolean borrowed = false;

    Book(String bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    void display() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Borrowed: " + (borrowed ? "Yes" : "No"));
        System.out.println("----------------------");
    }
}

//  Student Class 
class Student {
    String studentId;
    String name;
    int yearOfStudy;
    int booksBorrowed = 0;

    Student(String studentId, String name, int yearOfStudy) {
        this.studentId = studentId;
        this.name = name;
        this.yearOfStudy = yearOfStudy;
    }

    void display() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Year: " + yearOfStudy);
        System.out.println("Books Borrowed: " + booksBorrowed);
        System.out.println("----------------------");
    }
}

//  Main Class 
public class LibrarySystem {

    static Scanner input = new Scanner(System.in);

    static Book[] books = new Book[50];
    static Student[] students = new Student[50];

    static int bookCount = 0;
    static int studentCount = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Student");
            System.out.println("3. Search Book");
            System.out.println("4. Search Student");
            System.out.println("5. Borrow Book");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addBook(); break;
                case 2: addStudent(); break;
                case 3: searchBook(); break;
                case 4: searchStudent(); break;
                case 5: borrowBook(); break;
                case 6: System.out.println("Program Ended."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    //  Add Book 
    static void addBook() {

        System.out.print("Enter Book ID: ");
        String id = input.nextLine();

        // Check duplicate ID
        for (int i = 0; i < bookCount; i++) {
            if (books[i].bookId.equals(id)) {
                System.out.println("Book ID already exists!");
                return;
            }
        }

        System.out.print("Enter Title: ");
        String title = input.nextLine();

        System.out.print("Enter Author: ");
        String author = input.nextLine();

        System.out.print("Enter Year: ");
        int year = input.nextInt();
        input.nextLine();

        books[bookCount] = new Book(id, title, author, year);
        bookCount++;

        System.out.println("Book added successfully!");
    }

    //  Add Student 
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        String id = input.nextLine();

        // Check duplicate ID
        for (int i = 0; i < studentCount; i++) {
            if (students[i].studentId.equals(id)) {
                System.out.println("Student ID already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Year of Study: ");
        int year = input.nextInt();
        input.nextLine();

        students[studentCount] = new Student(id, name, year);
        studentCount++;

        System.out.println("Student added successfully!");
    }

    // Search Book 
    static void searchBook() {

        System.out.print("Enter Book ID or Title: ");
        String search = input.nextLine();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].bookId.equalsIgnoreCase(search) ||
                books[i].title.equalsIgnoreCase(search)) {

                books[i].display();
                return;
            }
        }

        System.out.println("Book not found!");
    }

    //  Search Student 
    static void searchStudent() {

        System.out.print("Enter Student ID or Name: ");
        String search = input.nextLine();

        for (int i = 0; i < studentCount; i++) {
            if (students[i].studentId.equalsIgnoreCase(search) ||
                students[i].name.equalsIgnoreCase(search)) {

                students[i].display();
                return;
            }
        }

        System.out.println("Student not found!");
    }

    //  Borrow Book 
    static void borrowBook() {

        System.out.print("Enter Student ID: ");
        String sid = input.nextLine();

        Student foundStudent = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].studentId.equals(sid)) {
                foundStudent = students[i];
                break;
            }
        }

        if (foundStudent == null) {
            System.out.println("Student not found!");
            return;
        }

        if (foundStudent.booksBorrowed >= 3) {
            System.out.println("Student cannot borrow more than 3 books!");
            return;
        }

        System.out.print("Enter Book ID: ");
        String bid = input.nextLine();

        for (int i = 0; i < bookCount; i++) {
            if (books[i].bookId.equals(bid) && !books[i].borrowed) {

                books[i].borrowed = true;
                foundStudent.booksBorrowed++;

                System.out.println("Book borrowed successfully!");
                return;
            }
        }

        System.out.println("Book not available!");
    }
}
