package controllers;

import play.*;
import play.mvc.*;
import java.lang.Object;
import utils.MessageDateComparator;
import utils.MessageFromComparator;
import java.util.*;

import models.*;

public class Home extends Controller
{
  
  public static void index()
  {
	    
		String userId = session.get("logged_in_userid");
		if (userId!=null)
		{	
          User  user = User.findById(Long.parseLong(userId));
          ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();
          ArrayList<Message> conversation = new ArrayList<Message>();
          Collections.sort(user.inbox,new MessageDateComparator()); 
    	  for (Message m: user.inbox)
    	  {		 
    			conversation.add(m);    
    	  }	
    	  conversations.add(conversation); 
          render("Home/index.html",user,conversations);
           
		}
		else
		{
			Accounts.login();
		}	  
  }
  
  public static void drop(Long id)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    
    User friend = User.findById(id);
 
    user.unfriend(friend);
    Logger.info("Dropping " + friend.email);
    index();
  } 
 
  public static void bySender()
  {
     /*
     * Copy the user's inbox to a new list of messages
     * Sort the copy of the inbox by sender name (name = firstname + lastname))
     * Render the inbox in the home view 
     */
	  String userId = session.get("logged_in_userid");
	  User user = User.findById(Long.parseLong(userId));
	  ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();	     
	  ArrayList<Message> conversation = new ArrayList<Message>();
	 
	  Collections.sort(user.inbox,new MessageFromComparator());
	  for (Message m: user.inbox)
	  {		 
			conversation.add(m);    
	  }
	  conversations.add(conversation); 
	  render("Home/index.html",user,conversations);
  }

  public static void byConversation()
  {  
    /*
     * Create a list of list of messages
     * Each list of messages is a single conversation
     * A conversation takes place between 2 users
     * for each of user's friends
     *    create list of messages comprising conversation with this friend,
     *    add this list of messages (conversation) to list of conversations
     * 
     * render conversations in view
     */
	  String userId = session.get("logged_in_userid");
	  User user = User.findById(Long.parseLong(userId));
	  ArrayList<ArrayList<Message>> conversations = new ArrayList<ArrayList<Message>>();
	  for (Friendship f: user.friendships)
	  {		 
		  conversations.add(getConversation(user,f.targetUser));
	  }	   
      render("Home/index.html",user, conversations);
  }
  
  
/**
 * Generates a conversation comprising a list of messages between two users.
 * The list is sorted by reference to date and time. 
 * Recall that a message object contains an instance of Date object
 * 
 * @param user the user who initiates the conversation
 * @param friend the user with whom the initiator is having a conversation
 * @return the list of messages which makes up the conversation*/
 
  static ArrayList<Message> getConversation (User user, User friend)
  {
  
   /**Create a list of messages to represent a conversation
   * from the user's outbox
   * extract message to friend,  then
   *    add the message to the conversation
   * from the user's inbox
   * extract message from  friend, then
   *    add the message to the conversation
   * Sort the conversation by date
   * @return the conversation*/
   
	 ArrayList<Message> conversation = new ArrayList<Message>();
	  for (Message m: user.outbox)
	  {
		  if(m.to.equals(friend))
		  {
			conversation.add(m);  
		  } 
	  }	  
	  for (Message m: user.inbox)
	  {
		  if(m.from.equals(friend))
		  {
			conversation.add(m);  
		  } 
	  }	
	  Collections.sort(conversation,new MessageDateComparator());
      return conversation;
  }

}