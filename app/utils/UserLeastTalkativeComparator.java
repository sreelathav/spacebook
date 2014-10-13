package utils;

import java.util.Comparator;

import models.User;

public class UserLeastTalkativeComparator implements Comparator<User>
{
	 /**
     * compare the outboxes of users  
     *use the Integer.compare method
     *argument negated to facilitate the reverse sort
     *@return integer as the result of compare
	*/
  @Override
  public int compare(User a, User b)
  {
     
	 return -Integer.compare (b.outbox.size(), a.outbox.size());
	
  }
}
