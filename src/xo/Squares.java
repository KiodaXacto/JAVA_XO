/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author kiodi
 */
public class Squares extends javax.swing.JLabel{
    private int[] cordonates = new int[2];
    public void setCordonates(int[] c){
        cordonates = c;
    }
    
    public void setCordonates(int x, int y){
        cordonates[0] = x;
        cordonates[1] = y;
    }

    public void setSquare(XO a,char c){
        a.setSquare(cordonates[0], cordonates[1], c);
    }

    public Squares(){
        super();
        this.setSize(100, 100);
        this.setOpaque(true);
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        this.setBorder(loweredetched);
    }


   
}
