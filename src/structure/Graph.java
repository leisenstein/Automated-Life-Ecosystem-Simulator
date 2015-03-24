/*
 * Graph.java
 *
 * Created on April 26, 2008, 1:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package structure;
import ales.creature.Creature;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author paul
 */
public abstract class Graph {
    
    private java.util.Random rand = new java.util.Random();
    protected java.util.List vertexList;
    protected Board board;
    
    /** Creates a new instance of Graph */
    public Graph() {
    }
    
    public Graph(List l){
        vertexList = l;
    }
    
    public void setRandom(){
        rand.setSeed(System.currentTimeMillis());
    }
    
    public long getSeed() {
            return rand.nextLong();
    }
    
    public boolean hasNext() {
        synchronized(vertexList) {
            Iterator i = vertexList.iterator();
            return i.hasNext();
        }
    }
    
    public boolean addVertex( Vertex v ){
        boolean result;
        synchronized(vertexList) {
            result = vertexList.add( v ); 
        }
        return result;
    }
    
    public Vertex getNextVertex() {
        synchronized (vertexList) {
            Iterator i = vertexList.iterator(); 
            Vertex v = (Vertex)i.next();
            i.remove();
            return v;
        }
    }
    
    public LinkedList pullSubList(Point p, int r){
        Vertex v;
        LinkedList l = new LinkedList();
        synchronized (vertexList) {
            Iterator i = vertexList.iterator();
            while (i.hasNext()) {
                v = (Vertex) i.next();
                if (v.distance(p) <= r) {
                    l.add(v);
                }
            }
        }
        return l;
    }



    public boolean emptyStore(){
        return (vertexList.size()==1);
    }

    
    public Board getBoard(){
        return board;
    }

}
