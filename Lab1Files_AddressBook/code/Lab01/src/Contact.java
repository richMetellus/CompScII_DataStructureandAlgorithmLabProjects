  /**
 *
 * @author richelin.metellus
 */

public class Contact {
    private static int contactCount = 0;
    private String name;
    private String address;
    private String phone;
    
/**
 * 
 * @param name
 * @param newAddress
 * @param newPhone 
 */
   public Contact(String name, String newAddress, String newPhone)
   {
       this.name = name;
       address = newAddress;
       phone = newPhone ;
       contactCount++;
   }
   
   
   public String getName()
   {
       return name;
   }
/**
 * 
 * @param newName 
 */
   public void setName(String newName)
   {
       name = newName;
   }
/**
 * 
 * @return 
 */   
   public String getAddress()
   {
       return address;
   }
   /**
    * 
    * @param address 
    */
   public void setAddress( String address)
   {
       this.address = address;
   }
   /**
    * 
    * @return 
    */
   public static int getContactCount()
   {
       return contactCount;
   }
   /**
    * 
    * @return 
    */
   public String getPhone() // non-static because ii want to call it on instance.
   {
       return phone;
   }
   /**
    * 
    * @param phone 
    */
   public void setPhone(String phone)
   {
       this.phone = phone;
   }
   /**
    * 
    * @return 
    */
   public String toString()
   {
       return getClass().getSuperclass().getName() + "@" + contactCount  +":"
               + getName() + ":" + getAddress() + ":" + getPhone() ;
   }
   /**
    * 
    * @param o
    * @return 
    */
    @Override
   public boolean equals(Object o)
   {
       if (!( o instanceof Contact))
           return false;
       Contact c = (Contact)o;
       
       return name.equalsIgnoreCase(c.name)
               && address.equalsIgnoreCase(c.address) // not o.address. it has to be the cast identifier.
               && phone.equals(c.phone);
   }
}
