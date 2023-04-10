/**
 * This is the room class, instances of specific rooms are created so that people know
 * where to go
 * @author jakubdavison
 */
public class Room{
  private String code;
  private int capacity;

  Room(){

  }
  Room(String codeR, int cap){
    if (cap > 0){
      this.code = codeR;
      this.capacity = cap;
    }
  }
  public void setCode(String codeR){
    this.code = codeR;
  }
  public String getCode(){
    return this.code;
  }
  public void setCapacity(int cap){
    this.capacity = cap;
  }
  public int getCapacity(){
    return this.capacity;
  }
  public String printRoom(){
    return "| " + this.code + " | capacity: " + this.capacity + " |";
  }
}
