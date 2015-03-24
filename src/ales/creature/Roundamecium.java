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
 * 
 * 
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 * 
 * Basic Roundamecium class
 */
public class Roundamecium extends CreaturZoans {
	
	private java.util.Random rand = new java.util.Random();
        private int deathCounter = 100;
	private int lifespan = 15000;
        private final boolean DEBUG = false;
        
	public Roundamecium( Point loc, CreatureStore theSource ){
		super(loc, theSource);
                rand.setSeed(theSource.getSeed());
                this.speed = new Point( rand.nextInt(121)-30, rand.nextInt(121)-30);//used to be 60
                this.type = 2;

                lastMoveTime = 0;
                age = 0;
                health = 100000;
                energy = 100000;
	}
	
	public void behave( int gameTime ) {
		int elapsed = gameTime - lastMoveTime;
		lastMoveTime = gameTime;
                
                double pullX = 0.0, pullY = 0.0;
                boolean eating = false;
                CreatureStore cs = source.manager.ales.cs.getAll();
                Creature c;
                while(cs.hasNext()) {
                    c = (Creature) cs.getNextVertex();
                    if( this.canEat(c) && c.state != DYING && c.state != DEAD ) {
                        if( c.distance( this.location ) < 500  && eating == false ) {
                            eating = true;
                            c.consumed();
                            if(DEBUG) System.out.println("Herbivore at " + this.location 
                                    + " eats creature at " + c.getLocation() + ", distance is " 
                                    + c.distance( this.location ));
                            this.energy += 5000;
                        }
                        pullX += c.getLocation().x - location.x;
                        pullY += c.getLocation().y - location.y;
                    }
                }

                pullX = pullX * 100 / Math.sqrt(pullX * pullX + pullY * pullY);
                pullY = pullY * 100 / Math.sqrt(pullX * pullX + pullY * pullY);

                
                location.x += new Double(pullX * elapsed).intValue();
                location.y += new Double(pullY * elapsed).intValue();
                		
		normalize();	

                // update creature age
                age += elapsed;
                energy -= elapsed;
                
                // update creature health
                if( source.manager.board.getOxygen() < 10000 ) health -= 2*elapsed;
                if( age > lifespan ) health -= 50*elapsed;
                if( source.manager.board.getOxygen() < 1 || health < 1 ) {
                    state = DYING;
                }
                if( state == DYING ) {
                    deathCounter -= elapsed;
                    if( deathCounter < 1 ) state = DEAD;
                }
                
                // update board state
                source.manager.board.changeOxygen(-elapsed);
                           
		// possibly divide
		if( state != DYING && state != DEAD && rand.nextInt(9000) <= elapsed ){
			//divide
			source.addVertex( this.getClone( 0 ) );
			if(DEBUG) System.out.println("A herbivore has been added.");
		}
	}
	
	public Creature getClone( int age ){
		Creature clone = new Roundamecium( location, source );
		super.getClone( clone, 0);
		return clone;
	}
        
        
        
        public String getSprite(){
            String image = "herbivoreAdultImage";
            if (age < 1000)
                image = "herbivoreYoungImage";
            if (state == DYING )
                image = "herbivoreDyingImage";
            if (state == DEAD )
                image = "errorImage";
            return image;
        }
		
    

}
