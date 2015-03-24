/*
 * Packet.java
 *
 * Created on April 20, 2008, 12:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package adhoc.nodes;

/**
 *dd
 * @author paul
 */
public class Packet {
    
    private Character type;
    private String data;
    private Integer color;
    
    /** Creates a new instance of Packet */
    public Packet() {
        type = new Character('p');
        data = new String("");
        color = new Integer(0);
        }
    
    public Packet(char c, String s){
        type = new Character(c);
        data = new String(s);
        color = new Integer(0);
    }
    
    public Packet(char c, String s, int i){
        type = new Character(c);
        data = new String(s);
        color = new Integer(i);
    }
    
    public Packet(Packet p){
        type = new Character(p.getType());
        data = new String(p.getData());
        color = new Integer(p.getColor());
        p = null;
    }
       
    public char getType(){
        return this.type.charValue();
    }
    
    public String getData(){
        return this.data;
    }
    
    public int getColor(){
        return this.color.intValue();
    }
}
