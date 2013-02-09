package com.liftmania.gui;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.liftmania.Lift;

public class ShaftTest {
	
	Shaft shaft;
	Mockery context;
	LiftsVisualiser visualiser;

	Lift lift;
	
	@Before
	public void setUp() throws Exception {
		context = new Mockery(){{
			lift = new Lift(1);
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		visualiser = context.mock(LiftsVisualiser.class);
		shaft = new Shaft(visualiser, 4, lift);
	}

	@Test
	public void testShaft() {
		assertEquals(visualiser, shaft.visualiser);
		assertEquals(4, shaft.numFloors);
		assertEquals(lift, shaft.lift);
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLiftFloorInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLiftFloorIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAnimationStepOnFloor() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimateLift() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimateUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimateDown() {
		fail("Not yet implemented");
	}

	@Test
	public void testHighlightGrid() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimationPause() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpenDoors() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseDoors() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAnimationCommand() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

}
