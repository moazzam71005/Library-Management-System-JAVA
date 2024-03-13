import java.util.ArrayList;  // Arraylists
import javax.swing.*;       //for GUI components


// Library Class
public class Library {
    private ArrayList<Book> books;  // arraylists for users and books
    private ArrayList<User> users;

    public Library() {  //setter
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    
    //getters
    public ArrayList<Book> getBooks() {
        return books;
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
   //methods
    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void borrowBook(User user, Book book) { 
        if (book.isAvailable()) {
            book.setAvailability(false);
            user.getBorrowedBooks().add(book);
        } else {
            JOptionPane.showMessageDialog(null, "Book is not available for borrowing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void returnBook(User user, Book book) {
        if (user.getBorrowedBooks().contains(book)) {
            book.setAvailability(true);
            user.getBorrowedBooks().remove(book);
        } else {
            JOptionPane.showMessageDialog(null, "Book not borrowed by the user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchBooksByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }
}
