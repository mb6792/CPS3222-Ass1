package com.liftmania;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class LiftControllerTest {
	LiftController lc;
	
	@Before
	public void setUp() throws Exception {
		lc = new LiftController(4, 2, true);
	}

	@Test
	public void testLiftController() {
		assertEquals(4, lc.numFloors);
		assertEquals(2, lc.numLifts);
		assertEquals(2, lc.lifts.length);
	}

	@Test
	public void testGetLifts() {
		Lift[] lifts = lc.getLifts();
		assertEquals(2, lifts.length);
	}

	@Test
	public void testMoveLiftIntInt() {
		try {
			Thread.sleep(3000);
			lc.moveLift(1, 5);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
		assertEquals(5, lc.lifts[1].getFloor());
	}

	@Test
	public void testMoveLiftLiftInt() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
