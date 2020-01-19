import java.io.*;
import java.util.*;
/**
 * class Main, 
 * This class implements a library database. It is the top level class 
 * in this project. The library database communicates via text input/output 
 * in the text terminal.
 * 
 * This class uses an object of class Scanner to read input from the user and from a file.
 * And outputs data to a file using an object of the Printwriter class.
 * It contains a loop that repeatedly reads input and then adds that input data 
 * to the database or displays data from database to the console as requested, until the users wants to 
 * leave.
 * 
 * @author Andrew Megson 
 * @version 11/12/18
 */
public class LibraryIO
{
    private Scanner inFile; // = new Scanner(new FileReader("H:\\csc8001\\data.txt"));
    private Scanner k; // = new Scanner(System.in);
    private Library library; // = new Library();
    private PrintWriter outFile; // = new PrintWriter("H:\\csc8001\\results.txt");            

    /**
     * Creates an input/output user interface so the library database can be used.
     */
    public LibraryIO() throws FileNotFoundException
    {
        inFile = new Scanner(new FileReader("H:\\CSC8001 Programming\\cw2inFile.txt"));
        k = new Scanner(System.in);
        library = new Library();
        outFile = new PrintWriter("H:\\CSC8001 Programming\\cw2outFile.txt");

    }

    public static void main(String[] args) throws FileNotFoundException
    {
        LibraryIO myLibrary = new LibraryIO();

        myLibrary.startProgram();

    }

    /**
     * This is the main method of the program it operates a loop that carries out the different function available in the program 
     * (using the data input and the data stored in the library object), 
     * until the user ends the program by entering 'exit'.
     *
     *
     */
    public void startProgram()
    {
        //Scanner k = new Scanner(System.in);

        //Scanner inFile = new Scanner(new FileReader("H:\\csc8001\\data.txt"));

        //PrintWriter outFile = new PrintWriter("H:\\csc8001\\results.txt");

        //String inputLine = reader.nextLine().trim().toLowerCase();
        //Library library = new Library();
        //         SortedArrayList<Book> Books = new SortedArrayList<>();
        //         SortedArrayList<User> Users = new SortedArrayList<>();

        //scanner in populate arraylists

        readInfo();

        printMenu();
        char ch = k.next().charAt(0);
        k.nextLine();
        while (ch != 'f')
        {
            switch(ch)
            {
                case 'b': allBookInfo();
                break;

                case 'u': allUserInfo();
                break;

                case 'i': bookIssued();
                break;

                case 'r': bookReturned();
                break;

                default: System.out.println("Invalid entry, try again");
            }
            printMenu();
            ch = k.next().charAt(0);
            k.nextLine();
        }
        outFile.close();
    }

    /**
     * method to read all the info from the file.
     */
    public void readInfo()
    {
        int x = Integer.parseInt(inFile.next().trim().toLowerCase());
        inFile.nextLine();

        //loop x times
        for (int i=1; i<=x; i++)
        {
            Book b = new Book();
            b.setTitle(inFile.nextLine().trim().toLowerCase());

            String authorFullName = inFile.nextLine().trim().toLowerCase();
            String[] nameArray = authorFullName.split(" "); //split at spaces

            b.setAuthSurname(nameArray[nameArray.length-1]);
            b.setAuthFullName(authorFullName);

            library.getBooks().add(b);

        }

        int y = Integer.parseInt(inFile.next().trim().toLowerCase());
        inFile.nextLine();

        //loop y times
        for (int i=1; i<=y; i++)
        {
            User u = new User();

            String fullName = inFile.nextLine().trim().toLowerCase();
            String[] nameArray = fullName.split(" ");

            u.setFirstname(nameArray[0]);
            u.setSurname(nameArray[1]);

            library.getUsers().add(u);

        }

    }

    /**
     * method to print out info on all the Books.
     */
    public void allBookInfo()
    {
        System.out.println("Here are all the books currently in our library;");
        System.out.println();

        for(Book b : library.getBooks())
        {

            System.out.println(b);

        }

    }

    /**
     * method to print out info on all the Users.
     */
    public void allUserInfo()
    {
        System.out.println("Here are all the current Library users;");
        System.out.println();

        for(User u :library.getUsers())
        {
            System.out.println(u);
        }
    }

