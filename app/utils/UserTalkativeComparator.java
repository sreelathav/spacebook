package utils;

import java.util.Comparator;
import models.User;

public class UserTalkativeComparator implements Comparator<User>
{
	 /**
     * compare the outboxes of users  
     *use the Integer.compare method
     *use the outbox of b as the parameter
     *to facilitate sorting
	*/	
  @Override
  public int compare(User a, User b)
  {
    return Integer.compare (b.outbox.size(), a.outbox.size());
  }
}
