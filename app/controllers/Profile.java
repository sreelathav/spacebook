package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.db.jpa.Blob;

public class Profile extends Controller
{
  public static void index()
  {
    String userId = session.get("logged_in_userid");
    if(userId!=null)
    {	
       User user = User.findById(Long.parseLong(userId));
       render(user);
    }
    else
    {
    	Accounts.login();
    }
  }
  
  public static void changeStatus(String profiletext)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.statusText = profiletext;
    user.save();
    Logger.info("Status changed to " + profiletext);
    index();
  }  
  public static void changeAge(String profileage)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.age = profileage;
    user.save();
    Logger.info("Age changed to " + profileage);
    index();
  }
  public static void changeNationality(String profilenationality)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.nationality = profilenationality;
    user.save();
    Logger.info("Nationality changed to " + profilenationality);
    index();
  }
  
  public static void getPicture(Long id) 
  {
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  
  public static void uploadPicture(Long id, Blob picture)
  {
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    index();
  }  
}