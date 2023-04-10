/**
 *This is class inherits calues from Room and adds a time and occupancy so that
 *people know what time their tests will be. It is what separates these instances
 *even though they might be in the same room (have the same code)
 * @author jakubdavison
 */
 import java.util.Date;
 import java.text.SimpleDateFormat;
 import java.text.ParseException;

 public class Bookable_Room extends Room{
   enum Status{
     EMPTY, AVAILABLE, FULL
   }
   private Status status;
   private Date time;
   private int occupancy;

   Bookable_Room(String date, int occupy, String codeR, int cap){
     super(codeR, cap);
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
     this.occupancy = occupy;
     setStatus();
   }
   Bookable_Room(String date, Room room){
     super(room.getCode(), room.getCapacity());
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
     this.occupancy = 0;
     setStatus();
   }
   Bookable_Room(String date, int occupy, Room room){
     super(room.getCode(), room.getCapacity());
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
     this.occupancy = occupy;
     setStatus();
   }
   Bookable_Room(Date date, Room room){
     super(room.getCode(), room.getCapacity());
     this.time = date;
   }

   public void setStatus(){
     if(occupancy == 0){
       this.status = Status.EMPTY;
     }
     else if(occupancy < super.getCapacity()){
       this.status = Status.AVAILABLE;
     }
     else{
       this.status = Status.FULL;
     }
   }
   public Status getStatus(){
     return this.status;
   }
   public void setTime(String date){
     try{
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       this.time = dateFormat.parse(date);
     }
     catch(ParseException e){
       e.printStackTrace();
     }
   }
   public void setTime(Date date){
     this.time = date;
   }
   public String getTimeString(){
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
     String ret = formatter.format(this.time);
     return ret;
   }
   public Date getTime(){
     return this.time;
   }
   public void setOccupancy(int occupy){
     this.occupancy = occupy;
   }
   public int getOccupancy(){
     return this.occupancy;
   }
   public void addOccupant(){
     this.occupancy++;
     setStatus();
   }
   public String printBookableRoom(){

     return "| " + getTimeString() + " | " + this.status + " | " + super.getCode() + " | " + this.occupancy + " |";//fill in
   }
   public boolean bookableRoomCheck(String date, Room rom){
     if((date.equals(getTimeString())) && (rom.getCode().equals(super.getCode()))) {
       return true;
     }
     return false;
   }
 }
