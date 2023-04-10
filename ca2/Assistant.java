/**
 * This class generates the assistants, uses their names and emails
 * @author jakubdavison
 */
public class Assistant{
  private String email;
  private String name;

  Assistant(String mail, String nameA){
    if(mail.substring(mail.length()-9).equals("uok.ac.uk")){
      if(!nameA.equals("")){
        this.email = mail;
        this.name = nameA;
      }
    }
  }

  public void setEmail(String mail){
    this.email = mail;
  }
  public String getEmail(){
    return this.email;
  }
  public void setName(String nameA){
    this.name = nameA;
  }
  public String getName(){
    return this.name;
  }
  public String printAssistant(){
    return "| " + this.name + " | " + this.email + " |";
  }
}
