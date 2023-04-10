/**
 *This is where all the lists are stored. It works like the main memory
 * @author jakubdavison
 */
 import java.util.ArrayList;

 public class Booking_System{
   private ArrayList <Bookable_Room> bookableRooms = new ArrayList<>();
   private ArrayList <Room> rooms = new ArrayList<>();
   private ArrayList <Available_Assistant> availableAssistants = new ArrayList<>();
   private ArrayList <Assistant> assistants = new ArrayList<>();
   private ArrayList <Booking> bookings = new ArrayList<>();

   Booking_System(){

   }

   public void setBookableRooms(ArrayList <Bookable_Room> bookroom){
     this.bookableRooms = bookroom;
   }
   public ArrayList <Bookable_Room> getBookableRooms(){
     return this.bookableRooms;
   }
   public Bookable_Room getBookableRoom(int x){
     return this.bookableRooms.get(x);
   }
   public void addBookableRoom(Bookable_Room bookroom){
     this.bookableRooms.add(bookroom);
   }
   public void removeBookableRoom(int x){
     this.bookableRooms.remove(x);
   }
   public String printBookableRooms(){
     String ret = "";
     for (int i=0; i<this.bookableRooms.size(); i++){
       ret = ret + this.bookableRooms.get(i).printBookableRoom() + "\n";
     }
    return ret;
   }
   public Room getRoom(int x){
     return this.rooms.get(x);
   }
   public void addRoom(Room rom){
     String output = "";
     for (int i=0; i<rooms.size(); i++){
       if(rooms.get(i).getCode().equals(rom.getCode())){
         output = "Sorry this code is already in use";
       }
     }
     if(output.equals("")){
       this.rooms.add(rom);
     }
     else{
       System.out.println(output);
     }
   }
   public ArrayList <Room> getRooms(){
     return this.rooms;
   }
   public String printRooms(){
     String ret = "";
     for (int i=0; i<this.rooms.size(); i++){
       ret = ret + this.rooms.get(i).printRoom() + "\n";
     }
     return ret;
  }

   public void setAvailableAssistants(ArrayList <Available_Assistant> assists){
     this.availableAssistants = assists;
   }
   public ArrayList <Available_Assistant> getAvailableAssistants(){
     return this.availableAssistants;
   }
   public Available_Assistant getAvailableAssistant(int x){
     return this.availableAssistants.get(x);
   }
   public void addAvailableAssistant(Available_Assistant assist){
     this.availableAssistants.add(assist);
   }
   public void removeAvailableAssistant(int x){
     this.availableAssistants.remove(x);
   }
   public boolean isFreeAvailableAssistant(){
     for(int i=0; i<availableAssistants.size(); i++){
       if(availableAssistants.get(i).getStatus().toString().equals("FREE")) {
         return true;
       }
     }
     return false;
   }
   public Available_Assistant getFreeAvailableAssistant(){
     /*
      I can implement the return of the first assistant because before I call this method I call the isFree (just above)
      SO i know that at least one has to be free.
     */
     for(int i=0; i<availableAssistants.size(); i++){
       if(availableAssistants.get(i).getStatus().toString().equals("FREE")) {
         return availableAssistants.get(i);
       }
     }
     return availableAssistants.get(0);
   }
   public String printAvailableAssistants(){
     String ret = "";
     for (int i=0; i<this.availableAssistants.size(); i++){
       ret = ret + this.availableAssistants.get(i).printAvailableAssistant()+ "\n";
     }
    return ret;
   }

   public Assistant getAssistant(int x){
     return this.assistants.get(x);
   }
   public void addAssistant(Assistant as){
     String output = "";
     for (int i=0; i<assistants.size(); i++){
       if(assistants.get(i).getEmail().equals(as.getEmail())){
         output = "Sorry a student with this email is already in the system";
       }
     }
     if(output.equals("")){
       this.assistants.add(as);
     }
     else{
       System.out.println(output);
     }
   }
   public ArrayList<Assistant> getAssistants(){
     return this.assistants;
   }
   public String printAssistants(){
     String ret = "";
     for (int i=0; i<this.assistants.size(); i++){
       ret = ret + this.assistants.get(i).printAssistant() + "\n";
     }
     return ret;
  }

   public void setBookings(ArrayList <Booking> books){
     this.bookings = books;
   }
   public ArrayList <Booking> getBookings(){
     return this.bookings;
   }
   public Booking getBooking(int x){
     return this.bookings.get(x);
   }
   public void addBooking(Booking book){
     book.getBookableRoom().addOccupant();
     book.getAvailableAssistant().changeStatus();
     this.bookings.add(book);
   }
   public void removeBooking(int x){
     this.bookings.remove(x);
   }
   public String printBookings(){
     String ret = "";
     for (int i=0; i<this.bookings.size(); i++){
       ret = ret + this.bookings.get(i).printBooking()+ "\n";
     }
    return ret;
   }

 }
