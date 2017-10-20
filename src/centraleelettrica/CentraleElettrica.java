package centraleelettrica;

/**
 *
 * @author mantini.christian
 */
public class CentraleElettrica {

    private Relazione rel;
    
    public void action(String command) {
       Query q = new Query();
       Table.personalized = true;
       new Thread(){
           public void run(){
               Listener.change("", false);
           }
       }.start();
       switch(command.split(" ")[0].toLowerCase()){
           case "select":
               rel = q.sendSelect(command);
               if(rel != null){
                   Gui.console.setText(command+"\nEseguito con successo");
               }
               break;
           case "delete":
               rel = null;
               q.sendRemove(command);
               break;
           case "insert":
               rel = null;
               q.sendInsert(command);
               break;
       }
    }
    
    public Relazione getRelazione(){
        return rel;
    }
    
    public static void main(String[] args) {
        //new CentraleElettrica();
        new Gui();
        //new Query().sendSelect("select * from employees");
        //new Query().getTable();
    }

}
