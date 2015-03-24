/*
 * Created on Oct 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales.creature;
import ales.*;
import java.awt.Point;
import ales.structure.CreatureStore;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class Barrier extends Creature {
    
    public Barrier( Point loc, CreatureStore theSource ) {
        super(loc, theSource);
        type = 0;
        state = 1;
    }
    
    public void makeMove( int gameclock ){
        // barriers don't move
    }
    
    public String getSprite(){
        return "barrierImage";
    }
}
