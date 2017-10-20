package centraleelettrica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public final class InsertPanel extends JPanel {

    private final Gui g;

    private static final JButton indietro = new JButton("Indietro");

    private ArrayList<JTextArea> listaAree;

    public InsertPanel(String[] columnName, String valori, final Gui g) {
        super();
        //setPreferredSize(new Dimension(300, 100));
        this.g = g;

        indietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.eastNormal();
            }

        });

        listaAree = new ArrayList<>();

        interfaccia(columnName, valori);

    }

    public void interfaccia(String[] columnName, String valori) {

        setLayout(new GridLayout(columnName.length * 2, 2));

        String[] valoriScomposti = valori.split(" ");
        for (int i = 0; i < columnName.length; i++) {
            add(new JLabel(columnName[i]));
            JTextArea area = new JTextArea(valoriScomposti[i]);
            listaAree.add(area);
            add(area);
        }

        JButton b = new JButton("query");
        b.addActionListener((ActionEvent e) -> {
            
            Query q = new Query();
            q.sendRemove(Query.toDelete(valoriScomposti, columnName));
            
            /*q = new Query();
            q.sendInsert(Query.toInsert(valoriScomposti, listaAree));*/
            g.eastNormal();
        });

        add(b);
        add(indietro);
    }

}
