// Book Class
public class Book {
    // attributes
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean availability;
    // Constructor
    public Book(int bookID, String title, String author, String genre, boolean availability) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Getters and setters
    public int getBookID() {
        return bookID;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return availability;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }

    public String toString() {
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availability;
    }
}