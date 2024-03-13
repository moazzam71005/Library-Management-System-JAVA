import java.awt.*; //  AWT for Action Listener
import java.io.*; //  For file handling
import java.util.ArrayList; //  Arraylists
import javax.swing.*; //  SWING for GUI

public class LibraryManagementSystem { // Library Management Class
    private static Library library;

    public static void main(String[] args) { // main function
        library = new Library();
        loadDataFromFile(); // Loads data from file

        SwingUtilities.invokeLater(
            () -> createAndShowGUI()); // Starts and initializes GUI
    }

    private static void createAndShowGUI() { // GUI Function
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image =
            new ImageIcon("librifylogo.png"); // setting logo of application
        frame.setIconImage(image.getImage());

        // Set the background color to purple
        frame.getContentPane().setBackground(new Color(170, 84, 255));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create tabs
        JPanel addBookPanel = createAddBookPanel();
        JPanel addUserPanel = createAddUserPanel();
        JPanel borrowReturnPanel = createBorrowReturnPanel();
        JPanel searchPanel = createSearchPanel();
        JPanel deleteBookPanel = createDeleteBookPanel();

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Add Book", addBookPanel);
        tabbedPane.addTab("Add User", addUserPanel);
        tabbedPane.addTab("Borrow/Return", borrowReturnPanel);
        tabbedPane.addTab("Search Books", searchPanel);
        tabbedPane.addTab("Delete Book", deleteBookPanel);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        frame.setSize(1200, 700); // window dimensions
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false); // Set to be not resizable
    }

    // Add books panel
    private static JPanel createAddBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // layout of panel
        // Adding buttons & textfields
        JTextField bookIdField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JButton addButton = new JButton("Add BOOK");

        // Set the background color of the panel and labels to purple
        panel.setBackground(new Color(170, 84, 255));
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                component.setBackground(new Color(170, 84, 255));
                ((JLabel) component)
                    .setFont(new Font("Times New Roman", Font.PLAIN,
                        18)); // Set font and size
            }
        }

        // Set the background color of the button to blue
        addButton.setBackground(new Color(109, 247, 127));
        addButton.setFont(
            new Font("Times New Roman", Font.BOLD, 18)); // Set font and size

        panel.add(new JLabel("BOOK ID:"));
        panel.add(bookIdField);
        panel.add(new JLabel("TITLE:"));
        panel.add(titleField);
        panel.add(new JLabel("AUTHOR:"));
        panel.add(authorField);
        panel.add(new JLabel("GENRE:"));
        panel.add(genreField);
        panel.add(new JLabel(""));
        panel.add(addButton);

        panel.setBackground(new Color(170, 84, 255));
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(new Color(170, 84, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD,
                    20)); // Set font and size for labels
            }
        }

        // Set the background color of the button to blue
        addButton.setBackground(new Color(109, 247, 127));
        addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        // button functioning
        addButton.addActionListener(e -> {
            try {
                int bookID = Integer.parseInt(bookIdField.getText());
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();
                if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                    Book newBook = new Book(bookID, title, author, genre, true);
                    library.addBook(newBook);
                    saveDataToFile(); // Save data to file
                    JOptionPane.showMessageDialog(null,
                        "Book added successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Please fill in all fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                    "Please enter a valid Book ID.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Add user panel
    private static JPanel createAddUserPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // layout
        // components
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JButton addButton = new JButton("Add USER");

        // Set the background color of the panel and labels to purple
        panel.setBackground(new Color(170, 84, 255));
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                component.setBackground(new Color(170, 84, 255));
                ((JLabel) component)
                    .setFont(new Font("Times New Roman", Font.PLAIN,
                        18)); // Set font and size
            }
        }

        // Set the background color of the button to blue
        addButton.setBackground(new Color(109, 247, 127));
        addButton.setFont(
            new Font("Times New Roman", Font.BOLD, 18)); // Set font and size

        panel.add(new JLabel("NAME:"));
        panel.add(nameField);
        panel.add(new JLabel("CONTACT Info:"));
        panel.add(contactField);
        panel.add(new JLabel(""));
        panel.add(addButton);

        panel.setBackground(new Color(170, 84, 255));
        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(new Color(170, 84, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD,
                    20)); // Set font and size for labels
            }
        }

        // Set the background color of the button to blue
        addButton.setBackground(new Color(109, 247, 127));
        addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        // button functioning
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String contactInfo = contactField.getText();
            if (!name.isEmpty() && !contactInfo.isEmpty()) {
                int userID = library.getUsers().size() + 1; // Generate user ID
                User newUser = new User(userID, name, contactInfo);
                library.addUser(newUser);
                saveDataToFile(); // Save data to file
                JOptionPane.showMessageDialog(null, "User added successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please fill in all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Borrow / Return Panel
    private static JPanel createBorrowReturnPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // layout

        // Combobox for displaying users and book to choose
        JComboBox<User> userComboBox =
            new JComboBox<>(library.getUsers().toArray(new User[0]));
        JComboBox<Book> bookComboBox =
            new JComboBox<>(library.getBooks().toArray(new Book[0]));
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");

        // Set the background color of the panel and labels to purple
        panel.setBackground(new Color(170, 84, 255));

        // Set the background color of the buttons to blue
        borrowButton.setBackground(new Color(109, 247, 127));
        borrowButton.setFont(
            new Font("Times New Roman", Font.BOLD, 18)); // Set font and size
        returnButton.setBackground(new Color(109, 247, 127));
        returnButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

        // Adding buttons
        panel.add(new JLabel("Select USER:"));
        panel.add(userComboBox);
        panel.add(new JLabel("Select BOOK:"));
        panel.add(bookComboBox);
        panel.add(borrowButton);
        panel.add(returnButton);

        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(new Color(170, 84, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD,
                    20)); // Set font and size for labels
            }
        }
        // functioning of buttons
        borrowButton.addActionListener(e -> {
            User selectedUser = (User) userComboBox.getSelectedItem();
            Book selectedBook = (Book) bookComboBox.getSelectedItem();
            if (selectedUser != null && selectedBook != null) {
                library.borrowBook(selectedUser, selectedBook);
                saveDataToFile(); // Save data to file
                JOptionPane.showMessageDialog(null,
                    "Book borrowed successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please select a user and a book.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        returnButton.addActionListener(e -> {
            User selectedUser = (User) userComboBox.getSelectedItem();
            Book selectedBook = (Book) bookComboBox.getSelectedItem();
            if (selectedUser != null && selectedBook != null) {
                library.returnBook(selectedUser, selectedBook);
                saveDataToFile(); // Save data to file
                JOptionPane.showMessageDialog(null,
                    "Book returned successfully.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please select a user and a book.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Search Books Panel

    private static JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(4, 2)); // layout

        JTextField titleSearchField = new JTextField();
        JTextField idSearchField =
            new JTextField(); // Add a text field for searching by ID
        JTextField authorSearchField =
            new JTextField(); // Add a text field for searching by Author
        JButton searchTitleButton = new JButton("Search by TITLE");
        JButton searchIdButton =
            new JButton("Search by ID"); // Add a button for searching by ID
        JButton searchAuthorButton = new JButton(
            "Search by AUTHOR"); // Add a button for searching by Author

        // Set the background color of the panel and labels to purple
        panel.setBackground(new Color(170, 84, 255));
        // Set the background color of the buttons to blue
        searchTitleButton.setBackground(new Color(109, 247, 127));
        searchTitleButton.setFont(
            new Font("Times New Roman", Font.BOLD, 18)); // Set font and size
        searchIdButton.setBackground(new Color(109, 247, 127));
        searchIdButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        searchAuthorButton.setBackground(new Color(109, 247, 127));
        searchAuthorButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

        panel.add(new JLabel("TITLE:"));
        panel.add(titleSearchField);
        panel.add(searchTitleButton);
        panel.add(new JLabel("BOOK ID:"));
        panel.add(idSearchField);
        panel.add(searchIdButton);
        panel.add(new JLabel("AUTHOR:"));
        panel.add(authorSearchField);
        panel.add(searchAuthorButton);

        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(new Color(170, 84, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD,
                    20)); // Set font and size for labels
            }
        }
        // for displaying results
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        panel.add(resultArea);
        // adding scrollbar for viewing bigger results
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adding functioning to buttons

        searchTitleButton.addActionListener(e -> {
            String title = titleSearchField.getText();
            if (!title.isEmpty()) {
                ArrayList<Book> result = library.searchBooksByTitle(title);
                displaySearchResults(result, resultArea);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a title.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchIdButton.addActionListener(e -> {
            try {
                int bookID = Integer.parseInt(idSearchField.getText());
                Book result = searchBookById(bookID);
                if (result != null) {
                    ArrayList<Book> resultList = new ArrayList<>();
                    resultList.add(result);
                    displaySearchResults(resultList, resultArea);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "No book found with the given ID.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                    "Please enter a valid Book ID.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        searchAuthorButton.addActionListener(e -> {
            String author = authorSearchField.getText();
            if (!author.isEmpty()) {
                ArrayList<Book> result = library.searchBooksByAuthor(author);
                displaySearchResults(result, resultArea);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Please enter an author name.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Delete books panel
    private static JPanel createDeleteBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2)); // layout

        JTextField bookIdToDeleteField = new JTextField();
        JButton deleteButton = new JButton("Delete BOOK");

        // Set the background color of the panel and labels to purple
        panel.setBackground(new Color(170, 84, 255));

        // Set the background color of the button to red
        deleteButton.setBackground(new Color(255, 87, 34));
        deleteButton.setFont(
            new Font("Times New Roman", Font.BOLD, 18)); // Set font and size

        // adding components
        panel.add(new JLabel("Book ID to Delete:"));
        panel.add(bookIdToDeleteField);
        panel.add(new JLabel(""));
        panel.add(deleteButton);

        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setBackground(new Color(170, 84, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD,
                    20)); // Set font and size for labels
            }
        }

        // button functioning
        deleteButton.addActionListener(e -> {
            try {
                int bookIDToDelete =
                    Integer.parseInt(bookIdToDeleteField.getText());
                Book bookToDelete = searchBookById(bookIDToDelete);
                if (bookToDelete != null) {
                    library.getBooks().remove(bookToDelete);
                    saveDataToFile(); // Save data to file
                    JOptionPane.showMessageDialog(null,
                        "Book deleted successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "No book found with the given ID.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                    "Please enter a valid Book ID.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Search by ID function
    private static Book searchBookById(int bookID) {
        for (Book book : library.getBooks()) {
            if (book.getBookID() == bookID) {
                return book;
            }
        }
        return null;
    }

    // Displays results
    private static void displaySearchResults(
        ArrayList<Book> result, JTextArea resultArea) {
        if (result.isEmpty()) {
            resultArea.setText("No results found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Book book : result) {
                sb.append(book.toString()).append("\n");
            }
            resultArea.setText(sb.toString());
        }
    }

    // File handling to save data in file (both user and book)
    private static void saveDataToFile() {
        try (PrintWriter bookWriter =
                 new PrintWriter(new FileWriter("books.txt"));
             PrintWriter userWriter =
                 new PrintWriter(new FileWriter("users.txt"))) {
            for (Book book : library.getBooks()) {
                bookWriter.println(book.getBookID() + "," + book.getTitle()
                    + "," + book.getAuthor() + "," + book.getGenre() + ","
                    + book.isAvailable());
            }

            for (User user : library.getUsers()) {
                userWriter.println(user.getUserID() + "," + user.getName() + ","
                    + user.getContactInfo());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // to retrieve data from file
    private static void loadDataFromFile() {
        try (BufferedReader bookReader =
                 new BufferedReader(new FileReader("books.txt"));
             BufferedReader userReader =
                 new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = bookReader.readLine()) != null) {
                String[] bookData =
                    line.split(","); // splits lines with commas as they
                                     // represent different fields
                // Results are stored in arrays indices show: 0 = id, 1 = title
                // , 2 = author, 3 = availability
                int bookID = Integer.parseInt(bookData[0]);
                String title = bookData[1];
                String author = bookData[2];
                String genre = bookData[3];
                boolean availability = Boolean.parseBoolean(bookData[4]);
                library.addBook(
                    new Book(bookID, title, author, genre, availability));
            }

            while ((line = userReader.readLine()) != null) {
                String[] userData = line.split(",");
                int userID = Integer.parseInt(userData[0]);
                String name = userData[1];
                String contactInfo = userData[2];
                library.addUser(new User(userID, name, contactInfo));
            }

        } catch (FileNotFoundException e) {
            // Ignore if files are not found
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
