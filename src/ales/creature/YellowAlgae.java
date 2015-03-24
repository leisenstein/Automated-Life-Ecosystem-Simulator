/*
 * .java
 *
 * Created on November 30, 2005, 12:14 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
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
public class YellowAlgae extends Creature{
	
    private final boolean DEBUG = false;
    private int deathCounter = 100;
    private int lifespan = 500000;
          
    public YellowAlgae( Point loc, CreatureStore theSource ){
        super(loc, theSource); 
        this.type = 1;

        lastMoveTime = 0;
        age = 0;
        health = 100000;
        energy = 100000;
    }
	
	public void behave( int gameTime ) {
		int elapsed = gameTime - lastMoveTime;
                int cloneThreshold = Math.abs(9000- (source.manager.board.getNutrient()/10));
		lastMoveTime = gameTime;
		drift(elapsed);

                // update creature age
                age += elapsed;
                
                // update creature health
                if( source.manager.board.getNutrient() < 10000 ) health -= elapsed;
                //if( source.manager.board.getOxygen() < 10000 ) health -= elapsed;
                if( age > lifespan ) health -= 50*elapsed;
                if( source.manager.board.getNutrient() < 1 || health < 1 ) {
                    if(DEBUG) System.out.println("Plant dying. n=" 
                            + source.manager.board.getNutrient() + "  o="
                            + source.manager.board.getOxygen() + "  h="
                            + health + "\n");
                    state = DYING;
                }
                if( state == DYING ) {
                    deathCounter -= elapsed;
                    if( deathCounter < 1 ){
                        state = DEAD;
                        source.manager.changeNutrient(7500);}
                }
                
                // update board state
                source.manager.board.changeNutrient(-elapsed);
                source.manager.board.changeOxygen(+elapsed);
                           
		if( state != DYING && state != DEAD && rand.nextInt(cloneThreshold+1) <= Math.abs(elapsed+1))
                        {//divide
			source.addVertex( this.getClone( 0 ) );
			System.out.println("A creature has been added.");
		}
	}
	
	public Creature getClone( int age ){
		Creature clone = new YellowAlgae( location, source );
		super.getClone( clone, age/2);
		return clone;
	}
        
        
        
        public String getSprite(){
            String image = "plantAdultImage";
            if (age < 1000)
                image = "plantYoungImage";
            if (state == DYING )
                image = "plantDyingImage";
            if (state == DEAD )
                image = "errorImage";
            return image;
        }
		
    
}
