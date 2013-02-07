package com.liftmania;

import com.liftmania.gui.LiftsVisualiser;

public class LiftFactory {
	
	static int numFloors = 4;
	static int numLifts = 2;
	static boolean randomizePositons = true;
	
	public static void main (String args[]){
		LiftsVisualiser visualiser = new LiftsVisualiser(numFloors, numLifts);
		LiftController lc = new LiftController(visualiser, numFloors, numLifts, randomizePositons);
		visualiser.setController(lc);
		lc.randomizePosition();
	}
}
