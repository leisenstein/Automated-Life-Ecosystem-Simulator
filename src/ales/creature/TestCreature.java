
package ales.creature;

import ales.*;
import java.awt.Point;
import ales.structure.CreatureStore;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class TestCreature extends Creature {
	
	private java.util.Random rand = new java.util.Random();
	
	public TestCreature( Point loc, CreatureStore theSource ){
		super(loc, theSource);
                rand.setSeed(theSource.getSeed());
                this.speed = new Point( rand.nextInt(125)-62, rand.nextInt(125)-62);
                this.offset = new Point( 4, 8 );
                lastMoveTime = 0;
                age = 0;
                health = 10000;
	}
	
	public void makeMove( int gameTime ) {
		int elapsed = gameTime - lastMoveTime;
		lastMoveTime = gameTime;
		location.x = location.x + speed.x * elapsed;
		location.y = location.y + speed.y * elapsed;
		normalize();	
                age += elapsed;
           
		
		if( rand.nextInt(100000) <= elapsed ){
			//divide
			source.addVertex( this.getClone( 0 ) );
			System.out.println("A creature has been added.");
		}
	}
	
	public Creature getClone( int age ){
		Creature clone = new TestCreature( location, source );
		super.getClone( clone, age);
		return clone;
	}
        
        public String getSprite(){
            return "errorImage";
        }	
}
