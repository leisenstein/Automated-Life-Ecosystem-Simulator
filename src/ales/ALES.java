/*
 * Created on Oct 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales;

import java.awt.*;
import java.util.*;


/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5.1
 *
 * Main entry point for the ALES application.  Calls ALES_Game, which contains
 * the UI, Board, Creatures, Game Manager and Animator as members
 * 
 */

public class ALES {
    
    
    public static void main(String[] args) {
        
        ALES_Game ales = new ALES_Game( args );
    }
    
}
