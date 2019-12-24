/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

/**
 *
 * @author kiodi
 */
public class XO {
    private char[][] map = new char[3][3];
    private String mapStr = "";
    
    public int getWinner(){
        for(int i = 0;i<3;i++){
            if(map[i][0] == map[i][1] && map[i][0] == map[i][2] && map[i][0] !='-'){
                return map[i][0];
            }else if(map[0][i] == map[1][i] && map[0][i] == map[2][i] && map[0][i] !='-'){
                return map[0][i];
            }
        }
        if(map[0][0] == map[1][1] && map[0][0] == map[2][2] && map[0][0] !='-'){
            return map[0][0];
        }else if(map[0][2] == map[1][1] && map[0][2] == map[2][0] && map[0][2] !='-'){
            return map[0][2];
        }else if(mapStr.contains("-"))
            return 1;
    return 0;    
    }
    
    public void setSquare(int x, int y, char c){
        this.map[x][y] = c;
        this.mapStr = this.toString();
    }
    
    public char getSquare(int x, int y){
        return this.map[x][y];
    }
    
    public void stringToMap(String str){
        this.mapStr = str;
        String[] aux = str.split("\n");
        map[0] = aux[0].toCharArray();
        map[1] = aux[1].toCharArray();
        map[2] = aux[2].toCharArray();
    }
    
    public char[][] getMap(){
        return this.map; 
    }
    
    public void setMap(char [][] map){
        this.map = map; 
        this.mapStr = this.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(char[] c:this.map){
            for(char i:c){
                s.append(i);
            }
            s.append("\n");
        }
        return s.toString();
    }
}
