import java.io.*;
import java.util.*;
/**
 * class Library, the library class contains a sorted arraylist of users and a sorted arraylist of books which are used to contain all the information required for the system.
 * 
 * @author Andrew Megson 
 * @version 11/12/18
 */
public class Library
{

    private SortedArrayList<Book> Books;
    private SortedArrayList<User> Users;

    static Scanner k = new Scanner(System.in);

    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        Books = new SortedArrayList<>();
        Users = new SortedArrayList<>();
    }

    /**
     * method to access the sorted arraylist of Book objects
     *
     * @returns the arraylist of Book objects
     */
    public SortedArrayList<Book> getBooks()
    {
        return Books;
    }

    /**
     * method to access the sorted arraylist of User objects
     *
     * @returns the arraylist of User objects
     */
    public SortedArrayList<User> getUsers()
    {
        return Users;
    }

    /**
     * method to check that a User is valid i.e. present in the sorted arraylist of Users.
     *
     *@param takes 2 strings of user first name and surname
     *@returns a boolean, true if user is present in sorted arraylist of users.
     */
    public boolean validUser(String name1, String name2)
    {
        boolean validUser = false;

        for(User u :Users)
        {
            if (u.getFirstName().equals(name1) && u.getSurname().equals(name2))
            {
                validUser = true;
                break;
            }

        }

        return validUser;

    }

    /**
     * method to check a book is valid i.e. in the Books sorted arraylist
     *
     *@param takes 2 strings of book title and author fullname
     *@returns a boolean, true if book is present in Books sorted arraylist
     */
    public boolean validBook(String title, String AuthFullname)
    {
        boolean validBook = false;

        for(Book b : Books)
        {
            if (b.getTitle().equals(title) && b.getAuthFullName().equals(AuthFullname))
            {  
                validBook = true;
                break;
            }

        }

        return validBook;
    }

    /**
     * method to check if user is allowed to borrow i.e. has less than maximum amount of books allowed
     *
     *@param takes 2 strings of user first name and surname
     *@returns a boolean, true if user curently has less than 3 books on loan.
     */
    public boolean allowedToBorrow(String name1, String name2)
    {
        int x = 0;
        boolean allowed = false;
        for(User u : Users)
        {
            if (u.getFirstName().equals(name1) && u.getSurname().equals(name2))
            {
                x = u.getNumberOfBooks();
                break;
            }

        }
        if(x < User.MAXBOOKSALLOWED )
        {
            allowed = true;

        }
        return allowed;
    }

    /**
     *method to check the current loan stauts of a book
     *
     *@param takes 2 strings of book title and author fullname
     *@returns a boolean, true if book is currently out on loan
     */
    public boolean currentLoanStatus(String title, String AuthFullname)
    {
        boolean a = false;

        for(Book b : Books)
        {
            if (b.getTitle().equals(title) && b.getAuthFullName().equals(AuthFullname))
            {  
                a = b.loanStatus() ;
                break;
            }

        }
        return a;
    }

    /**
     * method to get the name of the user who currently has a book out on loan
     *
     *@param takes 2 strings of book title and author fullname
     *@returns a String of the current borrowers full name
     *
     */
    public String getBorrowerName(String title, String AuthFullname)
    {
        String fullname;
        User u = new User();

        for (Book b : Books)
        {
            if (b.getTitle().equals(title) && b.getAuthFullName().equals(AuthFullname))
            {
                u = b.getBorrower();
                break;
            }
        }

        fullname = u.getFirstName() + " " + u.getSurname();

        return fullname;
    }

    /**
     * method to loan a book to a user i.e. updates the information stored in the sorted arraylists accordingly,
     * increases user number of books by 1, and sets book loan stsatus to true. 
     *
     *@param takes 4 String parameters of the users first name, users surname, book title and book authors fullname
     *
     */
    public void loanBook(String name1,String name2, String title, String AuthFullname)
    {
        User x = new User();

        for(User u : Users)
        {
            if (u.getFirstName().equals(name1) && u.getSurname().equals(name2))
            {
                u.increaseBooks();
                x = u;
                break;
            }

        }

        for (Book b : Books)
        {
            if (b.getTitle().equals(title) && b.getAuthFullName().equals(AuthFullname))
            {
                b.setOnLoan(true);
                b.setBorrower(x);
                break;
            }

        }
    }

    /**
     * method to return a book to a user i.e. updates the information stored in the sorted arraylists accordingly,
     * decreases user number of books by 1, and sets book loan stsatus to false. 
     *
     *@param takes 4 String parameters of the users first name, users surname, book title and book authors fullname
     *
     */
    public void returnBook(String name1, String name2, String title, String AuthFullname)
    {
        User x = new User();
        for(User u : Users)
        {
            if (u.getFirstName().equals(name1) && u.getSurname().equals(name2))
            {
                u.decreaseBooks();

                break;
            }

        }

        for (Book b : Books)
        {
            if (b.getTitle().equals(title) && b.getAuthFullName().equals(AuthFullname))
            {
                b.setOnLoan(false);
                b.setBorrower(x);
                break;
            }

        }

    }

}