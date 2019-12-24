/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kiodi
 */
public class Robot {
    private List<Squares> theMap;
    private Squares[][] field = new Squares[3][3];
    private XO map;
    private boolean randPlay = false;
    
    private int defidculty;
    
    public Squares playSquare(){
        if(defidculty == 0){
            return selectSquare();
        }
        if(defidculty == 1){
            return selectSquareMedium();
        }
        if(defidculty == 2){
            return selectSquareGood();
        }
        return expereSquares();
    }
    
    public Robot(List<Squares> theMap, XO map){
        this.theMap = theMap;
        this.map = map;
        for(int i = 0; i<theMap.size();i++){
            field[(int)(i/3)][i%3] = theMap.get(i);
        }
    }
    
    public Squares selectSquare(){
        randPlay = true;
        return selectSquare(this.theMap);
    }
    public Squares selectSquare(List<Squares> l){
        Random rn = new Random();
        int n = l.size();
        int i = rn.nextInt(n);
        if(l.isEmpty())
            return this.theMap.get(i);
        return l.get(i);
    }
    
    public Squares selectSquareMedium(){
        return selectSquareMedium('-');
    }
    
    public Squares selectingFirstPart(char[][] m , char c){
        for(int i = 0;i<3;i++){
            if(m[i][0] == m[i][1] && m[i][2] == '-' && m[i][1] != c){
                return field[i][2];
            }else if(m[i][0] == m[i][2] && m[i][1] == '-' && m[i][0] != c){
                return field[i][1];
            }else if(m[i][1] == m[i][2] && m[i][0] == '-' && m[i][1] != c){
                return field[i][0];
            }else if(m[0][i] == m[1][i] && m[2][i] == '-' && m[1][i] != c){
                return field[2][i];
            }else if(m[0][i] == m[2][i] && m[1][i] == '-' && m[0][i] != c){
                return field[1][i];
            }else if(m[1][i] == m[2][i] && m[0][i] == '-' && m[1][i] != c){
                return field[0][i];
            }
        }
        return null;
    }
    public Squares selectingTests(char[][] m , char c){
        if(m[0][0] == m[1][1] && m[2][2] == '-' && m[1][1] != c)
            return field[2][2];
        else if(m[0][0] == m[2][2] && m[1][1] == '-' && m[0][0] != c)
            return field[1][1];
        else if(m[2][2] == m[1][1] && m[0][0] == '-' && m[1][1] != c)
            return field[0][0];
        else if(m[0][2] == m[1][1] && m[2][0] == '-' && m[1][1] != c)
            return field[2][0];
        else if(m[0][2] == m[2][0] && m[1][1] == '-' && m[0][2] != c)
            return field[1][1];
        else if(m[2][0] == m[1][1] && m[0][2] == '-' && m[1][1] != c)
            return field[0][2];
        return null;
    }
    public Squares selectSquareMedium(char c){
        char[][] m = map.getMap();
        Squares aux = selectingFirstPart(m, c);
        if(aux != null)
            return aux;
        aux = selectingTests(m,c);
        if(aux != null)
            return aux;
        return selectSquare();
    }
    
    public Squares selectSquareGood(){
        return selectSquareGood('x');
    }
    
    public Squares selectingGoodFirstPart(char[][] m, char c){
        for(int i = 0;i<3;i++){
            if(m[i][0] == m[i][1] && m[i][2] == '-' && m[i][1] == c){
                return field[i][2];
            }else if(m[i][1] == m[i][2] && m[i][0] == '-' && m[i][1] == c){
                return field[i][0];
            }else if(m[i][0] == m[i][2] && m[i][1] == '-' && m[i][0] == c){
                return field[i][1];
            }else if(m[0][i] == m[2][i] && m[1][i] == '-' && m[0][i] == c){
                return field[1][i];
            }else if(m[0][i] == m[1][i] && m[2][i] == '-' && m[1][i] == c){
                return field[2][i];
            }else if(m[1][i] == m[2][i] && m[0][i] == '-' && m[1][i] == c){
                return field[0][i];
            }
        }
        return null;
    }
    public Squares selectSquareGood(char c){
        char[][] m = map.getMap();
        Squares aux = selectingGoodFirstPart(m, c);
        if(aux != null)
            return aux;
        aux = selectingTests(m,c);
        if(aux != null)
            return aux;
        return selectSquareMedium();
    }
    
    public void prepareSetFirstStep(List<Squares> set){
        for(int i = 0 ; i<theMap.size(); i++){
            if(theMap.get(i) == field[0][0] || theMap.get(i) == field[0][2] || theMap.get(i) == field[1][1] || theMap.get(i) == field[2][0] || theMap.get(i) == field[2][2])
                set.add(theMap.get(i));
        }
    }
    
    public void prepareSetSecondStep(List<Squares> set){
        for(int i = 0 ; i<theMap.size(); i++){
                if(theMap.get(i) == field[0][0] || theMap.get(i) == field[0][2] || theMap.get(i) == field[1][1] || theMap.get(i) == field[2][0] || theMap.get(i) == field[2][2])
                    continue;
                set.add(theMap.get(i));
            }
    }
    
    public Squares expereSquares(){
        String m = map.toString();
        String match;
        LinkedList<Squares> set = new LinkedList<>();
        LinkedList <String> matches = new LinkedList<>();
        
        prepareSetFirstStep(set);
        
        match = "o--\n---\n---\n";
        matches.add(match);
        match = "--o\n---\n---\n";
        matches.add(match);
        match = "---\n---\no--\n";
        matches.add(match);
        match = "---\n---\n--o\n";
        matches.add(match);
        if(!m.contains("o") && !m.contains("x")){
           return selectSquare(set);
        }
        match = "---\n-o-\n---\n";
        if(m.equals(match)){
            return selectSquare(set);
        }
        
        if(matches.contains(m)){
            return field[1][1];
        }
        
        matches.clear();
        match = "o--\n-o-\n--x\n";
        matches.add(match);
        match = "--o\n-o-\nx--\n";
        matches.add(match);
        match = "x--\n-o-\n--o\n";
        matches.add(match);
        match = "--x\n-o-\no--\n";
        matches.add(match);
        if(matches.contains(m)){
            selectSquare(set);
        }
        
        matches.clear();
        match = "o--\n-x-\n--o\n";
        matches.add(match);
        match = "--o\n-x-\no--\n";
        matches.add(match);
        if(matches.contains(m)){
            set.clear();
            prepareSetSecondStep(set);
            return selectSquare(set);
        }
        Squares s =  selectSquareGood();
        if(randPlay && !set.isEmpty()){
            randPlay = false;
            return selectSquare(set);
        }
        randPlay = false;
        return s;
    }

    public int getDefidculty() {
        return defidculty;
    }

    public void setDefidculty(int defidculty) {
        this.defidculty = defidculty;
    }
    
}
