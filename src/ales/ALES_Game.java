/*
 * ALES_Game.java
 *
 * Created on November 29, 2005, 5:03 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package ales;
import ales.creature.Terramecium;
import ales.creature.YellowAlgae;
import ales.structure.ALES_Board;
import java.awt.*;
import ales.structure.CreatureStore;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class ALES_Game {
    public CreatureStore cs;
    public GameManager gm;
    public ALES_Board board;
    public AlesLarryUI myUI;
    public Animator anim;
    
    /** Creates a new instance of ALES_Game */
    public ALES_Game(String[] args) {
        board = new ALES_Board(80000,80000,50000,579999,389999);
        gm = new GameManager(this);
        cs = new CreatureStore(gm);
        
        loadGame();
        
        myUI = new AlesLarryUI(this);
        myUI.setVisible(true);
        
        anim = new Animator( this, myUI.getDrawPanel() );
        anim.setRunning( true );
        
    }
    
    private void loadGame() {
        cs.addVertex(new YellowAlgae(new Point( 100000, 100000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 180000, 150000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 310000, 180000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 50000, 280000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 250000, 45000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 210000, 80000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 200000, 10000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 80000, 150000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 230000, 180000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 290000, 1000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 120000, 350000), cs) );
        cs.addVertex(new YellowAlgae(new Point( 360000, 380000), cs) );
        cs.addVertex(new Terramecium(new Point(123456, 123456), cs));
        cs.addVertex(new Terramecium(new Point(290000, 200000), cs));
        cs.addVertex(new Terramecium(new Point(490000, 300000), cs));
        cs.addVertex(new Terramecium(new Point(1290000, 150000), cs));
        //cs.addVertex(new QuadDidium(new Point(400000, 200000), cs));
        //cs.addVertex(new QuadDidium(new Point(400000, 300000), cs));
        //cs.addVertex(new QuadDidium(new Point(500000, 300000), cs));
    }
    public ALES_Board getBoard() {
        return board;
    }
    
    public CreatureStore getStore() {
        return cs;
    }
    
    public GameManager getManager(){
        return gm;
    }
    
    
}
