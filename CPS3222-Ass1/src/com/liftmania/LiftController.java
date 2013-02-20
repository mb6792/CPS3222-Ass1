package com.liftmania;

import java.util.ArrayList;

import com.liftmania.gui.LiftsVisualiser;

public class LiftController {

	int numFloors;
	int numLifts;

	boolean randomizePositions;
	
	Lift[] lifts;

	LiftsVisualiser visualiser;

	public LiftController(LiftsVisualiser visualiser, int numFloors, int numLifts, boolean randomizePositions) {
		this.numFloors = numFloors;
		this.numLifts = numLifts;
		
		//added
		this.visualiser = visualiser;

		lifts = new Lift[numLifts];
		for (int i = 0; i < numLifts; i++) {
			lifts[i] = new Lift(i);
		}
		
		//added
		this.randomizePositions = randomizePositions;
	}
	
	public void randomizePosition(){
		if (this.randomizePositions) {
			for (int i=0; i < numLifts; i++) {
				moveLift(lifts[i], (int)(Math.random() * (numFloors)));
			}
		}
	}

	public Lift[] getLifts() {
		return lifts;
	}
	
	/**
	 * Move a lift to a floor.
	 * 
	 * @param liftNumber - The lift number (1-based)
	 * @param floorNumber - The floor to move to (0-based).
	 */
	public void moveLift(int liftNumber, int floorNumber) {
		visualiser.animateLiftMovement(liftNumber, floorNumber);
	}
	
	public void moveLift(Lift lift, int floorNumber) {	
		moveLift(lift.getId(),  floorNumber);
	}
	

	/**
	 * Closes the door of a particular lift.
	 * 
	 * @param liftNumber - The lift number (0-based)
	 */
	public void closeLiftDoor(int liftNumber) {
		Lift lift = lifts[liftNumber];
		lift.closeDoors();

//		visualiser.addAnimationCommand(new AnimationCommand(AnimationCommand.Command.close, liftNumber, lift.getFloor(), -1));
	}

	/**
	 * Open the door of a particular lift.
	 * 
	 * @param liftNumber - The lift number (0-based)
	 */
	public void openLiftDoor(int liftNumber) {
		Lift lift = lifts[liftNumber];
		lift.openDoors();
		/*visualiser.addAnimationCommand(new AnimationCommand(
				AnimationCommand.Command.open, liftNumber, lift.getFloor(), -1));*/
	}

	/**
	 * Calls a lift to a particular floor.
	 * @param floor - The floor number (0-based).
	 */
	
	public void callLiftToFloor(int floor) {
		boolean foundMoving = false;
		
		for(Lift lift : lifts){
			if(lift.getToFloor() == floor){//lift.isMoving() && lift.getFloor()==floor
				foundMoving = true;
			}
		}
		if(foundMoving == false){
			//Find lifts closest to the required floor
			ArrayList<Lift> closestLifts = getClosestStationaryLifts(floor);
			
			if (closestLifts.size() == 0) {
				throw new RuntimeException("Could not find an available lift.");
			}
			
			//Pick random lift
			Lift lift = closestLifts.get((int)(Math.random() * (closestLifts.size())));
			
			moveLift(lift, floor);
		}
	}
	
	public ArrayList<Lift> getClosestStationaryLifts(int floor) {
		ArrayList<Lift> result = new ArrayList<Lift>();
		
		if(floor <= numFloors){
			int distance = -1;
			while (result.size() == 0) {
				distance++;
				for (Lift lift : lifts) {
					if (lift.distanceFromFloor(floor) == distance && !lift.isMoving()) {
						result.add(lift);
					}
				}
			}
		}
		
		return result;
	}

}
