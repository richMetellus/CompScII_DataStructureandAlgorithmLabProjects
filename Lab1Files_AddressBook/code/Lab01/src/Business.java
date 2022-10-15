/**
 *
 * @author richelin.metellus
 * @version 01/19/2017
 * Business contact with 2extra data fields/getter/setter
 */
public class Business extends Contact {
    private static int businessCount = 0;
    private String title;
    private String businessName;
    
    /**
     * 
     * @param name
     * @param address
     * @param phone
     * @param title
     * @param businessName 
     */
    public Business( String name, String address, String phone,
            String title, String businessName)
    {
        super(name, address, phone);
        this.title = title;
        this.businessName = businessName;
        businessCount++;
    }
    /**
     * 
     * @return 
     */
    public String getBusinessName()
    {
        return businessName;
    }
    /**
     * 
     * @param businessName 
     */
    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }
    /**
     * 
     * @return 
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * 
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return super.toString() + ":" + getClass().getName() + "@" + businessCount + ":"
                + title + ":" + businessName;
        
    }
    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o)
    {
        if (! ( o instanceof Business)) // is o an instance of the class Business. return true or false 
            return false; // if they are not the same data type return false.
        Business b = (Business) o;
        
        return super.equals( b) && title.equalsIgnoreCase(b.title)
                && businessName.equalsIgnoreCase(b.businessName ); 
               
    }
}
