/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 *
 * @author kiodi
 */
public class Panel extends JPanel {
    private static JPopupMenu pop;
    public Panel(){
        pop.addPopupMenuListener(new Panel.PopupPrintListener());
        addMouseListener(new MousePopupListener());
    }
    public static void setPop(JPopupMenu pop){
        Panel.pop = pop;
    }
    public JPopupMenu getPop(){
        return Panel.pop;
    }
    class MousePopupListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
          checkPopup(e);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
          checkPopup(e);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
          checkPopup(e);
        }

        private void checkPopup(MouseEvent e) {
          if (e.isPopupTrigger()) {
            pop.show(Panel.this, e.getX(), e.getY());
          }
        }
    }

  // An inner class to show when popup events occur
    class PopupPrintListener implements PopupMenuListener {
        private int counter = 0;
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            counter++;
        }
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            counter++;
        }
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            counter++;
        }
    }

    
}
