/*
 * Terramecium.java
 *
 * Created on November 30, 2005, 2:32 AM
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
public class Terramecium extends CreaturZoans implements Cloneable {
    
    private java.util.Random rand = new java.util.Random();
    private int deathCounter = 100;
    private int lifespan = 150000;
    private int maxSpeed;
    
    
    public Terramecium( Point loc, CreatureStore theSource ){
        super(loc, theSource);
        rand.setSeed(theSource.getSeed());
        this.maxSpeed = 80 + rand.nextInt(30);
        this.type = 2;
        
        lastMoveTime = 0;
        age = 0;
        state = CHILD;
        health = 100000;
        energy = 100000;
    }
    
    public void behave( int gameTime ) {
        int elapsed = gameTime - lastMoveTime;
        lastMoveTime = gameTime;
        int cloneThreshold = Math.abs(16000- (source.manager.board.getOxygen()/10));
        
        if (state != DYING && state != DEAD) {
            breathe(elapsed);
            eat = foodSource(source.manager.ales.cs.getNeighborhood(this.location, 500));
            CreatureStore cs = source.manager.ales.cs.getNeighborhood(this.location, 30000);
            
            // iterate through other creatures and pick a target, if there is something which can
            // be eaten.
            if (cs.emptyStore()){
                this.drift(elapsed);
            } else if (!eat) {
                target = getTarget(cs);
                int closest = (new Double(target.distance(this.location)).intValue());
                int moveX = ((target.x - location.x) * maxSpeed/closest );
                int moveY = ((target.y - location.y) * maxSpeed/closest );
                changeLoc(moveX, moveY, elapsed);
            } else{}
            // update creature age
            age += elapsed;
            energy -= elapsed*20;
            
            // update creature health
            if( source.manager.board.getOxygen() < 50000 ) health -= 50*elapsed;
            if( age > lifespan ) health -= 50*elapsed;
            if (energy < 50000 || health < 50000) health -= 50*elapsed;
            if (energy < 20000 || health < 20000) health -= 200*elapsed;
            if( source.manager.board.getOxygen() < 500 || energy < 1000 || health < 1000) {
                state = DYING;
            }
        } else if (state == DYING){
            deathCounter -= elapsed;
            if( deathCounter < 1 ){
                state = 32;
                source.manager.changeNutrient(+10000);
            }
        }
        
        // update board state
        
        // possibly divide
        if( state != DYING && state != DEAD && rand.nextInt(cloneThreshold) <= elapsed ){
            //divide
            source.addVertex( this.getClone( 0 ) );
            if(DEBUG) System.out.println("A Herbivore2 has been added.");
        }
    }
    
    public Creature getClone( int age ){
        Creature clone = new Terramecium( location, source );
        super.getClone( clone, 0);
        return clone;
    }
    
    
    
    public String getSprite(){
        String image = "herbivore2AdultImage";
        if (age < 1000)
            image = "herbivore2YoungImage";
        if (state == DYING )
            image = "herbivore2DyingImage";
        if (state == DEAD )
            image = "errorImage";
        return image;
    }
    
    
    
}
