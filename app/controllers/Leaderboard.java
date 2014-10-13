package controllers;

import java.lang.Object;
import play.*;
import play.mvc.*;
import utils.UserSocialComparator;
import utils.UserTalkativeComparator;
import models.*;
import java.util.*;


public class Leaderboard extends Controller
{  
  public static void index()
  {
		if (session.get("logged_in_userid")!=null)
		{	
		   List<User> users = User.findAll();
		   Collections.sort(users, new UserSocialComparator());
           render("Leaderboard/index.html",users);
		}
		else
		{
			Accounts.login();
		}	    
  }
  
  public static void talkative()
  {   
   
	List<User> users = User.findAll();  
	Collections.sort(users, new UserTalkativeComparator());
	render("Leaderboard/index.html",users);
   
  }

  
   
}