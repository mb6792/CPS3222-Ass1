package com.liftmania;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.liftmania.gui.LiftsVisualiser;

public class LiftControllerTest {
	LiftController lc;
	Mockery context;
	LiftsVisualiser visualiser;
	
	@Before
	public void setUp() throws Exception {
		context = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		visualiser = context.mock(LiftsVisualiser.class);
		lc = new LiftController(visualiser, 4, 2, true);
	}

	@Test
	public void testLiftController() {
		assertEquals(4, lc.numFloors);
		assertEquals(2, lc.numLifts);
		assertEquals(2, lc.lifts.length);
		assertEquals(visualiser, lc.visualiser);
	}

	@Test
	public void testGetLifts() {
		Lift[] lifts = lc.getLifts();
		assertEquals(2, lifts.length);
	}

	@Test
	public void testMoveLiftIntInt() {
		context.checking(new Expectations(){{
			oneOf(visualiser).animateLiftMovement(1, 5);
		}});
		
		lc.moveLift(1, 5);
		
		context.assertIsSatisfied();
		
//		try {
//			Thread.sleep(3000);
//			lc.moveLift(1, 5);
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			
//		}
//		assertEquals(5, lc.lifts[1].getFloor());
	}

	@Test
	public void testMoveLiftLiftInt() {
		try{
			Thread.sleep(3000);
			lc.moveLift(lc.lifts[1], 5);
			Thread.sleep(3000);
		}catch (Exception e){}
		assertEquals(5, lc.lifts[1].getFloor());
	}

	@Test
	public void testCloseLiftDoor() {
		lc.closeLiftDoor(1);
		assertFalse(lc.lifts[1].doorsOpen);
	}

	@Test
	public void testOpenLiftDoor() {
		lc.openLiftDoor(1);
		assertTrue(lc.lifts[1].doorsOpen);
	}

	@Test
	public void testCallLiftToFloor() {
		lc.lifts[0].floor = 1;
		lc.lifts[1].floor = 2;
		lc.callLiftToFloor(4);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {}
		assertEquals(4, lc.lifts[1].getFloor());
	}

	@Test
	public void testGetClosestStationaryLifts() {
		lc.lifts[0].setFloor(1);
		lc.lifts[0].setMoving(false);
		lc.lifts[1].setFloor(4);
		lc.lifts[1].setMoving(false);
		
		ArrayList<Lift> csl = lc.getClosestStationaryLifts(3);
		assertEquals(1, csl.get(0).id);
	}
}
