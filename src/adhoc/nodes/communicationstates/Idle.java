/*
 * Idle.java
 *
 * Created on April 9, 2008, 10:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package adhoc.nodes.communicationstates;
import adhoc.nodes.*;
import adhoc.structure.Ether;
import structure.*;
import java.util.*;
/**
 *
 * @author paul
 */
public class Idle extends adhoc.nodes.Transceiver {
    
    Ether e;
    Node n;
    
    /** Creates a new instance of Idle */
    public Idle(Ether theEther, Node theNode) {
        e = theEther;
        n = theNode;
        setStateName("Idle");
    }
    
    public Idle(){
        setStateName("Idle");
    }
}
