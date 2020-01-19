
/**
 * class Users, a User object contains all the info needed to be stored for a users record i.e. user firstname, user surname, 
 * current number of books a user has and the maximum number of books allowed.
 * 
 * @author Andrew Megson 
 * @version 11/12/18
 */
public class User implements Comparable<User>
{

    private String firstName;
    private String surname;
    private int numberOfBooks;

    public static final int MAXBOOKSALLOWED = 3;

    /**
     * Constructor of class Users
     */
    public User()
    {

        String firstName = "";
        String surname ="";
        int numberOfBooks =0;
    }

    /**
     * Constructor of class Users
     *
     *@param takes 2 paramters of strings of users first name and surname
     */
    public User(String firstName, String surname)
    {
        this.firstName=firstName;
        this.surname=surname;
    }

    /**
     * Constructor of class Users
     * 
     *@param takes 2 paramters of strings of users first name and surname and an int of number of books
     */
    public User(String firstName, String surname, int numberOfBooks)
    {
        this.firstName=firstName;
        this.surname=surname;
        this.numberOfBooks=numberOfBooks;
    }

    /**
     * @override
     * 
     * Method to override the toString method
     * 
     */
    public String toString()
    {
        String message;

        message = firstName + " " + surname + ", current no. of books on loan = " +numberOfBooks;

        return message;

    }

    /**
     * method to get firstname of user
     * 
     * @returns a string of user firstname
     *
     */
    public String getFirstName()
    {
        return firstName;
    }

     /**
     *  method to get surname of user
     * 
     * @returns a string of user surname
     *
     */
    public String getSurname()
    {
        return surname;
    }

     /**
     * method to set first name of a user
     * 
     * @param take a string of a user first name 
     */
    public void setFirstname (String name)
    {
        firstName = name;
    }

     /**
     * method to set surname of a user
     * 
     * @param take a string of a users surname 
     *
     */
    public void setSurname (String name)
    {
        surname = name;
    }

     /**
     * method to get the number of books a user has
     *
     * @return an int of users number of books
     */
    public int getNumberOfBooks()
    {
        return numberOfBooks;
    }

     /**
     * method to set a users number of book
     * 
     * @param takes an int of users number of books
     *
     */
    public void setNumberOfBooks(int i)
    {
        numberOfBooks=i;
    }

     /**
     * method to increase a users number of books by 1.
     *
     */
    public void increaseBooks()
    {
        numberOfBooks = numberOfBooks + 1;
    }

     /**
     * method to decrease a users number of books by 1.
     *
     */
    public void decreaseBooks()
    {
        numberOfBooks = numberOfBooks - 1;
    }

     /**
     * method to implemet the compareTo method in User class
     *
     */
    public int compareTo(User u)
    {
        int surCmp = surname.compareTo(u.surname);
        if (surCmp !=0)
        {
            return surCmp;
        }
        int fnCmp = firstName.compareTo(u.firstName);
        if (fnCmp != 0)
        {
            return fnCmp;
        }
        else 
        {return 0;
        }
        //          what about if users have same first name?

    }
}