    /**
     * method to issue a book and update all info in book and user sorted arraylists accordingly.
     */
    public void bookIssued()
    {
        System.out.println("Please enter the name of the user requesting to borrow a book,"); 
        System.out.println("first name + surname and press enter.");

        String name1 = k.next().trim().toLowerCase();
        String name2 = k.next().trim().toLowerCase();
        k.nextLine();
        //valid user    
        if (!library.validUser(name1, name2))
        {
            System.out.println("I'm afraid this person is not currently a member of the library.");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;

        }

        //allowed to borrow

        if (!library.allowedToBorrow(name1, name2))
        {
            System.out.println("This user already the maximum amount of books allowed on loan, and must return one before loaning another.");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;
        }

        //valid book
        System.out.println("Please enter the name of the book requested, and press enter.");
        String title = k.nextLine().trim().toLowerCase();

        System.out.println("Please enter the full name of the author for the book requested, and press enter.");
        String authFullname = k.nextLine().trim().toLowerCase();

        if (!library.validBook(title, authFullname))
        {
            System.out.println("I'm afraid that book isn't in our library.");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;

        }

        //book already on loan
        if (library.currentLoanStatus(title, authFullname))
        {
            if (library.getBorrowerName(title, authFullname).equals(name1 + " " + name2))  // user requesting book already has that book on loan  
            {
                System.out.println("I'm afraid this user already has that book out on loan!");  
                System.out.println("(request cancelled, please select another option from the menu below.)"); 
                return;
            }

            else {
                System.out.println("I'm afraid that book is currently out on loan, a request to return the book has been sent to the current user who has it on loan.");
                System.out.println("(request cancelled, please select another option from the menu below.)");

                outFile.println("Hello " + library.getBorrowerName(title, authFullname)+ ", '" + title + "' currently in your possesion has been requested by another user.");        
                outFile.println("I'd like to politely ask that you return it as soon as possible.");
                outFile.println("Many thanks.");
                outFile.println();
                return;
            }
        }

        
        //issue book
        library.loanBook(name1, name2, title, authFullname);

        System.out.println("Thank you the request to loan " + title + " has been sucessful, and our records have been updated accordingly."); 

    }

    /**
     * method to return a book and update all info in book and user sorted arraylists accordingly.
     */
    public void bookReturned()
    {
        System.out.println("Please enter the name of the user returning a book,"); 
        System.out.println("first name + surname and press enter.");

        String name1 = k.next().trim().toLowerCase();
        String name2 = k.next().trim().toLowerCase();
        k.nextLine();
        //valid user    
        if (!library.validUser(name1, name2))
        {
            System.out.println("I'm afraid this person is not currently a member of the library.");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;

        }

        //valid book
        System.out.println("Please enter the name of the book being returned, and press enter.");
        String title = k.nextLine().trim().toLowerCase();

        System.out.println("Please enter the full name of the author for the book requested, and press enter.");
        String authFullname = k.nextLine().trim().toLowerCase();

        if (!library.validBook(title, authFullname))
        {
            System.out.println("I'm afraid that book isn't in our library.");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;

        }

        //correct borrower returning book

        if (!(library.getBorrowerName(title, authFullname).equals(name1+ " " + name2)))
        {
            System.out.println("I'm afraid that " + name1 + " " + name2 + " didn't borrow this book so can't return it!");
            System.out.println("(request cancelled, please select another option from the menu below.)");
            return;

        }

        //return book
        library.returnBook(name1, name2, title, authFullname);

        System.out.println("Thank you the request to return" + title + " has been sucessful, and our records have been updated accordingly."); 

    }

    /**
     * Prints a welcome message and menu of function options to the screen.
     */
    private void printMenu()
    {
        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("f - to finish running the program");
        System.out.println("b - to display on the screen the information about all the books in the library");
        System.out.println("u - to display on the screen the information about all the users");
        System.out.println("i - to update the stored data when a book is issued to a user");
        System.out.println("r - to update the stored data when a user returns a book to the library");
        System.out.println("------------------------------");
        System.out.println("Type a letter and press Enter");

    }

}