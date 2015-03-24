/*
 * CreaturZoans.java
 *
 * Created on March 22, 2006, 2:33 AM
 *
 * CreaturZoans is an abstract class that implements Creatures
 * that have self directed movement (implement getTarget
 * and implement consume
 * all CreaturZoans consume other Creatures
 */

package ales.creature;

import ales.*;
import java.awt.Point;
import ales.structure.CreatureStore;
/**
 *
 * @author johndaigle
 */
abstract class CreaturZoans extends Creature {
    /**
     * Creates a new instance of CreaturZoans
     */
    
    protected Point target;
    protected final boolean DEBUG = true;
    protected boolean eat;
    protected int scootCounter = 0;
    protected Point scootSpeed = new Point(driftSpeed);
    protected Creature c;
    
    public CreaturZoans(Point loc, CreatureStore theSource) {
        super(loc, theSource);
    }
    
    /**
     * all creaturzoans should breathe
     */
    
    protected void breathe(int turns)
    {
        source.manager.changeOxygen(-Math.abs(turns *  10));
    }
    /**
     * takes three integers, moves the creature, normalizes the position
     * @param moveX, moveY (integer distances), 
     * @param elapsed (integer time since last move)
     * @return nothing
     */
    protected void changeLoc(int moveX, int moveY, int elapsed){
        location.x += moveX * elapsed;
        location.y += moveY * elapsed;
        normalize();
    }
    
    /**scoot should move the creature to some random spot
     */
    
    /*Scoot Doesn't Work For SHIT
    protected void scoot(int scootC, int turns){
        if(scootCounter==0){
            rand.setSeed(source.getSeed());
               this.scootSpeed = new Point( rand.nextInt(241)-100, rand.nextInt(241)-100);
               scootCounter = scootC;
        }
        changeLoc(scootSpeed.x, scootSpeed.y, turns);
        scootCounter -= 1;
    }*/
       
    /**
     * takes a Creature Store (close neighborhood) and consumes the closest food
     * Creature in the neighborhood. Should be called with a small neighborhood, 500-800
     *  @param CreatureStore cs
     *  @return true if the creature eats, false else
     */
    
    protected boolean foodSource(CreatureStore cs) {
        if (cs.emptyStore())
            return false;
        else{
            int closest = 1000000;
            Creature foodSource = this;
            boolean eatThat = false;
            while (cs.hasNext()){
                Creature c = (Creature)cs.getNextVertex();
                if (this.canEat(c) && c.state != DYING && c.state != DEAD) {
                    eatThat = true;
                    int dist = c.distance(this.location);
                    if (dist < closest) {
                        closest = dist;
                        foodSource = c;
                    }
                }
            }
            if (eatThat) {
                foodSource.consumed();
                energy += 50000;
                health += 20000;
                if(DEBUG) System.out.println( "Creature at " + location + " eats creature at "
                        + target.getLocation() + ".  Distance: " + closest );
                return true;
            } else
                return false;
        }
    }
    
    /**
     * takes a creature store and searches for edible creatures or predators.
     * returns a point to move to getNextVertex, towards food, away from predators, and 
     * drift if there are no food or predatory creatures in the store.
     * should be called with a larger neighborhood, 30000-100000
     * 
     * @param CreatureStore cs
     * @return Point target
     */
    
    protected Point getTarget(CreatureStore cs) {
        target=new Point(this.location);
        target.x=this.location.x + this.driftSpeed.x;
        target.y=this.location.y + this.driftSpeed.y;
        int closest = 999999999;
        while(cs.hasNext()) {
            Creature c = (Creature) cs.getNextVertex();
            if( this.canEat(c) && c.state != DYING && c.state != DEAD ) {
                int dist = c.distance(this.location);
                if( dist < closest ){
                    closest = dist;
                    target = c.location;
                }
            } else if (c.canEat(this)){
                int dist = c.distance(this.location);
                if (dist < closest && dist != 0 ) {
                    closest = dist;
                    target.x = this.location.x + (this.location.x-c.location.x);
                    target.y = this.location.y + (this.location.y-c.location.y);
                }
            }
        }
        return target;}
    
}
