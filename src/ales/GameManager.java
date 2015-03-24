/*
 * Created on Oct 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales;
import ales.creature.Creature;
import ales.structure.ALES_Board;
import java.awt.*;
import ales.structure.CreatureStore;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 * This class handles all turnkeeping, telling Creatures when to move 
 * and managing both the Control Panel and the Board.
 *
 * Please note: Many functions of GameManager have been delegated to Animator.java
 */
public class GameManager implements Runnable {
	
    private final boolean DEBUG = true;
    public ALES_Game ales;
    public ALES_Board board;
    private CreatureStore store;
    private Container controlPanel;
    private Container mainPanel;
    private Creature c;
    private long timeThis;
    private long timeLast;
    private long elapsed;
	
	
	/**
	 * How many miliseconds in realtime is one game tick - can be used 
	 * to adjust simulation speed
	 */
	private int speed = 100; 
	private int gameclock = 0;
	private boolean running = false;

        
        public GameManager( ALES_Game a ){
            ales = a;
            board = ales.getBoard();
            timeLast = 0L;
        }      

        
        public void run(){
            if( running ){
 /*               
                store = ales.cs.getAll();
                timeThis = System.currentTimeMillis();
                elapsed = timeThis - timeLast;
                gameclock += (elapsed / speed);
                timeLast = timeThis;
                while(store.hasNext()){
                    c = store.next();
                    c.makeMove(gameclock);
                    if(DEBUG)System.out.println(c.getLocation());			
                }
                if(DEBUG)System.out.println("Creatures moved at gameclock " + gameclock + " realtime " + timeThis +"/n");
 */               
            }
        }
        
	/**
	 * Change the nutrient level of the current board by the specified amount
	 * @param delta
	 * @return true if the change was allowed
	 */
	public boolean changeNutrient( int delta ){
		return board.changeNutrient( delta );
	}
	
	/**
	 * Change the oxygen level of the current board by the specified amount
	 * @param delta
	 * @return true if the change was allowed
	 */
	
        public boolean changeOxygen( int delta ){
		return board.changeOxygen( delta );
	}

	/**
	 * Change the alkalinity of the current board by the specified amount
	 * @param delta
	 * @return true if the change was allowed
	 */
	
        public boolean changePh( int delta ){
		return board.changePh( delta );
	}

	/**
	 * Returns the current game clock - not real time
	 * @return an int
	 */
	
        public int getGameclock() {
		return gameclock;
	}
	
	public void setRunning( boolean status ){
		running = status;
                if(status) timeLast = System.currentTimeMillis();
	}
	
        public ALES_Board getBoard() {
                return board;
        }
	/**
	 * Returns the current game status
	 * @return true if the game is running - i.e. not paused
	 */
        
	public boolean isRunning(){
		return running;
	}
	
}