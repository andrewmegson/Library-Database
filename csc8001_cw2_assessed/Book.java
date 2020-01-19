
/**
 * class Book, a book object contains all the relevant info needed to be stored for a books record. 
 * i.e. (book title, author surname, author full name, loan status, current borrower
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Book implements Comparable<Book>
{

    private String title;
    private String authSurname;
    private String authFullName;
    private boolean onLoan;
    private User borrower;

    /**
     * Constructor of Books class
     */
    public Book()
    {

    }

    /**
     * Constructor of Books class
     * 
     * @param take 3 strings for book title, author fullname and author surname
     * 
     */
    
    public Book(String title,String authSurname, String authFullName)
    {
        this.title=title;
        this.authSurname= authSurname;
        this.authFullName=authFullName;
       

    }

    
     /**
     * @override
     * 
     * Method to override the toString method
     * 
     */
    public String toString()
    {
        String message = title + " by " + authFullName;

        if(onLoan)
        {
			message = message + ", currently on loan with " + borrower.getFirstName() + " " + borrower.getSurname(); 
        }
		else
		{
			message = message + ", currently available";
		  }

		return message;
    }

    /**
     * method to get book titile
     * 
     * @returns a string of the book title
     */
    public String getTitle()
    {
        return title;
    }

     /**
     * method to set the book title
     * 
     * @param a string of the book title
     */
    public void setTitle(String title)
    {
        this.title=title;
    }

     /**
     * method to get the author surname
     * 
     * @returns a string of author surname
     */
    public String getAuthSurname()
    {
        return authSurname;
    }

     /**
     * method to get the author fullname
     * 
     * @returns a string of author fullname
     */
    public String getAuthFullName()
    {
        return authFullName;
    }

     /**
     *  method to set the authors fullname
     * 
     * @param a string of authors fullname
     */
    public void setAuthFullName(String name)
    {
        authFullName = name ;
    }

     /**
     * method to set the authors surname
     * 
     * @param a string of author surname
     */
    public void setAuthSurname(String name)
    {
        authSurname = name ;
    }


     /**
     * method to get the books loan status
     * 
     * @returns a boolean of the loan status (i.e. true if on loan)
     */
    public boolean loanStatus()
    {
        return onLoan;
    }

     /**
     * method to return current borrow of book
     * 
     * @returns an object of type User that is euqal to the current User who has book on loan
     */
    public User getBorrower()
    {
        return borrower;
    }

     /**
     * method to set the loan stautus of a book
     * 
     * @param takes a boolean variable (i.e. true if book is on loan)
     */
    public void setOnLoan(boolean b)
    {
        onLoan=b;
    }

     /**
     * method to set borrow of book
     * 
     * @param takes an object of type User equal to the current borrow of the book
     */
    public void setBorrower( User u)
    {
        borrower=u;
    }

     /**
     * method to implemet the compareTo method in Book class
     *
     */
    public int compareTo(Book b)
    {
        int surCmp = authSurname.compareTo(b.authSurname);
        if (surCmp !=0)
        {
            return surCmp;
        }
        else 
        {return 0;
        }

    }
}
