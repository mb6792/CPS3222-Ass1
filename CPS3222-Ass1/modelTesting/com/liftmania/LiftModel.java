package com.liftmania;

import static junit.framework.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.RandomTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.CoverageMetric;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class LiftModel implements FsmModel {
	private Lift sut = new Lift(1);
	
	int id;
	boolean moving = false;
	int floor = 0;
	int toFloor = -1;
	boolean doorsOpen = false;
	
	public enum LiftState {
		MOVING,
		CLOSE,
		OPEN;
	}
	
	@Override
	public LiftState getState() {
		if(moving){
			return LiftState.MOVING;
		}
		if(doorsOpen){
			return LiftState.OPEN;
		}else{
			return LiftState.CLOSE;
		}
		
	}

	@Override
	public void reset(boolean arg0) {
		sut = new Lift(1);
		
		moving = false;
//		floor = 0; 
//		toFloor = -1;
		doorsOpen = false;
	}
	
	public boolean setMovingTrueGuard(){return getState().equals(LiftState.CLOSE);};
	public @Action void setMovingTrue(){
		sut.setMoving(true);
		moving = true;
		
		assertEquals(moving, sut.isMoving());
		assertEquals(doorsOpen, sut.isOpen());
		Assert.assertFalse(doorsOpen);
	}

	public boolean setMovingFalseGuard(){return getState().equals(LiftState.MOVING);};
	public @Action void setMovingFalse(){
		sut.setMoving(false);
		moving = false;
		
		assertEquals(moving, sut.isMoving());
		assertEquals(doorsOpen, sut.isOpen());
		Assert.assertFalse(doorsOpen);
	}
	
	public boolean openDoorsGuard(){return getState().equals(LiftState.CLOSE);};
	public @Action void openDoors(){
		sut.openDoors();
		doorsOpen = true;
		
		assertEquals(doorsOpen, sut.isOpen());
	}
	
	public boolean closeDoorsGuard(){return getState().equals(LiftState.OPEN);};
	public @Action void closeDoors(){
		sut.closeDoors();
		doorsOpen = false;
		
		assertEquals(doorsOpen, sut.isOpen());
	}
	
	
	@Test
	public void main(){
//		Tester tester = new RandomTester(new LiftModel());
		Tester tester = new GreedyTester(new LiftModel());
		
		tester.buildGraph();
		
		tester.addListener(new StopOnFailureListener());
		
		CoverageMetric trCoverage = new TransitionCoverage();
		tester.addCoverageMetric(trCoverage);
		
		VerboseListener verboseListener = new VerboseListener();
		verboseListener.setModel(tester.getModel());
		tester.addListener(verboseListener);
		
		tester.generate(200);
		
		tester.printCoverage();
	}
}
