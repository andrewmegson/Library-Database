import java.util.*;
/**
 * class SortedArrayList here.
 * 
 * @author Andrew Megson 
 * @version 11/12/18
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E>
{

    /**
     * Method to add element to sorted arraylist
     * 
     * @param takes an elemet of type e to be added to sorted arraylist
     * 
     */
    public boolean add(E e)
    {

        if(this.size()==0)
        {
            super.add(e);
            return true;
        }

        for(int i=0; i < (this.size()); i++)
        {
            if (this.get(i).compareTo(e)>=0)
            {
                super.add(i,e);

                return true;

            }
        }

        super.add(e);
        return true;

    }
}

