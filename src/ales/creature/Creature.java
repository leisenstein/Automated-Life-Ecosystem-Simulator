/*
 * Created on Oct 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales.creature;

import ales.structure.CreatureStore;
import java.awt.*;
import java.lang.*;
import java.lang.StringBuffer;
import java.util.Random;
import structure.*;
import ales.structure.ALES_Board;


/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class Creature extends structure.Vertex {
    private ALES_Board board;
    private int boardMinX;
    private int boardMaxX;
    private int boardMinY;
    private int boardMaxY;
    protected int age;
    protected int health;
    protected int energy;
    protected CreatureStore source;
    protected int state;
    protected int lastMoveTime = 0;
    protected Point offset;
    protected Point speed;
    protected Point seed;
    protected Point driftSpeed;
    protected Random rand = new Random();
    public final int CHILD = 1;
    public final int ADULT = 2;
    public final int DYING = 16;
    public final int DEAD = 32;
    public final int CARNIVORE = 1;
    public final int HERBIVORE = 2;
    public final int PLANT = 3;
    protected int type;
    
    public Creature() {
        System.out.println("Cannot use default constructor for Creature!");
    }
    
    public Creature( Point loc, CreatureStore theSource ){
        age = 0;
        health = 100;
        energy = 100;
        state = CHILD;
        source = theSource;
        board = (ALES_Board) theSource.getBoard();
        rand.setSeed(theSource.getSeed());
        this.driftSpeed = new Point( rand.nextInt(121)-60, rand.nextInt(121)-60);
//		lastMoveTime = source.manager.getGameClock();
        location=new Point(loc.x,loc.y);
        offset=new Point(0,0);
        speed=new Point(0,0);
        type = 99;
        boardMinX = (int)board.getBoard().getMinX();
        boardMaxX = (int)board.getBoard().getMaxX();
        boardMinY = (int)board.getBoard().getMinY();
        boardMaxY = (int)board.getBoard().getMaxY();
    }
    
    public void behave( int gameTime ) {
        // to be overridden in all classes derived from Creature
        
    }
    
    public void consumed() {
        state = DYING;
    }
    
    public boolean canEat( Creature c ) {
        return (this.type  == (int) c.type +1); // (this.type < c.type); //
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public int getState(){
        return state;
    }
    
    public int getType() {
        return type;
    }
    
    public int putType(int t) {
        return type = t;
    }
    

    //All classes are capable of drifting if no other behavior presents itself
    protected void drift(int elapsed){
        location.x = location.x + driftSpeed.x * elapsed;
        location.y = location.y + driftSpeed.y * elapsed;
        normalize();
    }
    
    public Creature getClone( int cloneAge ) {
        Creature clone = new Creature( location, (CreatureStore)source );
        clone.state = this.state;
        clone.age = cloneAge;
        clone.health = this.health;
        clone.energy = this.energy;
        clone.lastMoveTime = this.lastMoveTime;
        return clone;
    }
    
    public Creature getClone( Creature c, int cloneAge ) {
        c.state = this.state;
        c.age = cloneAge;
        c.health = this.health;
        c.energy = this.energy;
        c.lastMoveTime = this.lastMoveTime;
        c.type = this.type;
        return c;
    }
    
    public Point getLocation() {
        return location;
    }
    
    public Point getOffset() {
        return offset;
    }
    
    public void normalize(){
        // Keep the creature from going off the board - reflect at the edges.
        // This should be cleaned up to account for offset
        if (board.getBoard().contains(location)==false) {
        if( location.x < boardMinX ) {
            location.x = boardMinX + ( boardMinX - location.x);
            speed.x *= -1;
            rand.setSeed(source.getSeed());
            this.driftSpeed = new Point( rand.nextInt(121)-60, rand.nextInt(121)-60);
        }
        if(location.x > boardMaxX ){
            location.x = boardMaxX - ( location.x - boardMaxX);
            speed.x *= -1;
            rand.setSeed(source.getSeed());
            this.driftSpeed = new Point( rand.nextInt(121)-60, rand.nextInt(121)-60);
        }
        if( location.y < boardMinY ) {
            location.y = boardMinY + ( boardMinY - location.y);
            speed.y *= -1;
            rand.setSeed(source.getSeed());
            this.driftSpeed = new Point( rand.nextInt(121)-60, rand.nextInt(121)-60);
        }
        if(location.y > boardMaxY ){
            location.y = boardMaxY - ( location.y - boardMaxY);
            speed.y *= -1;
            rand.setSeed(source.getSeed());
            this.driftSpeed = new Point( rand.nextInt(121)-60, rand.nextInt(121)-60);
        }
        }
    }
    
    public void rebuildCreature(int ty,int a,int h,int e,int st,int t,Point l,Point o,Point sp){
        type         = ty;
        age          = a;
        health       = h;
        energy       = e;
        state        = st;
        lastMoveTime = t;
        location     = l;
        offset       = o;
        speed        = sp;
        //-	Creature
    }
    
    public String printCreature() {
        StringBuffer str1;
        StringBuffer str = new StringBuffer("<creature,");
        str.append(type);
        str.append(",");
        str.append(age);
        str.append(",");
        str.append(health);
        str.append(",");
        str.append(energy);
        str.append(",");
        str.append(state);
        str.append(",");
        str.append(lastMoveTime);
        str.append(",");
        str.append(location.x);
        str.append(",");
        str.append(location.y);
        str.append(",");
        str.append(offset.x);
        str.append(",");
        str.append(offset.y);
        str.append(",");
        str.append(speed.x);
        str.append(",");
        str.append(speed.y);
        str.append(",/>");
        return str.toString();
    }
    
    public String getSprite() {
        // To be overridden by all derived classes
        String theSprite = "error";
        return theSprite;
    }
    
}
