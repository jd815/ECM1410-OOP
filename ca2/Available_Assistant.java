/**
 * The available assistant class inherits the values from class assistant
 * and adds time and status
 * @author jakubdavison
 */
 import java.util.Date;
 import java.text.SimpleDateFormat;
 import java.text.ParseException;
 public class Available_Assistant extends Assistant{
   private Date time;
   enum Status{
     FREE, BUSY
   }
   private Status status;
   Available_Assistant(Assistant as){
     super(as.getEmail(), as.getName());
     status = Status.FREE;
   }
   Available_Assistant(String date, Assistant as){
     super(as.getEmail(), as.getName());
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
     status = Status.FREE;
   }
   Available_Assistant(String date, String mail, String nameA){
     super(mail, nameA);
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
     status = Status.FREE;
   }
   public void setTime(String date){
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
   }
   public void setTime(Date date){
     this.time = date;
   }
   public Date getTime(){
     return this.time;
   }
   public String getTimeString(){
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     String ret = formatter.format(this.time);
     return ret;
   }
   public Status getStatus(){
     return this.status;
   }
   public void changeStatus(){
     if(this.status == Status.FREE){
       this.status = Status.BUSY;
     }
     else{
       this.status = Status.FREE;
     }
   }
   public boolean availableAssistantCheck(String date, Assistant as){
     if((date.equals(getTimeString())) && (as.getEmail().equals(super.getEmail()))) {
       return true;
     }
     return false;
   }
   public String printAvailableAssistant(){
     return "| " + getTimeString() + " | " + this.status + " | " + super.getEmail() + " |";
   }

 }
