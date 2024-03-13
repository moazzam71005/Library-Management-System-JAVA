import java.util.ArrayList;   //Arraylists
//User class
public class User {
    private int userID;
    private String name;
    private String contactInfo;
    private ArrayList<Book> borrowedBooks;
    // Constructor
    public User(int userID, String name, String contactInfo) {
        this.userID = userID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String toString() {
        return "User ID: " + userID + ", Name: " + name + ", Contact Info: " + contactInfo + ", Borrowed Books: " + borrowedBooks.size();
    }
}
