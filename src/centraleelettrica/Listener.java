package centraleelettrica;

import java.awt.Component;
import java.awt.Container;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;

/**
 *
 * @author LENOVO
 */
public class Listener implements ActionListener {

    private static Gui gui;

    public Listener(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Table.personalized = false;
        change(e.getActionCommand(), true);
    }

    public static void change(String premuto, boolean aggiorna) {
        Component[] comp = gui.getPanelWest().getComponents();
        new Thread(){
            public void run(){
                gui.changeEnable(aggiorna, premuto);
            }
        }.start();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                JButton temp = (JButton) comp[i];
                if (temp.getText().equals(premuto)) {
                    temp.setEnabled(false);
                } else {
                    temp.setEnabled(true);
                }
            }
        }
    }

}
