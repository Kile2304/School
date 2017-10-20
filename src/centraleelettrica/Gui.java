package centraleelettrica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author LENOVO
 */
public class Gui extends JFrame {

    private final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; //VARIABILE PER OTTENERE LA LARGHEZZA E ALTEZZA MASSIMA DEL FRAME
    private final Listener listener = new Listener(this);
    private Table t;
    private JTextArea cmd;

    private JPanel east;

    private JPanel west;

    private static Gui g;

    private boolean isNormal;

    private static final int TESTHEIGHT = 1080;

    private JScrollPane scroll;

    public static String table = "";

    public static JTextArea console;

    public Gui() {
        super("Centrale Idroelettrica");

        g = this;
        isNormal = true;

        setLayout(new BorderLayout());

        device.setFullScreenWindow(this);
        int width = device.getFullScreenWindow().getWidth();
        int height = device.getFullScreenWindow().getHeight();
        setSize(width, height);

        west = west();
        add(west, BorderLayout.WEST);

        east = new JPanel();

        console = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        console.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        console.setCaretPosition(0);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setAutoscrolls(true);
        console.setEditable(false);

        JScrollPane roll = new JScrollPane(console);
        roll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        eastNormal();

        t = new Table(this);
        t.aggiornaTabella(null);
        t.setPreferredSize(new Dimension(width, height));

        scroll = new JScrollPane(t);
        scroll.setPreferredSize(new Dimension(width, height));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(scroll.getWidth(), scroll.getHeight()));

        center.add(scroll, BorderLayout.CENTER);

        roll.setPreferredSize(new Dimension(scroll.getWidth(), adaptHeight(200)));
        console.setPreferredSize(new Dimension(scroll.getWidth(), adaptHeight(500)));
        center.add(roll, BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void changeEnable(boolean aggiorna, String table) {
        Gui.table = table;
        if (!table.equals("")) {
            Relazione rel = new Query().sendSelect("select * from " + table);
            t.aggiornaTabella(rel);
        }
    }

    private JPanel eastNorth() {
        JPanel panel = new JPanel();

        //panel.setPreferredSize(new Dimension(400, 720));
        panel.setPreferredSize(new Dimension(350, adaptHeight(720)));
        //panel.setPreferredSize(new Dimension(east.getWidth(), 720));
        panel.setLayout(new BorderLayout());

        panel.add(new JLabel("shell"), BorderLayout.NORTH);
        cmd = new JTextArea();
        cmd.setLineWrap(true);
        cmd.setAutoscrolls(true);

        JScrollPane scroll = new JScrollPane(cmd);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll, BorderLayout.CENTER);

        JButton command = new JButton("query");
        command.addActionListener((ActionEvent e) -> {
            CentraleElettrica ce = new CentraleElettrica();
            ce.action(cmd.getText());
            Relazione rel = ce.getRelazione();
            t.aggiornaTabella(rel);
        });
        panel.add(command, BorderLayout.SOUTH);

        return panel;
    }

    public void eastNormal() {
        east.removeAll();
        east.setLayout(new BorderLayout());
        add(east, BorderLayout.EAST);

        JPanel eastNorth = eastNorth();
        east.add(eastNorth, BorderLayout.NORTH);

        /*JPanel eastSouth = eastSouth();
        east.add(eastSouth, BorderLayout.SOUTH);*/
        revalidate();
        repaint();
    }

    public void eastInsertCentrale(String[] name, String valori) {
        if (isNormal) {

            east.removeAll();

            InsertPanel p = new InsertPanel(name, valori, this);
            p.setPreferredSize(new Dimension(east.getWidth(), east.getHeight() / 3));
            east.add(p, BorderLayout.NORTH);

            revalidate();
            repaint();
        } else {
            eastNormal();
        }
    }

    /*private JPanel eastSouth() {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        JButton insertCentrale = new JButton("Insert Centrale");
        insertCentrale.addActionListener((ActionEvent e) -> {
            g.eastInsertCentrale(true, false);
        });
        panel.add(insertCentrale, BorderLayout.NORTH);

        JButton insertProduzione = new JButton("Insert Produzione");
        insertProduzione.addActionListener((ActionEvent e) -> {
            g.eastInsertCentrale(false, false);
        });
        panel.add(insertProduzione, BorderLayout.SOUTH);

        JButton removeCentrale = new JButton("    Remove Centrale    ");
        removeCentrale.addActionListener((ActionEvent e) -> {
            g.eastInsertCentrale(true, true);
        });
        panel.add(removeCentrale, BorderLayout.WEST);

        JButton removeProduzione = new JButton("Remove Produzione");
        removeProduzione.addActionListener((ActionEvent e) -> {
            g.eastInsertCentrale(false, true);
        });
        panel.add(removeProduzione, BorderLayout.EAST);

        return panel;

    }*/
    private JPanel west() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        String[] tableList = new Query().getTable();

        Gui.table = tableList[0];

        for (int i = 0; i < tableList.length; i++) {
            JButton centrale = new JButton(tableList[i]);
            centrale.addActionListener(listener);
            centrale.setEnabled(!tableList.equals(tableList[0]));
            centrale.setBorder(null);
            panel.add(centrale);
        }

        return panel;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public Table getTable() {
        return t;
    }

    private static final int adaptHeight(int val) {
        return device.getFullScreenWindow().getHeight() * val / TESTHEIGHT;
    }

    public JPanel getPanelWest() {
        return west;
    }

}
