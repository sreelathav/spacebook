package models;

import javax.persistence.*;
import play.db.jpa.*;
import java.util.Date;

@Entity
public class Message extends Model
{
  public String messageText;
  public Date postedAt;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;

  public Message(User from, User to, String messageText)
  {
    this.from = from;
    this.to = to;
    this.messageText = messageText;
    postedAt = new Date();
    
   }
  
}