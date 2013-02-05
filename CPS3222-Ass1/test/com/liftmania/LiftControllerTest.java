package com.liftmania;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LiftControllerTest {
	LiftController lc;
	
	@Before
	public void setUp() throws Exception {
		lc = new LiftController(4, 6, true);
	}

	@Test
	public void testLiftController() {
		assertEquals(4, lc.numFloors);
		assertEquals(5, lc.numLifts);
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetLifts() {
		lc.getLifts()
	}

	@Test
	public void testMoveLiftIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveLiftLiftInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseLiftDoor() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpenLiftDoor() {
		fail("Not yet implemented");
	}

	@Test
	public void testCallLiftToFloor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClosestStationaryLifts() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
