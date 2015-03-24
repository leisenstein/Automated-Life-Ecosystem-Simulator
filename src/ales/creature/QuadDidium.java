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
public class QuadDidium extends CreaturZoans {
    
    private java.util.Random rand = new java.util.Random();
    private int deathCounter = 100;
    private int lifespan = 150000;
    private int sensoryRange = 200000;
    private final boolean DEBUG = true;
    private int maxSpeed;
    
    public QuadDidium( Point loc, CreatureStore theSource ){
        super(loc, theSource);
        rand.setSeed(theSource.getSeed());
        this.maxSpeed = 105 + rand.nextInt(30);
        this.type = 3;
        
        lastMoveTime = 0;
        age = 0;
        health = 10000;
        energy = 10000;
    }
    
    public void behave( int gameTime ) {
        int elapsed = gameTime - lastMoveTime;
        lastMoveTime = gameTime;
        
        if (state != DYING && state != DEAD) {
            breathe(elapsed);
            eat = foodSource(source.manager.ales.cs.getNeighborhood(this.location, 1000));
            CreatureStore cs = source.manager.ales.cs.getNeighborhood(this.location, 600000);
            
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
            energy -= elapsed;
            
            // update creature health
            if( source.manager.board.getOxygen() < 10000 ) health -= 2*elapsed;
            if( age > lifespan ) health -= 5*elapsed;
            if( source.manager.board.getOxygen() < 100 || health < 1 ) {
                state = DYING;
            }
        } else if (state == DYING){
            deathCounter -= elapsed;
            if( deathCounter < 1 ) state = 32;
        }
        
        // possibly divide
        if( state != DYING && state != DEAD && rand.nextInt(150000) <= elapsed ){
            //divide
            source.addVertex( this.getClone( 0 ) );
            if(DEBUG) System.out.println("A Carnivore has been added.");
        }
    }
    
    public Creature getClone( int age ){
        Creature clone = new Terramecium( location, source );
        super.getClone( clone, 0);
        return clone;
    }
    
    
    
    public String getSprite(){
        String image = "carnivoreAdultImage";
        if (age < 1000)
            image = "carnivoreYoungImage";
        if (state == DYING )
            image = "carnivoreDyingImage";
        if (state == DEAD )
            image = "errorImage";
        return image;
    }
    
    
    
    
}
