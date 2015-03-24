/*
 * Created on Oct 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales.structure;

import java.awt.*;
import structure.Board;

/**
 * @author Mike Balaun, Ed Bullwinkel, John Daigle, Larry Eisenstein
 * @version 0.5
 *
 */
public class ALES_Board extends Board {
        private int maxNutrient = 100000;
        private int maxOxygen = 100000;
        private int maxPh = 100000;
        
	
	/**
	 * The nutrient level in the drop of water being observed
	 */
	private int nutrientLevel;
	
	/**
	 * The oxygen level in the drop of water being observed
	 */
	private int oxygenLevel;
	
	/**
	 * The acidity or alkalinity of the drop of water
	 */
	private int ph;
        
        /*public ALES_Board(){
            nutrientLevel = 80000;
            oxygenLevel = 80000;
            ph = 50000;
            // board = new Rectangle(579999, 389999);
            this.newBoard(579999,389999);
        }*/
	
        public ALES_Board(int nL, int oL, int pL, int x, int y){
            nutrientLevel = nL;
            oxygenLevel = oL;
            ph = pL;
            this.newBoard(x,y);
        }
        
	/**
	 * Returns the nutrient level in the drop of water
	 */
	public int getNutrient(){
		return nutrientLevel;
	}
	
	/**
	 * Returns the oxygen level in the drop of water
	 */
	public int getOxygen(){
		return oxygenLevel;
	}
	/**
	 * Returns the Ph of the drop of water
	 */
	public int getPh(){
		return ph;
	}
	
	/**
	 * Modify the nutrient level in the drop of water
	 */
	public boolean changeNutrient( int delta ) {
                //for testing purposes, the nutrient level will stay constant.
		nutrientLevel += delta;
                if( nutrientLevel > maxNutrient)
                    nutrientLevel = maxNutrient;
		boolean valid = ( nutrientLevel >= 0 );
		if (!valid) {
			nutrientLevel = 0;
		}
		return valid;
	}
	
	/**
	 * Modify the oxygen level in the drop of water
	 */
	public boolean changeOxygen( int delta ) {
                //for testing purposese, the oxygen level will stay constant.
		oxygenLevel += delta;
                if ( oxygenLevel > maxOxygen )
                    oxygenLevel = maxOxygen;
		boolean valid = ( oxygenLevel >= 0 );
		if (!valid) {
			oxygenLevel = 0;
		}
		return valid;
	}

	/**
	 * Modify the Ph of the drop of water
	 */
	public boolean changePh( int delta ) {
		ph += delta;
		boolean valid = ( ph >= 0  || ph <= maxPh );
		if (!valid) {
			ph = 0;
		}
		return valid;
	}

        /**
	 * set the nutrient level in the drop of water
	 */
	public void setNutrient( int value ) {
		if( value > 0 && value < maxNutrient)
                    nutrientLevel = value;
	}
	
	/**
	 * set the oxygen level in the drop of water
	 */
	public void setOxygen( int value ) {
            if( value > 0 && value < maxOxygen)
                oxygenLevel = value;
	}

	/**
	 * set the Ph of the drop of water
         */
	public void setPh( int value ) {
            if( value > 0 && value < maxPh)
                ph = value;
	}

}
