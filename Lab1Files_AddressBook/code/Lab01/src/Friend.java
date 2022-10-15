/**
 *
 * @author Richelin.metellus
 * @version 01/19/2017
 * Friend contact with 2+data fields & getter & setter;
 */
public class Friend extends Contact {
    
   private String birthday;
   private String favoriteMovie;
   private static int friendCount;
   
  //Overload constructor
   /**
    * 
    * @param name
    * @param address
    * @param phone
    * @param birthday
    * @param favoriteMovie 
    */
    public Friend( String name, String address, String phone, String birthday, String favoriteMovie)
    {
        super(name, address,phone);
        this.birthday = birthday;
        this.favoriteMovie = favoriteMovie;
        friendCount++ ;
    }
    /**
     * 
     * @return 
     */
    public String getBirthday()
    {
        return birthday;
    }
    /**
     * 
     * @param birthday 
     */
    public void setBirthday( String birthday)
    {
       this.birthday = birthday; 
    }
    /**
     * 
     * @return 
     */
    public String getFavoriteMovie()
    {
        return favoriteMovie;
    }
    /**
     * 
     * @param favoriteMovie 
     */
    public void setFavoriteMovie( String favoriteMovie)
    {
        this.favoriteMovie = favoriteMovie;
    }
    /**
     * 
     * @return 
     */
   @Override
    public String toString()
    {
        return super.toString() + ":" +getClass().getName() + "@" + friendCount
            + " : " + getBirthday() + ":" + getFavoriteMovie();
    }
    /**
     * 
     * @param o
     * @return 
     */
   @Override
    public boolean equals( Object o)
    {
        if( !( o instanceof Object))
            return false;
        Friend f = (Friend) o;
        return super.equals(f) && birthday.equalsIgnoreCase(f.birthday) 
                && favoriteMovie.equalsIgnoreCase(f.favoriteMovie);
    }
}


