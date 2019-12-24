/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public final class Interface  {
    private final JFrame fenetre = new JFrame();
    public Interface() {
        this.squares = new LinkedList<>();
        this.theMAP = new LinkedList<>();
        this.againstRobot = false;
        JPopupMenu pop = new JPopupMenu();
        JMenuItem item;
        item = new JMenuItem("Rematch");
        pop.add(item);
        item.addActionListener((ActionEvent e) -> clear());
        pop.addSeparator();
        item = new JMenuItem("Exit");
        pop.add(item);
        item.addActionListener((ActionEvent e) -> System.exit(0));
        pop.setLabel("Justification");
        pop.setBorder(new BevelBorder(BevelBorder.RAISED));
        Panel.setPop(pop);
        initComponents();
        this.turn = 0;
        this.algorithm = new XO();
        this.xo = new ImageIcon[2];
        this.xo[0] = new ImageIcon("images/x.png");
        this.xo[1] = new ImageIcon("images/O.png");
        xoString = new String[2];
        xoString[0] = "X";
        xoString[1] = "O";
        
        initialisation();
        this.r = new Robot(theMAP,this.algorithm);
    }
    public void initialisation(){
        
        this.fenetre.setResizable(false);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setTitle("XO");
        
        

        this.algorithm.stringToMap("---\n---\n---");
        
        this.squareS00.setCordonates(0,0);
        this.theMAP.add(squareS00);
        this.squareS00.setText("");
            squareS00.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS01.setCordonates(0,1);
        this.theMAP.add(squareS01);
        this.squareS01.setText("");
            squareS01.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS02.setCordonates(0,2);
        this.theMAP.add(squareS02);
        this.squareS02.setText("");
            squareS02.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        
        this.squareS10.setCordonates(1,0);
        this.theMAP.add(squareS10);
        this.squareS10.setText("");
            squareS10.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS11.setCordonates(1,1);
        this.theMAP.add(squareS11);
        this.squareS11.setText("");
            squareS11.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS12.setCordonates(1,2);
        this.theMAP.add(squareS12);
        this.squareS12.setText("");
            squareS12.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        
        this.squareS20.setCordonates(2,0);
        this.theMAP.add(squareS20);
        this.squareS20.setText("");
            squareS20.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS21.setCordonates(2,1);
        this.theMAP.add(squareS21);
        this.squareS21.setText("");
            squareS21.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
        this.squareS22.setCordonates(2,2);
        this.theMAP.add(squareS22);
        this.squareS22.setText("");
            squareS22.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sClick(e,this);
                } 
            });
    }
    public int pupUp(String message,String title,Icon c){
        return  JOptionPane.showConfirmDialog(this.fenetre,
                    message,
                    title,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    c
                    );
    }
    public static void setNewInterface(){
        Interface.form = new Interface();
    }
    public  void clear(){
        this.fenetre.setEnabled(true);
        this.fenetre.setVisible(false);
        Interface.setNewInterface();
        form.xo = this.xo;
        form.xoString = this.xoString;
        form.againstRobot = this.againstRobot;
        form.turn = this.turn;
        form.r.setDefidculty( this.r.getDefidculty());
        
        this.fenetre.dispose();
        form.fenetre.setVisible(true);
        if(form.turn == 0 && form.againstRobot){
            Squares s = form.r.playSquare();
            if(s==null){
                return;
            }
            form.play(s);
            form.turn();
        }
    }
    private void turn(){
        if(this.turn == 0) this.turn = 1;
        else this.turn = 0;
    }
    public void sClick(MouseEvent e,MouseAdapter m){
        if(e.getButton() == 3){
            vladimir.getPop().show(e.getComponent(), e.getX(), e.getY());
            return;
        }
        Squares aux = (Squares)e.getSource();
        aux.removeMouseListener(m);
        if(!theMAP.contains(aux))
            return;
        boolean k = play(aux);
        turn();
        if(k){
            clear();
            return;
        }
        if(againstRobot && !theMAP.isEmpty()){
            Squares s = r.playSquare();
            if(s==null){
                return;
            }
            k = play(s);
            turn();
            if(k){
                clear();
            }
        }
    }
    public boolean play(Squares aux){
        xoDefault.setEnabled(false);
        xoAwsome.setEnabled(false);
        robot.setEnabled(false);
        opponent.setEnabled(false);
        this.theMAP.remove(aux);
        int i = algorithm.getWinner();
        this.squares.add(aux);
        if(i > 1){
            this.fenetre.setEnabled(true);
            return false;
        }
        aux.setSquare(algorithm, (turn==0)?'x':'o');
        aux.setIcon(xo[turn]);
        i = algorithm.getWinner();
        int n = -1;
        if(i==0){
            n = pupUp(
                    "NO one wins !!\nWould you like to repeat??",
                    "Draw!!",null);
        }else if(i==120){
            n = pupUp("'"+xoString[0]+"' wins!!\nWould you like to repeat??",
                    "Great!!",xo[0]);
            turn();
        }else if(i==111){
            n = pupUp("'"+xoString[1]+"' wins!!\nWould you like to repeat??",
                    "Great!!",xo[1]);
            turn();
        }
        return n==0;
    }
    private void initComponents() {

        vladimir = new Panel();
        squareS00 = new Squares();
        squareS01 = new Squares();
        squareS02 = new Squares();
        squareS10 = new Squares();
        squareS11 = new Squares();
        squareS12 = new Squares();
        squareS20 = new Squares();
        squareS21 = new Squares();
        squareS22 = new Squares();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu file = new JMenu();
        JMenuItem rematch = new JMenuItem();
        JMenuItem exit = new JMenuItem();
        JMenuItem edit = new JMenu();
        xoDefault = new JMenuItem();
        xoAwsome = new JMenuItem();
        JPopupMenu.Separator jSeparator1 = new JPopupMenu.Separator();
        robot = new javax.swing.JMenu();
        JMenuItem easy = new JMenuItem();
        JMenuItem medium = new JMenuItem();
        JMenuItem good = new JMenuItem();
        JMenuItem expert = new JMenuItem();
        opponent = new JMenuItem();
        JPopupMenu.Separator jSeparator2 = new JPopupMenu.Separator();
        JMenuItem about = new JMenuItem();

        this.fenetre.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout vladimirLayout = new javax.swing.GroupLayout(vladimir);
        vladimir.setLayout(vladimirLayout);
        vladimirLayout.setHorizontalGroup(
            vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vladimirLayout.createSequentialGroup()
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(squareS20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(squareS10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareS00, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(squareS01, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(squareS11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareS21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(squareS12, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(squareS22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareS02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        vladimirLayout.setVerticalGroup(
            vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vladimirLayout.createSequentialGroup()
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(squareS01, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(squareS00, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareS02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(squareS10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(squareS11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(squareS12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vladimirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(squareS20, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(squareS22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareS21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        file.setText("File");

        rematch.setText("Rematch");
        rematch.addActionListener(e->rematchActionPerformed());
        file.add(rematch);

        exit.setText("Exit");
        exit.addActionListener(e->exitActionPerformed());
        file.add(exit);

        jMenuBar1.add(file);

        edit.setText("Edit");

        xoDefault.setText("Default XO");
        xoDefault.addActionListener(e->xoDefaultActionPerformed());
        edit.add(xoDefault);

        xoAwsome.setText("League Of XO");
        xoAwsome.addActionListener(e->xoAwsomeActionPerformed());
        edit.add(xoAwsome);
        edit.add(jSeparator1);

        robot.setText("Play against Robot");

        easy.setText("Easy");
        easy.addActionListener(e->easyActionPerformed());
        robot.add(easy);

        medium.setText("Medium");
        medium.addActionListener(e->mediumActionPerformed());
        robot.add(medium);

        good.setText("Good");
        good.addActionListener(e->goodActionPerformed());
        robot.add(good);

        expert.setText("Expert");
        expert.addActionListener(e->expertActionPerformed());
        robot.add(expert);

        edit.add(robot);

        opponent.setText("Play against opponent");
        opponent.addActionListener(e->opponentActionPerformed());
        edit.add(opponent);
        edit.add(jSeparator2);

        about.setText("About");
        about.addActionListener(e->aboutActionPerformed());
        edit.add(about);

        jMenuBar1.add(edit);

        this.fenetre.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.fenetre.getContentPane());
        this.fenetre.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vladimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vladimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.fenetre.pack();
    }

    private void rematchActionPerformed() {
        clear();
    }

    private void exitActionPerformed() {
        System.exit(0);
    }

    private void xoDefaultActionPerformed() {
        this.xo[0] = new ImageIcon("images/x.png");
        this.xo[1] = new ImageIcon("images/O.png");
        xoString[0] = "X";
        xoString[1] = "O";
    }

    private void xoAwsomeActionPerformed() {//GEN-FIRST:event_xoAwsomeActionPerformed
        this.xo[0] = new ImageIcon("images/teemo.png");
        this.xo[1] = new ImageIcon("images/ez.png");
        xoString[0] = "Teemo";
        xoString[1] = "Ezreal";
    }//GEN-LAST:event_xoAwsomeActionPerformed

    private void aboutActionPerformed() {
        new About().setVisible(true);
    }

    private void opponentActionPerformed() {//GEN-FIRST:event_opponentActionPerformed
        againstRobot = false;
    }//GEN-LAST:event_opponentActionPerformed

    private void easyActionPerformed() {//GEN-FIRST:event_easyActionPerformed
        againstRobot = true;
        r.setDefidculty(0);
        if(turn == 0){
            Squares s = r.playSquare();
            if(s==null){
                return;
            }
            play(s);
            turn();
        }
    }//GEN-LAST:event_easyActionPerformed

    private void mediumActionPerformed() {//GEN-FIRST:event_mediumActionPerformed
        againstRobot = true;
        r.setDefidculty(1);
        if(turn == 0){
            Squares s = r.playSquare();
            if(s==null){
                return;
            }
            play(s);
            turn();
        }
    }//GEN-LAST:event_mediumActionPerformed

    private void goodActionPerformed() {//GEN-FIRST:event_goodActionPerformed
        againstRobot = true;
        r.setDefidculty(2);
        if(turn == 0){
            Squares s = r.playSquare();
            if(s==null){
                return;
            }
            play(s);
            turn();
        }
    }//GEN-LAST:event_goodActionPerformed

    private void expertActionPerformed() {//GEN-FIRST:event_expertActionPerformed
        againstRobot = true;
        r.setDefidculty(3);
        if(turn == 0){
            Squares s = r.playSquare();
            if(s==null){
                return;
            }
            play(s);
            turn();
        }
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(()->{
                form = new Interface();
                form.fenetre.setVisible(true);
            }
        );
    }

    
    private Squares squareS00;
    private Squares squareS01;
    private Squares squareS02;
    private Squares squareS10;
    private Squares squareS11;
    private Squares squareS12;
    private Squares squareS20;
    private Squares squareS21;
    private Squares squareS22;
    private Panel vladimir;
    
    
    

    
    private javax.swing.JMenuItem opponent;
    private javax.swing.JMenu robot;
    private javax.swing.JMenuItem xoAwsome;
    private javax.swing.JMenuItem xoDefault;
    
    private static Interface form;
    private Robot r;
    private final XO algorithm;
    private final LinkedList<Squares> theMAP;
    private ImageIcon [] xo;
    private String [] xoString;
    private LinkedList<Squares> squares;
    private boolean againstRobot;
    private int turn;
}
