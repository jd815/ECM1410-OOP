/**
 * This is the bookings class, it is probably the most important class as this is
 * where the actuall booking times are set. This class also contains the main method
 * @author jakubdavison
 */
 import java.util.Scanner;
 import java.util.InputMismatchException;
 import java.util.Date;
 import java.text.SimpleDateFormat;
 import java.text.ParseException;
 import java.lang.StringIndexOutOfBoundsException;
 import java.lang.NumberFormatException;
 public class Booking{
   static Booking_System bookingSystem = new Booking_System();
   static Scanner input = new Scanner (System.in);
   private int IDcode;
   private String email;
   private Bookable_Room bookableRoom;
   private Available_Assistant availableAssistant;
   enum Status {
     SCHEDULED, COMPLETED
   }
   private Status status;

   Booking(int code, String mail, Bookable_Room br, Available_Assistant as){
     this.IDcode = code;
     this.email = mail;
     this.bookableRoom = br;
     this.availableAssistant = as;
     setStatus();
   }
   public Bookable_Room getBookableRoom(){
     return this.bookableRoom;
   }
   public Available_Assistant getAvailableAssistant(){
     return this.availableAssistant;
   }
   public void setIDCode(int code){
     boolean t=true;
     for(int i=0; i<bookingSystem.getBookings().size(); i++){
       if(bookingSystem.getBooking(i).getIDCode() == code){
         System.out.println("This code is already in use");
         t=false;
       }
     }
     if(t=true){this.IDcode = code;}
   }
   public int getIDCode(){
     return this.IDcode;
   }
   public void setEmail(String mail){
     this.email = mail;
   }
   public String getEmail(){
     return this.email;
   }
   public void setStatus(){
     //This setStatus method relies on time
     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
     Date date = new Date();
     if(this.bookableRoom.getTime().compareTo(date)>0){
       this.status = Status.SCHEDULED;
     }
     else{
       this.status = Status.COMPLETED;
     }
   }
   public void setStatus(Status stat){
     //this one just sets the value based on the passed value
     this.status = stat;
   }
   public Status getStatus(){
     return this.status;
   }
   public String printBooking(){
     /*
     This is a print version for the screen. I have added the IDcode on the end so the user can see it when they want to remove import junit.framework.TestCase;
     */
     return "| " + this.bookableRoom.getTimeString() + " | " + this.status + " | " + availableAssistant.getEmail() + " | " + bookableRoom.getCode() + " | " + this.email + " | " + this.IDcode + " |";
   }

public static void main (String[] args){
  /*
  At the start I just hardcode the initial values we are supposed to have
  */
  Room room1 = new Room("IC215", 2);
  Room room2 = new Room("IC335", 3);
  Room room3 = new Room("IC111", 4);
  Room [] rooms = {room1, room2, room3};

  Bookable_Room br1 = new Bookable_Room("26/02/2021 07:00", room1);
  Bookable_Room br2 = new Bookable_Room("26/02/2021 08:00", room1);
  Bookable_Room br3 = new Bookable_Room("26/02/2021 09:00", room1);
  Bookable_Room br4 = new Bookable_Room("27/02/2021 07:00", room2);
  Bookable_Room br5 = new Bookable_Room("27/02/2021 08:00", room2);
  Bookable_Room br6 = new Bookable_Room("27/02/2021 09:00", room2);
  Bookable_Room br7 = new Bookable_Room("28/02/2021 07:00", room3);
  Bookable_Room br8 = new Bookable_Room("28/02/2021 08:00", room3);
  Bookable_Room br9 = new Bookable_Room("28/02/2021 09:00", room3);
  Bookable_Room [] bRooms = {br1, br2, br3, br4, br5, br6, br7, br8, br9};

  Assistant as1 = new Assistant("vl815@uok.ac.uk", "Viet Duc");
  Assistant as2 = new Assistant("jd118@uok.ac.uk", "Jakub Davison");
  Assistant as3 = new Assistant("am815@uok.ac.uk", "Alex Martin");
  Assistant [] ass = {as1, as2, as3};

  Available_Assistant aa1 = new Available_Assistant("25/02/2021", as1);
  Available_Assistant aa2 = new Available_Assistant("26/02/2021", as1);
  Available_Assistant aa3 = new Available_Assistant("27/02/2021", as1);
  Available_Assistant aa4 = new Available_Assistant("26/02/2021", as2);
  Available_Assistant aa5 = new Available_Assistant("27/02/2021", as2);
  Available_Assistant aa6 = new Available_Assistant("28/02/2021", as2);
  Available_Assistant aa7 = new Available_Assistant("26/02/2021", as3);
  Available_Assistant aa8 = new Available_Assistant("27/02/2021", as3);
  Available_Assistant aa9 = new Available_Assistant("28/02/2021", as3);
  Available_Assistant [] aas = {aa1, aa2, aa3, aa4, aa5, aa6, aa7, aa8, aa9};

  for(int x=0; x<3; x++){
    bookingSystem.addRoom(rooms[x]);
    for(int i=0; i<3; i++){
      bookingSystem.addBookableRoom(bRooms[x*3 + i]);
    }
  }
  for (int x=0; x<3; x++){
    bookingSystem.addAssistant(ass[x]);
    for(int i=0; i<3; i++){
      bookingSystem.addAvailableAssistant(aas[x*3 + i]);
    }
  }

  Booking bk1 = new Booking(11, "sg815@uok.ac.uk", br1, aa1);
  Booking bk2 = new Booking(12, "pd342@uok.ac.uk", br4, aa5);
  bookingSystem.addBooking(bk1);
  bookingSystem.addBooking(bk2);
  /*
  Once intial values are set, the mainScreen method is called so the main screen can load
  */
  mainScreen();
}

  public static void clearScreen() {
    //clears the screen
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  public static void mainScreen(){
    System.out.println("University of Knowledge - COVID test \n\nManage Bookings \n\nPlease, enter the number to select your option: \n");;
    System.out.println("To manage Bookable Rooms: \n    1. List \n    2. Add \n    3. Remove");
    System.out.println("To manage Assistants on Shift: \n    4. List \n    5. Add \n    6. Remove");
    System.out.println("To manage Bookings: \n    7. List \n    8. Add \n    9. Remove \n    10. Conclude");
    System.out.println("After selecting one the options above, you will be presented other screens. \nIf you press 0, you will be able to return to this main menu. \nPress -1 (or ctrl+c) to quit this application.\n");;
    boolean t = true;
    /**
     *Using a try catch to catch if the user inputs something else than an Integer
     *Using the switch case instead of the if loops and if the Integer input isn't -1 or 1-10 then it will ask the user to input again
     *All surrounded in a while(true) loop so that it keeps asking the user to input a valid input until he does
     */
    while(t==true){
      try{
        int decision = input.nextInt();
        switch(decision){
          case -1:
            System.exit(0);
            break;
          case 1:
            t=false;
            screen1();
            break;
          case 2:
            t=false;
            screen2();
            break;
          case 3:
            t=false;
            screen3();
            break;
          case 4:
            t=false;
            screen4();
            break;
          case 5:
            t=false;
            screen5();
            break;
          case 6:
            t=false;
            screen6();
            break;
          case 7:
            t=false;
            screen7();
            break;
          case 8:
            t=false;
            screen8();
            break;
          case 9:
            t=false;
            screen9();
            break;
          case 10:
            t=false;
            screen10();
            break;
          default:
            System.out.println("INVALID INPUT. Please input -1 or a number from 1 to 10");
            decision = input.nextInt();
            break;
          }
        }
      catch(InputMismatchException e){
        System.out.println("INVALID INPUT. Please input -1 or a number from 1 to 10");
      }
    }
  }
  public static void screen1(){
    /*
    When user selects 1, the screen is cleared and a list of bookable rooms is shown
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    System.out.println(bookingSystem.printBookableRooms());
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    //All screens have most of their code in a while true loop so that it keeps asking the user to input values until
    //they stops with ctrl + c or -1

    boolean t = true;
    while(t==true){
      try{
        int decision = input.nextInt();
        switch(decision){
          case 0:
            t=false;
            mainScreen();
            break;
          case -1:
            System.exit(0);
            break;
        }
      }
      catch(InputMismatchException e){
        System.out.println("please input numbers 1-10");
      }
    }
  }
  public static void screen2(){
    /*
    When the user selects 2, this screen shows, and allows them to add a Bookable room
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test \n\nAdding bookable room \n");
    System.out.println(bookingSystem.printBookableRooms());
    System.out.println("Please enter the following: \n"); //The specification has "one of the following but the user needs to add all of them to have a bookable room"
    System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    while(true){
        Scanner input1 = new Scanner(System.in);
        String decision = input1.nextLine();
        //I have decided instead of writing out the code multiple times I can just created a method that checks the room input
        int roomNumber = checkRoomInput(decision);
        if(roomNumber != -1){
          Bookable_Room newBR = new Bookable_Room(decision.substring(6, 21), bookingSystem.getRoom(roomNumber));
          bookingSystem.addBookableRoom(newBR);
          System.out.println("Bookable Room added successfully:\n" + newBR.printBookableRoom());
          System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), separated by a white space.");
          System.out.println("0. Back to main menu. \n-1. Quit application.");
        }
      }
    }
  public static void screen3(){
    /*
    when user chooses 3, this screen shows and allows them to deleted any bookable rooms
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    for(int i=0; i<bookingSystem.getBookableRooms().size();i++){
      if(bookingSystem.getBookableRoom(i).getStatus().toString().equals("EMPTY")) {
        System.out.println(bookingSystem.getBookableRoom(i).printBookableRoom());
      }
    }
    System.out.println("Removing bookable room \n\nPlease, enter the following:\n");
    System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    boolean t = true;
    while(t==true){
      Scanner input1 = new Scanner(System.in);
      String decision = input1.nextLine();
      int roomNumber = checkRoomInput(decision);
      if(roomNumber != -1){
        for(int i=0; i<bookingSystem.getBookableRooms().size(); i++){
          if(bookingSystem.getBookableRoom(i).bookableRoomCheck(decision.substring(6, 21), bookingSystem.getRoom(roomNumber)) == true){
            roomNumber = i;
          }
        }
        System.out.println("Bookable Room Removed successfully:\n" + bookingSystem.getBookableRoom(roomNumber).printBookableRoom());
        bookingSystem.removeBookableRoom(roomNumber);
        System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), separated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
      }
      }
  }
  public static void screen4(){
    /*
    when user selects 4, this screen shows and lists out the assistants on shift
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    System.out.println(bookingSystem.printAvailableAssistants());
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    boolean t = true;
    while(t==true){
      try{
        int decision = input.nextInt();
        switch(decision){
          case 0:
            t=false;
            mainScreen();
            break;
          case -1:
            System.exit(0);
            break;
        }
      }
      catch(InputMismatchException e){
        System.out.println("please input numbers 1-10");
      }
    }
  }
  public static void screen5(){
    /*
    when user selects 5, this screen allows them to add any assistants on shift
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test \n\nAdding assistants on shift\n");
    System.out.println(bookingSystem.printAvailableAssistants());
    System.out.println("Please enter the following: \n"); //The specification has "one of the following but the user needs to add all of them to have a bookable room"
    System.out.println("The sequential ID (email) of an Assistant and a date (dd/mm/yyyy) \nseparated by a white space.");
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    boolean t = true;
    while(t==true){
      Scanner input1 = new Scanner(System.in);
      String decision = input1.nextLine();
      //Just like with the check room input I have made one for assistants
      int studentNumber = checkAssistantInput(decision);
      if(studentNumber != -1){
        Available_Assistant newAA = new Available_Assistant(decision.substring(16, 26), bookingSystem.getAssistant(studentNumber));
        bookingSystem.addAvailableAssistant(newAA);
        System.out.println("Assistant on shift added successfully:\n" + newAA.printAvailableAssistant());
        System.out.println("The sequential ID (email) of an Assistant, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
      }
    }
  }
  public static void screen6(){
    /*
    whenthe user selects 6, this screen shows and allows them to delete any assistants on shift. if they are free
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    for(int i=0; i<bookingSystem.getAvailableAssistants().size();i++){
      if(bookingSystem.getAvailableAssistant(i).getStatus().toString().equals("FREE")) {
        System.out.println(bookingSystem.getAvailableAssistant(i).printAvailableAssistant());
      }
    }
    System.out.println("Removing assistant on shift \n\nPlease, enter the following:\n");
    System.out.println("The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
    System.out.println("0. Back to main menu. \n-1. Quit application.\n");
    boolean t = true;
    while(t==true){
      Scanner input1 = new Scanner(System.in);
      String decision = input1.nextLine();
      int studentNumber = checkAssistantInput(decision);
      if(studentNumber != -1){
        for(int i=0; i<bookingSystem.getAvailableAssistants().size(); i++){
          if(bookingSystem.getAvailableAssistant(i).availableAssistantCheck(decision.substring(16, 26), bookingSystem.getAssistant(studentNumber)) == true){
            studentNumber = i;
          }
        }
        System.out.println("Assistant on shift Removed successfully:\n" + bookingSystem.getAvailableAssistant(studentNumber).printAvailableAssistant());
        bookingSystem.removeAvailableAssistant(studentNumber);
        System.out.println("The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
      }
      }
  }
  public static void screen7(){
    /*
    when the user selects 7, this screen shows and allows them to see all the bookings
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test");
    System.out.println(bookingSystem.printBookings());
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    boolean t = true;
    while(t==true){
      try{
        int decision = input.nextInt();
        switch(decision){
          case 0:
            t=false;
            mainScreen();
            break;
          case -1:
            System.exit(0);
            break;
        }
      }
      catch(InputMismatchException e){
        System.out.println("please input numbers 1-10");
      }
    }
  }
  public static void screen8(){
    /*
    when the user selects 8, this screen shows and allows them to add bookings
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n\nAdding booking (appointment for a COVID test) to the system\n");
    System.out.println("List of available time-slots:");
    for (int i=0; i<bookingSystem.getBookableRooms().size(); i++){
      int id=bookingSystem.getBooking(bookingSystem.getBookings().size()-1).getIDCode()+1+i;
      if((bookingSystem.isFreeAvailableAssistant() == true) && !(bookingSystem.getBookableRoom(i).getStatus().toString().equals("FULL"))){
        System.out.println(id + ". " + bookingSystem.getBookableRoom(i).getTimeString());
      }
    }
    System.out.println("\nPlease, enter the following:\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
    System.out.println("0. Back to main menu. \n-1. Quit application.\n");
    boolean t = true;
    while(t==true){
      Scanner input1 = new Scanner(System.in);
      String decision = input1.nextLine();
      if(checkBookingInput(decision) == true){
        int temp = Integer.parseInt(decision.substring(0,2)) - bookingSystem.getBooking(bookingSystem.getBookings().size()-1).getIDCode()-1;
        int temp1 = -1;
        int tot = bookingSystem.getBooking(bookingSystem.getBookings().size()-1).getIDCode()+1;
        Booking newB = new Booking(bookingSystem.getBooking(bookingSystem.getBookings().size()-1).getIDCode()+1, decision.substring(2, decision.length()), bookingSystem.getBookableRoom(temp), bookingSystem.getFreeAvailableAssistant());
        bookingSystem.addBooking(newB);
        bookingSystem.getFreeAvailableAssistant().changeStatus();
        System.out.println("Booking added successfully:\n" + newB.printBooking());
        System.out.println("List of available time-slots:");
        for (int i=0; i<bookingSystem.getBookableRooms().size(); i++){
          int id=bookingSystem.getBooking(bookingSystem.getBookings().size()-1).getIDCode()+1+i;
          if(bookingSystem.isFreeAvailableAssistant() == true){
            System.out.println(id + ". " + bookingSystem.getBookableRoom(i).getTimeString());
          }
        }
        System.out.println("\nPlease, enter the following:\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
      }
    }
  }
  public static void screen9(){
    /*
    when the user selects 9, this screen shows and allows them to delete any bookings as long as they are scheduled and not completed
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    for(int i=0; i<bookingSystem.getBookings().size();i++){
      if(bookingSystem.getBooking(i).getStatus().toString().equals("SCHEDULED")) {
        System.out.println(bookingSystem.getBooking(i).printBooking());
      }
    }
    System.out.println("Removing booking from the system\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
    System.out.println("0. Back to main menu. \n-1. Quit application.");

    boolean t = true;
    while(t==true){
      Scanner input1 = new Scanner(System.in);
      String decision = input1.nextLine();
      int x=2;//a number so that if the user inputs something wrong it will not do anything
      try{
         x = Integer.parseInt(decision);
      }
      catch(NumberFormatException e){
        System.out.println("ERROR!\nYou have not entered a integer. Please try again.\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
        t=false;
      }
      if(t==true){
        if(x==-1){
          System.exit(0);
        }
        else if(x == 0){
          mainScreen();
        }
        else{
          for(int i=0; i<bookingSystem.getBookings().size();i++){
            if(bookingSystem.getBooking(i).getStatus().toString().equals("SCHEDULED")) {
              if(bookingSystem.getBooking(i).getIDCode() == x){
                System.out.println("Booking removed successfully. \n" + bookingSystem.getBooking(i).printBooking());
                bookingSystem.removeBooking(i);
              }
            }
          }
          if(x==2){
            System.out.println("ERROR! \nYou haven't entered a valid number. Please try again.\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
            System.out.println("0. Back to main menu. \n-1. Quit application.");
            t=false;
          }
        }
        System.out.println("Please, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
        t=true;
      }
    }
  }
  public static void screen10(){
    /*
    if the user selects 10, this screen shows and allows them to change the status of the booking from scheduled to completed
    */
    clearScreen();
    System.out.println("University of Knowledge - COVID test\n");
    for(int i=0; i<bookingSystem.getBookings().size();i++){
      if(bookingSystem.getBooking(i).getStatus().toString().equals("SCHEDULED")) {
        System.out.println(bookingSystem.getBooking(i).printBooking());
      }
    }
    System.out.println("Conclude booking\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be completed.");
    System.out.println("0. Back to main menu. \n-1. Quit application.");
    boolean t = true;
        while(t==true){
          Scanner input1 = new Scanner(System.in);
          String decision = input1.nextLine();
          int x=2;//a number so that if the user inputs something wrong it will not do anything
          try{
             x = Integer.parseInt(decision);
          }
          catch(NumberFormatException e){
            System.out.println("ERROR!\nYou have not entered a integer. Please try again.\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
            System.out.println("0. Back to main menu. \n-1. Quit application.");
            t=false;
          }
          if(t==true){
            if(x==-1){
              System.exit(0);
            }
            else if(x == 0){
              mainScreen();
            }
            else{
              for(int i=0; i<bookingSystem.getBookings().size();i++){
                if(bookingSystem.getBooking(i).getStatus().toString().equals("SCHEDULED")) {
                  if(bookingSystem.getBooking(i).getIDCode() == x){
                    bookingSystem.getBooking(i).setStatus(Status.COMPLETED);
                    System.out.println("Booking completed successfully.\n" + bookingSystem.getBooking(i).printBooking());
                  }
                }
              }
              if(x==2){
                System.out.println("ERROR! \nYou haven't entered a valid number. Please try again.\n\nPlease, enter the following:\n\nThe sequential ID to select the booking to be removed from the listed bookings above.");
                System.out.println("0. Back to main menu. \n-1. Quit application.");
                t=false;
              }
            }
            System.out.println("Please, enter the following:\n\nThe sequential ID to select the booking to be completed.");
            System.out.println("0. Back to main menu. \n-1. Quit application.");
            t=true;
          }
        }
  }
  public static int checkRoomInput(String decision){
    /*
    This method is passed the user input and it makes sure that every part of it is acceptable and
    that if any errors are there, they will be caught and the user will have another chance to input
    */
    int roomNumber = -1;
    if (decision.length()<6){
      try{
        if(Integer.parseInt(decision)==0){
          mainScreen();
        }
        else if(Integer.parseInt(decision)== -1){
          System.exit(0);
        }
        else{
          System.out.println("ERROR!\nyour inputed number is too short for a date and isn't 0 or -1. \n\n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
          System.out.println("0. Back to main menu. \n-1. Quit application.");
          return -1;
        }
      }
      catch(NumberFormatException e){
        System.out.println("ERROR!\nyour input is too short for a date and isn't a integer. \n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
        return -1;
      }
    }
    else if(!((decision.substring(decision.length()-5, decision.length()).equals("07:00"))|| (decision.substring(decision.length()-5, decision.length()).equals("08:00")) || (decision.substring(decision.length()-5, decision.length()).equals("09:00")))) {
      System.out.println("ERROR!\nThe time you have given isn't valid \n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), separated by a white space.");
      System.out.println("0. Back to main menu. \n-1. Quit application.");
      return -1;
    }
    else{
      try{
        Date time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        time = dateFormat.parse(decision.substring(6, 21));
      }
      catch(ParseException e){
        System.out.println("ERROR!\nyour input has some unknown characters. Please try again \n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
        return -1;
      }
      catch(StringIndexOutOfBoundsException e){
        System.out.println("ERROR!\nyour input is too short. Please try again.\n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.");
        return -1;
      }
    for (int i=0; i<bookingSystem.getRooms().size(); i++){
      if(decision.substring(0,5).equals(bookingSystem.getRoom(i).getCode())){
        roomNumber=i;
      }
    }

    if(roomNumber == -1){
      System.out.println("ERROR!\nThe code of the room is not in this system. Please try again \n The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), \nseparated by a white space.");
      System.out.println("0. Back to main menu. \n-1. Quit application.");
      return -1;
    }
  }
  return roomNumber;
  }
  public static int checkAssistantInput(String decision){
    /*
    This method works very similarly to the previosu method with the exception that it is for assistants and not rooms
    */
    int studentNumber = -1;
    if (decision.length()<6){
      try{
        if(Integer.parseInt(decision)==0){
          mainScreen();
        }
        else if(Integer.parseInt(decision)== -1){
          System.exit(0);
        }
        else{
          System.out.println("ERROR!\nyour inputed number is too short for a date and isn't 0 or -1. \n\n The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
          System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        }
      }
      catch(NumberFormatException e){
        System.out.println("ERROR!\nyour input is too short for a date and isn't a integer. \n The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
      }
    }
    else{
      try{
        Date time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        time = dateFormat.parse(decision.substring(16, 26));
      }
      catch(ParseException e){
        System.out.println("ERROR!\nyour input has some unknown characters. Please try again \n The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        return -1;
      }
      catch(StringIndexOutOfBoundsException e){
        System.out.println("ERROR!\nyour input is too short. Please try again.\n The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        return -1;
      }
      for (int i=0; i<bookingSystem.getAssistants().size(); i++){
        if(decision.substring(0,15).equals(bookingSystem.getAssistant(i).getEmail())){
          studentNumber=i;
        }
      }

      if(studentNumber == -1){
        System.out.println("ERROR!\nThe code of the room is not in this system. Please try again \n The sequential ID of an assistant and date (dd/mm/yyyy)\nseparated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        return -1;
      }
    }
  return studentNumber;
  }
  public static boolean checkBookingInput(String decision){
    /*
    Again, this method utilized similar checks to the previous ones to make sure the inputed data is valid
    */
    if (decision.length()<3){
      try{
        if(Integer.parseInt(decision)==0){
          mainScreen();
        }
        else if(Integer.parseInt(decision)== -1){
          System.exit(0);
        }
        else{
          System.out.println("ERROR!\nI do not recognize this command please try again. \n\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
          System.out.println("0. Back to main menu. \n-1. Quit application.\n");
          return false;
        }
      }
      catch(NumberFormatException e){
        System.out.println("ERROR!\nYour input didnt start with an integer. Please try again. \n\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        return false;
      }
      }
    else{
      try{
        int bookingNumber = Integer.parseInt(decision.substring(0,2));
      }
      catch(NumberFormatException e){
        System.out.println("ERROR!\nThe sequential ID of a time-slot is invalid. Please try again.\nThe sequential ID of an available time-slot and the student email, separated by a white space.");
        System.out.println("0. Back to main menu. \n-1. Quit application.\n");
        return false;
      }
      if(decision.substring(decision.length()-9).equals("uok.ac.uk")){
        return true;
      }
    }
    return false;
  }
}
