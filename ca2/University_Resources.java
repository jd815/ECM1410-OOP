/**
 * This class is not used at all. I forgot it was used and I implemented the lists in the booking system.
 * It seems easier anyway as you can have it all in one place
 * @author jakubdavison
 */
 import java.util.ArrayList;
 public class University_Resources{
   private ArrayList<Assistant> assistants = new ArrayList<>();
   private ArrayList<Room> rooms = new ArrayList<>();

   University_Resources(ArrayList<Assistant> assist, ArrayList<Room> rom){
     this.assistants = assist;
     this.rooms = rom;
   }
   public void setAssistants(ArrayList<Assistant> assist){
     this.assistants = assist;
   }
   public ArrayList<Assistant> getAssistants(){
     return this.assistants;
   }
   public Assistant getAssistant(int x){
     return this.assistants.get(x);
   }
   public void addAssistant(Assistant assist){
     this.assistants.add(assist);
   }
   public void setRooms(ArrayList<Room> rom){
     this.rooms = rom;
   }
   public ArrayList<Room> getRooms(){
     return this.rooms;
   }
   public Room getRoom(int x){
     return this.rooms.get(x);
   }
   public void addRoom(Room rom){
     this.rooms.add(rom);
   }

 }
