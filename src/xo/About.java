/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

/**
 *
 * @author kiodi
 */
public class About{
    private final JFrame form = new JFrame();
    /**
     * Creates new form About
     */
    public About() {
        this.form.setSize(300,180);
        this.form.setResizable(false);
        this.form.setTitle("About");
        this.form.setLocationRelativeTo(null);
        
        ImageIcon s = new ImageIcon("images/about.png");
        JLabel label = new JLabel();
        label.setIcon(s);
        this.form.add(label);
    }
    
    public void setVisible(boolean arg){
        this.form.setVisible(arg);
    }
}
