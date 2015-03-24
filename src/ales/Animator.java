/*
 * Created on Oct 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales;

import ales.creature.Creature;
import java.awt.geom.*;
import java.awt.*;
import java.util.HashMap;
import ales.structure.CreatureStore;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 * Manages the main animation cycle.  Draws to the main panel which
 * is given by ALES_UI 
 */
public class Animator implements Runnable {
		
    private final boolean DEBUG = false;
    private String imagePath = "~/dev/ales/images/";
    private Point2D topLeft;
    private Point2D bottomRight;
    private ALES_Game ales;
    private long timeThis, timeLast, elapsed;
    private int gameclock = 0, nextUpdateUI = 0;
    private int oldgameclock;
    private int speed = 12;     // msec per gameclock tick.  higher speed = slower simulation.  
    private int nutrientRefresh = 2;
    private int oxygenRefresh = 1;
    private int drawOffsetX = 10;
    private int drawOffsetY = 12;
	
    /**
    * This is the thread which actually does the drawing
    */
    private Thread animatorThread;
    private int frame;
    private long targetMSec;
    private boolean running;
    private CreatureStore store;
    private Creature c;
    private javax.swing.JPanel drawPanel;
    public boolean grid = true;
    private HashMap imageMap;
    private Graphics g;
    
	
    public Animator( ALES_Game a, javax.swing.JPanel p ) {
        targetMSec = 100L;
        ales = a;
        drawPanel = p;
        g = drawPanel.getGraphics();
        timeLast = System.currentTimeMillis();
        
        // preload images
        imageMap = new HashMap();
        imageMap.put( "bgImage", new javax.swing.ImageIcon(Animator.class.getResource("background.gif")));
        imageMap.put( "bgGridImage", new javax.swing.ImageIcon(Animator.class.getResource("backgroundgrid.gif")));        
        imageMap.put( "plantYoungImage", new javax.swing.ImageIcon(Animator.class.getResource("algaeyoung.gif")));
        imageMap.put( "plantAdultImage", new javax.swing.ImageIcon(Animator.class.getResource("algaeadult.gif")));        
        imageMap.put( "plantDyingImage", new javax.swing.ImageIcon(Animator.class.getResource("algaedying.gif")));
        imageMap.put( "carnivoreYoungImage", new javax.swing.ImageIcon( Animator.class.getResource("carnivoreyoung.gif")));        
        imageMap.put( "carnivoreAdultImage", new javax.swing.ImageIcon( Animator.class.getResource( "carnivoreadult.gif")));        
        imageMap.put( "carnivoreDyingImage", new javax.swing.ImageIcon( Animator.class.getResource( "carnivoredying.gif")));
        imageMap.put( "herbivoreYoungImage", new javax.swing.ImageIcon( Animator.class.getResource( "herbivoreyoung.gif")));        
        imageMap.put( "herbivoreAdultImage", new javax.swing.ImageIcon( Animator.class.getResource("herbivoreadult.gif")));        
        imageMap.put( "herbivoreDyingImage", new javax.swing.ImageIcon( Animator.class.getResource("herbivoredying.gif")));
        imageMap.put( "herbivore2YoungImage", new javax.swing.ImageIcon( Animator.class.getResource("herbivore2young.gif")));        
        imageMap.put( "herbivore2AdultImage", new javax.swing.ImageIcon( Animator.class.getResource("herbivore2adult.gif")));        
        imageMap.put( "herbivore2DyingImage", new javax.swing.ImageIcon( Animator.class.getResource("herbivore2dying.gif")));
        imageMap.put( "barrierImage", new javax.swing.ImageIcon( Animator.class.getResource( "barrier.gif")));
        imageMap.put( "errorImage", new javax.swing.ImageIcon( Animator.class.getResource("nocreature.gif")));
        

        animatorThread = new Thread(this);
        animatorThread.start();	
    }
        
    public void setRunning( boolean r ){
        running = r;
    }
	
    public void run() {
        // Remember the starting time
        long timeStart = System.currentTimeMillis();
        timeStart += targetMSec;
	
	while (Thread.currentThread() == animatorThread ) {
            // update all creature positions and states
            store = ales.cs.getAll();
            timeThis = System.currentTimeMillis();
            elapsed = timeThis - timeLast;
            oldgameclock = gameclock;
            gameclock += (elapsed / speed);
            if(DEBUG) System.out.println("Gameclock is now "+ gameclock +"\n");
            timeLast = timeThis;
            while(store.hasNext()){
                c = (ales.creature.Creature)store.getNextVertex();
                c.behave(gameclock+1);
                if(DEBUG)System.out.println(c.getLocation());			
            }
            if(DEBUG)System.out.println("Creatures moved at gameclock " + gameclock + " realtime " + timeThis +"\n");
            
            
	// Display the getNextVertex frame of animation.

            store = ales.cs.getAll();
            
            if(grid)
                g.drawImage(((javax.swing.ImageIcon)imageMap.get("bgGridImage")).getImage(),drawOffsetX,drawOffsetY,null);
            else
                g.drawImage(((javax.swing.ImageIcon)imageMap.get("bgImage")).getImage(),drawOffsetX,drawOffsetY,null);
            
            while(store.hasNext()){
                c = (ales.creature.Creature)store.getNextVertex();

                int x = c.getLocation().x /1000 + drawOffsetX;
                int y = c.getLocation().y /1000 + drawOffsetY;
                
                String imageName = c.getSprite();
                if (!imageMap.containsKey(imageName))
                    imageName = "errorImage";
                if (c.getState() != c.DEAD)
                    g.drawImage(((javax.swing.ImageIcon)imageMap.get(imageName)).getImage(), x, y, null);
                
                if(DEBUG) System.out.println("Creature now at " + c.getLocation());			
            }

            // do some board and CreatureStore maintenance
            if( gameclock > oldgameclock) {
                // Something to make Nutrient/Oxygen/Alk more dependent on Sliders
                //(nutrientRefresh * (gameclock - oldgameclock ))-(currentNut - Slider value)
                //ales.board.changeNutrient((nutrientRefresh * (gameclock - oldgameclock )));
                //ales.board.changeOxygen((oxygenRefresh * (gameclock - oldgameclock )));
            }
            ales.cs.removeDead();
            
            // intermittently update the slider values on the UI
            if( gameclock > nextUpdateUI ) {
                ales.myUI.setPB_Oxygen( ales.board.getOxygen());
                ales.myUI.setPB_Nutrients( ales.board.getNutrient());
                ales.myUI.setPB_Alkalinity( ales.board.getPh() );
                nextUpdateUI = gameclock + 200;
            }
            
            long timeStop = System.currentTimeMillis();
            if(DEBUG) System.out.println("Frame finished at " + timeStop + "\n");

            try {
              //  if ( timeStop < timeStart )
                    Thread.sleep(100);
              //  else
              //      System.out.println("Missed frame!  Should have finished by "+ timeStart);
            } catch (InterruptedException e) {
                break;
            }
            
        }
     }
}
