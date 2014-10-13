package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Members extends Controller
{
	
  public static void index()
  {
	  List<User> users = User.findAll();
	  String userId = session.get("logged_in_userid");
	  if (userId!=null)
	  {	  
	     User me = User.findById(Long.parseLong(userId));
         users.remove(users.indexOf(me));
         render(users);
	  }
	  else
	  {
		  Accounts.login();
	  }
  }
  
  public static void follow(Long id)
  {
     String userId = session.get("logged_in_userid");
	 User me = User.findById(Long.parseLong(userId));
     User friend = User.findById(id);
     me.befriend(friend);
     Home.index();
  }
}