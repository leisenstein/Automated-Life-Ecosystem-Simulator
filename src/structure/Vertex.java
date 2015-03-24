/*
 * Vertex.java
 *
 * Created on April 26, 2008, 1:16 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package structure;
import java.awt.*;  

/**
 *
 * @author paul
 */
public abstract class Vertex {
    
    public  Point location;
    protected Graph source;        
    private java.util.List vertexList;
            
    /** Creates a new instance of Vertex */
    
    public Vertex() {
    }
    
      public int distance( Point p ) {
        int dist = new Double( location.distance( p.x, p.y )).intValue();
        return dist;
    }
}
