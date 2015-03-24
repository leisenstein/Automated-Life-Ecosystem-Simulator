/*
 * TransceiverList.java
 *
 * Created on May 19, 2008, 11:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package adhoc.nodes;
import adhoc.structure.Ether;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import structure.*;

/**
 *
 * @author paul
 */
public class TransceiverList {
    
    private java.util.List tList; // data structure to hold transceivers 
    private Integer capacity; //how many transceivers per node
    private Integer overhead; //how much of the capacity is overhead
    
    protected Ether eth;
    protected Node nod;
    
    /** Creates a new instance of TransceiverList */
    public TransceiverList() {
    }
    
    public TransceiverList(Integer c, Ether e){
        capacity = new Integer(c.intValue());
        overhead = new Integer(1);
        tList = new java.util.ArrayList(capacity.intValue());
        eth = e;
        nod = null;
        tList.add(0,new adhoc.nodes.overheadstates.Hungry());
        fillCommunicationSlots(overhead.intValue());
    }
    
    public TransceiverList(Integer c, Ether e, Node n){
        capacity = new Integer(c.intValue());
        overhead = new Integer(1);
        eth = e;
        nod = n;
        tList.add(0,new adhoc.nodes.overheadstates.Hungry());
        fillCommunicationSlots(overhead.intValue());
    }
    
    
    private void fillCommunicationSlots(int start){
                for (int i = start;i<capacity.intValue();i++)
                {tList.add(i, new adhoc.nodes.communicationstates.Idle());
                }
    }
    
    /* 
     * getters
     */
    
    
    public Transceiver getTransceiver(int i){
        Transceiver t = (Transceiver) tList.get(i);
        return t;
    }
    
    /*
     * setters
     */
    
    public void setNode(Node n){
        nod = n;
    }
    
       /** state changing */
    public boolean becomeInvitee(int which) {
     Transceiver myTrans = new adhoc.nodes.communicationstates.Invitee(); 
     if (which < capacity.intValue()){
         tList.add(which, myTrans);
         return true;
     }
     else return false;
    }
    
    /**
     * finds and returns the index of the first Idle transceiver
     */
    
    public int firstIdle(){
        for (int i = 0; i<capacity.intValue();i++){
            Transceiver t = (Transceiver) tList.get(i);
            if (t.getStateName().matches("Idle")) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean routePacket(int i, Packet p){
        Transceiver myTrans = (Transceiver) tList.get(i);
        myTrans.recievePacket(p);
        return true;
    }
}
