package com.liftmania;

import com.liftmania.gui.LiftsVisualiser;
import com.liftmania.gui.Shaft;

public class LiftFactory{
	
	static int numFloors = 4;
	static int numLifts = 2;
	static boolean randomizePositons = true;
	
	static LiftsVisualiser visualiser;
	static LiftController lc;
	
	static Shaft[] shafts;
	
	public static void main (String args[]){
		visualiser = new LiftsVisualiser(numFloors, numLifts);
		lc = new LiftController(visualiser, numFloors, numLifts, randomizePositons);
		setShafts();
		visualiser.setController(lc);
		lc.randomizePosition();
	}
	
	public static void setShafts(){
		shafts = visualiser.getShafts();
		for (int i = 0; i < numLifts; i++) {
			shafts[i] = new Shaft(visualiser, numFloors, lc.getLifts()[i]);
			// Put each shaft in a separate thread for independent animation
			new Thread(shafts[i]).start();
		}
		visualiser.setShafts(shafts);
	}
	
}
