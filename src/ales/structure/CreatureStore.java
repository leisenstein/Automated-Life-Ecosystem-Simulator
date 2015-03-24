/*
 * Created on Oct 22, 2005
 *
 */
package ales.structure;
import ales.*;
import ales.creature.Creature;
import java.awt.*;
import java.util.*;
import structure.*;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class CreatureStore extends Graph {
	
    int count = 0;

	public GameManager manager;
        // private ALES_Board board;
	
	public CreatureStore( GameManager gm ) {

            manager = gm;
            vertexList = Collections.synchronizedList(new LinkedList());
            this.setRandom();
            board = gm.getBoard();
	}
	
	public CreatureStore( GameManager gm, LinkedList l ) {
            manager = gm;
            vertexList = Collections.synchronizedList( l );
            this.setRandom();
	}

        public CreatureStore getAll( ){
		Creature c;
		LinkedList l = new LinkedList();
		synchronized(vertexList) {
			Iterator i = vertexList.iterator();
			while (i.hasNext()){
				c = (Creature)i.next();
					l.add(c);
			}
		}
		return new CreatureStore( manager, l );
	}
	

        public void removeDead(){
            count ++;
            System.out.println("called" + count);
		synchronized(vertexList) {
			Iterator i = vertexList.iterator();
			Creature c = (Creature)i.next();
			if(c.getState() == 32) { //DEAD == 32
                            i.remove();
                            System.out.println("remove" + count + "\n");
                            manager.board.changePh( 2000 );
                        }
		}
        }

    public CreatureStore getNeighborhood(Point p, int r) {
        /*Creature c;
        
        LinkedList l = new LinkedList();
        synchronized (vertexList) {
            Iterator i = vertexList.iterator();
            while (i.hasNext()) {
                c = (Creature) i.next();
                if (c.distance(p) <= r) {   
                    l.add(c);
                }
            }
        }*/
        LinkedList l = new LinkedList(this.pullSubList(p,r));
        return new CreatureStore(manager, l);
    }
	

}
