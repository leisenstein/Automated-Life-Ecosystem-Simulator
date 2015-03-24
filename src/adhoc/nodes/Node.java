/*
 * Node.java
 *
 * Created on April 9, 2008, 11:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


package adhoc.nodes;

import adhoc.structure.Ether;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import structure.*;
import java.lang.*;
import java.awt.*;


/**
 *
 * @author paul
 */
public class Node extends Vertex {
    
    public TransceiverList tList; // data structure to hold transceivers 
    private java.util.List cList; // list of used colors
    private java.util.List gList; // list of legal colors    
    private Integer capacity; //how many transceivers per node
    private Integer numberOfColors; //how many codes are available
    private java.util.Queue packetQueue;
    private Integer queueLength;
    
    protected Ether nl;
    
    /** Creates a new instance of Node */
    public Node(){
    }
   
    
    public Node(adhoc.structure.Ether e, int g, int i, int q) {
        nl = e;
        numberOfColors = new Integer(g);
        capacity = new Integer(i);
        queueLength = new Integer(q);
        
        finishNode();
    }
    
    public Node(adhoc.structure.Ether e, int g, int i) {
        nl = e;
        numberOfColors = new Integer(g);
        capacity = new Integer(i);
        queueLength = new Integer(10);
        
        finishNode();
    }
    
        public Node(adhoc.structure.Ether e, int g) {
        nl = e;
        numberOfColors = new Integer(g);
        capacity = new Integer(5);
        queueLength = new Integer(10);
        
        finishNode();
    }

    public Node(adhoc.structure.Ether e) {
        nl = e;
        numberOfColors = new Integer(32);
        capacity = new Integer(5);
        queueLength = new Integer(10);

        finishNode(); 
    }
    
    private void finishNode(){
        setLists();
        listInit(false, cList);
        listInit(true, gList);
    }
     
    private void setLists(){
        tList = new TransceiverList(capacity, nl);
        cList = Collections.synchronizedList(new Vector (numberOfColors.intValue()));
        gList = Collections.synchronizedList(new Vector (numberOfColors.intValue()));
        packetQueue = new java.util.concurrent.ArrayBlockingQueue(queueLength.intValue());
    } 
    
    
    private void listInit(boolean b, java.util.List l){
        for (int i = 0; i<numberOfColors.intValue();i++)
        {l.add(i,new Boolean(b));
        }   
    }
    
 
    
 
    
    /** Integer getCapacity, returns the capacity of the Transceiver array */
    public Integer getCapacity(){
        return this.capacity;
    }
    
    /** Returns the Transceiver at an index */
    public Transceiver getTransceiver(int index){
        Transceiver myTrans = (Transceiver) tList.getTransceiver(index);
        return myTrans;
    }
    
    /** returns true if the color at index i is being used by this node */
    public boolean getUsed(int i){
        Boolean b = (Boolean) cList.get(i);
        return b.booleanValue();
    }
    
    /** returns true if the color at index i can be used by this node */
    public boolean getLegal(int i){
        Boolean b = (Boolean)gList.get(i);
        return b.booleanValue();
    }
   

    public java.util.List getCList() {
        return cList;
    }
    
    public adhoc.nodes.TransceiverList getTList(){
        return tList;
    }
    
    /** returns true if the packet can be added to the Queue, false otherwise */
    public boolean addPacket(Packet p){
        return packetQueue.add(p);
    }
    
    /** returns the Packet at the head of the packetQueue or else null */
    
    public Packet getPacket(){
        return (Packet)packetQueue.poll();
    }
    
    /** 
     * push a packet onto the designated transceiver
     */
    public boolean pushPacket(int i, Packet p){
        tList.routePacket(i,p);
        return true;
    }
    
    public boolean recieveInvitation(){
        return tList.becomeInvitee(tList.firstIdle());
    }
    
   
}
