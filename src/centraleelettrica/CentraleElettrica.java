package centraleelettrica;

/**
 *
 * @author mantini.christian
 */
public class CentraleElettrica {

    public void action(String command) {
       switch(command.split(" ")[0].toLowerCase()){
           case "select":
               break;
           case "delete":
               break;
           case "insert":
               break;
       }
    }
    
    public static void main(String[] args) {
        //new CentraleElettrica();
        new Gui();
        //new Query().sendSelect("select * from employees");
        //new Query().getTable();
    }

}
