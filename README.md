# Library-System---Java-
To design and implement a Library Management System using Java. The system allows users (members) to borrow and return books efficiently. This project demonstrates core Object-Oriented Programming concepts such as: • Encapsulation • Composition • Class and Object • Method Implementation • Data Abstraction
📚 Library Management System (Java OOP Project)

A Console-Based Library Management System built using Java and Object-Oriented Programming (OOP) concepts.
This project demonstrates real-world usage of Encapsulation, Classes, Objects, and Composition (HAS-A relationship).

🚀 Features
📖 Add and manage books
👤 Add library members
🔄 Borrow books
🔁 Return books
📚 View available books
👤 View borrowed books per member
❌ Input validation for invalid IDs
🧠 OOP Concepts Used
Encapsulation → Private fields with getters/setters
Composition (HAS-A Relationship) → Library HAS Books & Members
Class Design → Separate classes for Book, Member, Library
Data Abstraction → Internal logic hidden from user
Collections Framework → ArrayList used for storage
🏗️ Project Structure
Book → Represents a book entity
Member → Represents a library user
Library → Manages books & members (core logic)
LibrarySystem → Main class with menu-driven UI
💻 How to Run
Save the file as:
LibrarySystem.java
Compile:
javac LibrarySystem.java
Run:
java LibrarySystem
🧾 Sample Menu
📚 LIBRARY MANAGEMENT SYSTEM
1. Show Books
2. Borrow Book
3. Return Book
4. Show Member Books
5. Exit
📌 Full Source Code
import java.util.*;

// ===============================
// BOOK CLASS (Encapsulation)
// ===============================
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() { isAvailable = false; }
    public void returnBook() { isAvailable = true; }

    public String toString() {
        return id + " | " + title + " | " + author + " | " +
               (isAvailable ? "Available" : "Not Available");
    }
}

// ===============================
// MEMBER CLASS (Encapsulation)
// ===============================
class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrow(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void showBorrowedBooks() {
        System.out.println("📚 Borrowed Books of " + name);
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book b : borrowedBooks) {
                System.out.println(b);
            }
        }
    }
}

// ===============================
// LIBRARY CLASS (COMPOSITION)
// ===============================
class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public void showBooks() {
        System.out.println("\n📚 Library Books:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public Member findMember(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }

    public void borrowBook(int memberId, int bookId) {
        Member m = findMember(memberId);
        Book b = findBook(bookId);

        if (m == null || b == null) {
            System.out.println("❌ Invalid member or book ID");
            return;
        }

        if (!b.isAvailable()) {
            System.out.println("❌ Book already borrowed");
            return;
        }

        b.borrowBook();
        m.borrow(b);
        System.out.println("✅ Book borrowed successfully!");
    }

    public void returnBook(int memberId, int bookId) {
        Member m = findMember(memberId);
        Book b = findBook(bookId);

        if (m == null || b == null) {
            System.out.println("❌ Invalid member or book ID");
            return;
        }

        b.returnBook();
        m.returnBook(b);
        System.out.println("✅ Book returned successfully!");
    }
}

// ===============================
// MAIN CLASS
// ===============================
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book(1, "Java Basics", "James Gosling"));
        library.addBook(new Book(2, "OOP Concepts", "Bjarne Stroustrup"));
        library.addBook(new Book(3, "Data Structures", "Robert Lafore"));

        library.addMember(new Member(101, "Shalini"));
        library.addMember(new Member(102, "Arjun"));

        while (true) {
            System.out.println("\n============================");
            System.out.println("📚 LIBRARY MANAGEMENT SYSTEM");
            System.out.println("============================");
            System.out.println("1. Show Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show Member Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.showBooks();
                case 2 -> {
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.borrowBook(mid, bid);
                }
                case 3 -> {
                    System.out.print("Enter Member ID: ");
                    int rm = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int rb = sc.nextInt();
                    library.returnBook(rm, rb);
                }
                case 4 -> {
                    System.out.print("Enter Member ID: ");
                    int mm = sc.nextInt();
                    Member m = library.findMember(mm);
                    if (m != null) m.showBorrowedBooks();
                    else System.out.println("❌ Member not found");
                }
                case 5 -> {
                    System.out.println("👋 Exiting... Bye!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}
<img width="1912" height="807" alt="1" src="https://github.com/user-attachments/assets/d95bcadd-36bc-439f-9ba4-01e3156671be" />

<img width="1918" height="802" alt="2" src="https://github.com/user-attachments/assets/fce9ff8a-8f77-4e8f-86c1-d7d526bc8b76" />

<img width="1918" height="807" alt="3" src="https://github.com/user-attachments/assets/6e435297-6bdf-41e4-9023-2017e5b884e7" />

⭐ Future Improvements
File-based storage (save data permanently)
GUI version using Swing/JavaFX
Search books by title/author
Fine system for late returns
Admin login system
