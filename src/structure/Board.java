/*
 * Board.java
 *
 * Created on May 30, 2008, 3:46 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package structure;

import java.awt.Rectangle;

/**
 *
 * @author paul
 */
public abstract class Board {
    
        protected Rectangle board;
    
    /**
     * Creates a new instance of Board
     */
    public Board() {
    }

    
    public void newBoard(int x, int y){
        board = new Rectangle(x,y);
    }



    public Rectangle getBoard(){
        return board;
    }
    
}
