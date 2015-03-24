/*
 * Transceiver.java
 *
 * Created on April 9, 2008, 4:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package adhoc.nodes;
import adhoc.structure.Ether;
import structure.*;
/**
 *
 * @author paul
 */

public abstract class Transceiver {
    
    private String stateName;
    private Integer Color;
    private Packet pac = null;
    protected Ether e;
    protected Node n;
    /**
     * Creates a new instance of Transceiver
     */
    
    public Transceiver(){
    }
    
    public Transceiver(Ether theEther, Node myNode) {
        e = theEther;
        n = myNode;
    }
    
    public Transceiver(String myName){
        stateName = myName;
    }
    
    protected void setStateName(String inputString){
        stateName = inputString;
    }
    
    public String getStateName(){
        return stateName;                                                                  
    }
    
    public Packet getPacket(){
        Packet ket = new Packet(pac);
        pac = null;
        return ket;
    }
    
    public void recievePacket(Packet p){
        pac = new Packet(p);
    }
    
    
    
}
