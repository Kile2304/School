package centraleelettrica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public final class InsertPanel extends JPanel {

    private final String command;
    private final Gui g;

    private final boolean remove;

    private JTextField primo, secondo, terzo, quarto, quinto;
    
    private static final JButton indietro = new JButton("Indietro");

    public InsertPanel(String command, final Gui g) {
        super();
        //setPreferredSize(new Dimension(300, 100));
        this.command = command;
        this.g = g;
        
        indietro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                g.eastNormal();
            }
            
        });

        String com[] = command.split(" ");

        remove = com[0].equals("remove");

        if (com[1].equals("centrale")) {
            centrale();
        } else {
            production();
        }
    }

    public void centrale() {

        setLayout(new GridLayout(2, 2));

        add(new JLabel("Inserisci il codice"));
        primo = new JTextField();
        add(primo);

        if (!remove) {
            setLayout(new GridLayout(6, 2));
            add(new JLabel("Inserisci l'indirizzo"));
            secondo = new JTextField();
            add(secondo);

            add(new JLabel("Inserisci la provincia"));
            terzo = new JTextField();
            add(terzo);

            add(new JLabel("Inserisci la potenza"));
            quarto = new JTextField();
            add(quarto);

            add(new JLabel("Inserisci se e' attivo"));
            quinto = new JTextField();
            add(quinto);
        }
        JButton b = new JButton("query");
        b.addActionListener((ActionEvent e) -> {
            /*CentraleElettrica ce = new CentraleElettrica(true);
            if (!remove) {
                ce.action(command + " " + primo.getText() + " " + secondo.getText() + " " + terzo.getText() + " " + quarto.getText() + " " + quinto.getText());
            } else {
                ce.action(command + " " + primo.getText());
            }
            g.getTable().aggiornaTabella();*/
        });
        
        add(b);
        add(indietro);
    }

    public void production() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Inserisci il codice"));
        primo = new JTextField();
        add(primo);

        add(new JLabel("Inserisci l'anno"));
        secondo = new JTextField();
        add(secondo);

        add(new JLabel("Inserisci il mese"));
        terzo = new JTextField();
        add(terzo);

        if (!remove) {
        setLayout(new GridLayout(5, 2));
            add(new JLabel("Inserisci la potenza"));
            quarto = new JTextField();
            add(quarto);
        }
        JButton b = new JButton("query");
        b.addActionListener((ActionEvent e) -> {
            /*CentraleElettrica ce = new CentraleElettrica(true);
            if (!remove) {
                ce.action(command + " " + primo.getText() + " " + secondo.getText() + " " + terzo.getText() + " " + quarto.getText());
            } else {
                ce.action(command + " " + primo.getText() + " " + secondo.getText() + " " + terzo.getText());
                
            }
            g.getTable().aggiornaTabella();*/
        });

        add(b);
        add(indietro);
    }

}
