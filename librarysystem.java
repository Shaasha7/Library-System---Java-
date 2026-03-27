keep the pdf formagt as same and change the information according to the code i m giving below 
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

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

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

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
// Library HAS-A Books + Members
// ===============================
class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add member
    public void addMember(Member member) {
        members.add(member);
    }

    // Show books
    public void showBooks() {
        System.out.println("\n📚 Library Books:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Find book
    public Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    // Find member
    public Member findMember(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }

    // Borrow book
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

    // Return book
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
// MAIN CLASS (MENU SYSTEM)
// ===============================
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample Data
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
                case 1:
                    library.showBooks();
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    library.borrowBook(mid, bid);
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    int rm = sc.nextInt();
                    System.out.print("Enter Book ID: ");
                    int rb = sc.nextInt();
                    library.returnBook(rm, rb);
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    int mm = sc.nextInt();
                    Member m = library.findMember(mm);
                    if (m != null) m.showBorrowedBooks();
                    else System.out.println("❌ Member not found");
                    break;

                case 5:
                    System.out.println("👋 Exiting... Bye!");
                    return;

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}