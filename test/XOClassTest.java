/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import xo.XO;

/**
 *
 * @author Xacto
 */
public class XOClassTest {
    public static XO xo;
    char [][] map =    {{'x','o','x'},
                        {'o','x','o'},
                        {'o','-','x'}};
    String mapStr = "xox\noxo\no-x";
    
    //Tests's counter
    public static int counter = 1;
    
    public XOClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        System.out.println("Starting test");
        XOClassTest.xo = new XO();
        System.out.println("Seting test map");
        String map = "xox\noxo\no-x";
        xo.stringToMap(map);
        System.out.println("Object ready to be tested\n");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Test ended");
    }
    
    @Before
    public void setUp() {
        System.out.println("Test number "+counter+" is about to start");
    }
    
    @After
    public void tearDown() {
        System.out.println("Test number "+counter+" is ended\n");
        counter ++;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getMapTest(){
        char [][] test = xo.getMap();
        assertArrayEquals("getMap went wrong",test, map);
    }
    
    @Test
    public void getWinnerTest(){
        char test = 'x';
        assertEquals("getWinner went wrong",xo.getWinner(), test);
    }
    
    @Test
    public void stringToMapTest(){
        xo.stringToMap(mapStr);
        assertArrayEquals("stringToMap went wrong",xo.getMap(),map);
    }
    
    @Test
    public void getSetSquareTest(){
        XO xxo = new XO();
        xxo.stringToMap(mapStr);
        xxo.setSquare(2, 1, 'o');
        assertEquals("getSquare and setSquare went wrong",xxo.getSquare(2, 1), 'o');
    }
    
    @Test
    public void setMapTest(){
        XO xxo = new XO();
        char [][] m =   {{'-','-','-'},
                         {'x','-','x'},
                         {'-','o','-'}};
        xxo.setMap(m);
        assertArrayEquals("setMap went wrong",xxo.getMap(), m);
    }
    
}
