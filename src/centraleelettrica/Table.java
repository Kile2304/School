package centraleelettrica;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author LENOVO
 */
class Table extends JPanel implements ActionListener {

    private String text[][];
    private int numero;
    private boolean isCentrale;
    final Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);

    private static int PIXEL = 64;

    private String[] columnName;
    
    private Gui gui;

    private static final String QUERY = "select * from ";

    public Table(Gui gui) {
        isCentrale = true;
        this.gui = gui;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        removeAll();

        g.setFont(font);
        Color c = null;
        try {
            Robot robot = new Robot();
            c = robot.getPixelColor(getWidth() / 2, getHeight() / 2);
        } catch (AWTException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] large = getSpace(g);

        for (int i = 0; i < columnName.length; i++) {
            int actualWidth = g.getFontMetrics().stringWidth(columnName[i]);
            if (actualWidth > large[i]) {
                large[i] = actualWidth;
            }
        }

        int tot = 0;
        for (int i = 0; i < numero + 1; i++) {
            String[] temp = i == 0 ? columnName : text[i - 1];

            tot = 0;
            int y = i * PIXEL + (PIXEL / 4 * 3);
            
            String valori = "";
            for (int j = 0; j < columnName.length; j++) {
                g.drawLine(tot, i * PIXEL, tot, (i + 1) * PIXEL);   //VERTICALE

                tot += large[j] / 4;

                int x = tot;

                int actualWidth = (large[0] - g.getFontMetrics().stringWidth(temp[j])) / 2;
                int centramento = actualWidth > 0 ? actualWidth : 0;

                g.drawString(temp[j], x + centramento, y);
                tot += large[j] + large[j] / 4;
                valori += temp[j]+" ";
            }
            if (i != 0) {

                JButton delete = new JButton("-");

                String where = null;
                for (int k = 0; k < temp.length; k++) {
                    where += columnName[k] + "=" + temp[k];
                }
                delete.setActionCommand("delete * from " + Gui.table + " where " + where);

                delete.setMargin(null);
                delete.setBounds(tot + PIXEL / 3 * 2, y - PIXEL / 2, PIXEL * 2, PIXEL / 2);
                delete.setFont(new Font("default", Font.BOLD, font.getSize()));

                delete.addActionListener(this);

                add(delete);

                JButton modify = new JButton("+");
                
                modify.setActionCommand(valori);
                modify.setFont(new Font("default", Font.BOLD, 13));
                modify.setMargin(null);
                modify.setBounds(tot + PIXEL / 3 * 10, y - PIXEL / 2, PIXEL * 2, PIXEL / 2);
                modify.addActionListener(this);

                add(modify);
            }

            g.drawLine(tot, i * PIXEL, tot, (i + 1) * PIXEL);   //VERTICALE
            g.drawLine(0, (i + 1) * PIXEL, tot, (i + 1) * PIXEL);
        }

        setPreferredSize(new Dimension(tot + 1 + PIXEL * 6, text.length * PIXEL));

    }

    private int[] getSpace(Graphics g) {
        if (text.length > 0) {
            int column = text.length;

            int max[] = new int[column];

            for (int i = 0; i < text.length; i++) {
                String[] temp = text[i];
                for (int j = 0; j < temp.length; j++) {
                    int actualWidth = g.getFontMetrics().stringWidth(temp[j]);

                    if (actualWidth > max[j]) {
                        max[j] = actualWidth;
                    }
                }
            }
            PIXEL = g.getFontMetrics().getHeight();
            int colPix = 0;

            for (int i = 0; i < column; i++) {
                colPix += max[i];
            }
            setPreferredSize(new Dimension(max.length * PIXEL + colPix + max[0] / 2, numero * PIXEL));

            return max;
        }
        int[] max = new int[columnName.length];
        for (int i = 0; i < max.length; i++) {
            max[i] = 0;
        }
        return max;
    }

    public void changeEnable(boolean aggiorna) {
        isCentrale = Boolean.logicalXor(isCentrale, true);
        if (aggiorna) {
            aggiornaTabella(null);
        }
    }

    public void aggiornaTabella(Relazione rel) {
        if (rel == null) {
            rel = new Query().sendSelect(QUERY + Gui.table);
        }

        columnName = rel.getColumnName();
        text = rel.getValue();

        numero = text.length;
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] c = e.getActionCommand().split(" ");
        Relazione rel = null;
        if (c.equals("delete")) {
            new Query().sendRemove(e.getActionCommand());
            rel = new Query().sendSelect("select * from "+Gui.table);
        } else {
            gui.eastInsertCentrale(columnName, e.getActionCommand());
        }
    }

}
