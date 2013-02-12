package com.liftmania.gui;

import static org.junit.Assert.*;

import java.awt.Color;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.liftmania.Lift;
import com.liftmania.gui.AnimationCommand.Command;

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
		shaft.setLiftFloor(3);
		for(int i = 0; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testSetLiftFloorIntInt() {
		shaft.setLiftFloor(2, 3);
		for(int i = 0; i < 17; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 17; i < 27; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		for(int i = 27; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testIsAnimationStepOnFloor() {
		assertFalse(shaft.isAnimationStepOnFloor(1, 1));
	}
	
	@Test
	public void testIsAnimationStepOnFloor2() {
		assertTrue(shaft.isAnimationStepOnFloor(10, 1));
	}
	
	@Test
	public void testIsAnimationStepOnFloor3() {
		assertTrue(shaft.isAnimationStepOnFloor(20, 2));
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
		AnimationCommand ac = new AnimationCommand(Command.call, 3);
		shaft.addAnimationCommand(ac);
		assertTrue(shaft.animationCommands.contains(ac));
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

}
