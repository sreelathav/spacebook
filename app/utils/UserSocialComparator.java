package utils;

import java.util.Comparator;
import models.User;

public class UserSocialComparator implements Comparator<User>
{
	 /**
     * compare the size of friendships list of users  
     *use the Integer.compare method
     *@return result of the compare as the integer
	*/
  @Override
  public int compare(User a, User b)
  {
    return Integer.compare (b.friendships.size(), a.friendships.size());
  }
}
