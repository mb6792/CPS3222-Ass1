package com.liftmania.gui;

import static org.junit.Assert.*;

import java.awt.Color;

import org.jmock.Expectations;
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
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		lift = new Lift(1);
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
		shaft.animateLift(3);
		
		for(int i = 0; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		
		assertEquals(false, shaft.lift.isMoving());
		assertEquals(-1, shaft.lift.getToFloor());
	}
	
	@Test
	public void testAnimateLiftD() {
		shaft.setLiftFloor(3);
		
		shaft.animateLift(0);
		
		for(int i = 0; i < 10; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		for(int i = 10; i < 40; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		assertEquals(false, shaft.lift.isMoving());
	}

	@Test
	public void testAnimateUp() {
		shaft.animateUp(2);
		
		for(int i = 0; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testAnimateDown() {
		shaft.animateDown(1);
	
		for(int i = 0; i < 10; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		for(int i = 10; i < 40; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testHighlightGrid() {
		shaft.highlightGrid(20, 40);
		for(int i = 0; i < 20; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 20; i < 40; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testAnimationPause() {
		boolean thrown = false;
		
		try{
			shaft.animationPause(50);
		}catch(Exception ie){
			thrown = true;
		}
		
		assertFalse(thrown);
	}

	@Test
	public void testOpenDoors() {
		shaft.openDoors();
		assertEquals(Color.RED, shaft.liftColor);
		
		for(int i = 0; i < 10; i++){
			assertEquals(Color.RED, shaft.grid[i].getBackground());
		}
		for(int i = 10; i < 40; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testCloseDoors() {
		shaft.closeDoors();
		assertEquals(Color.GREEN, shaft.liftColor);
		
		for(int i = 0; i < 10; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		for(int i = 10; i < 40; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
	}

	@Test
	public void testAddAnimationCommand() {
		AnimationCommand ac = new AnimationCommand(Command.call, 3);
		shaft.addAnimationCommand(ac);
		assertTrue(shaft.animationCommands.contains(ac));
	}
	
	@Test
	public void testBeforeSetTesting(){
		assertEquals(false, shaft.testing);
	}
	
	@Test
	public void testSetTesting(){
		shaft.setTesting();
		assertEquals(true, shaft.testing);
	}

	@Test
	public void testRunMove() {
		AnimationCommand ac = new AnimationCommand(Command.move, 3);
		shaft.addAnimationCommand(ac);
		
		shaft.setTesting();
		shaft.run();
		
		for(int i = 0; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		assertEquals(false, shaft.lift.isMoving());
	}

	@Test
	public void testRunClose() {
		AnimationCommand ac = new AnimationCommand(Command.close, 2);
		shaft.addAnimationCommand(ac);
		
		shaft.setTesting();
		shaft.run();
		
		for(int i = 0; i < 20; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 20; i < 30; i++){
			assertEquals(Color.GREEN, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		assertEquals(false, shaft.lift.isMoving());
	}
	
	@Test
	public void testRunOpen() {
		AnimationCommand ac = new AnimationCommand(Command.open, 0);
		shaft.addAnimationCommand(ac);
		
		shaft.setTesting();
		shaft.setLiftFloor(3);
		shaft.run();
		
		for(int i = 0; i < 30; i++){
			assertEquals(Color.LIGHT_GRAY, shaft.grid[i].getBackground());
		}
		for(int i = 30; i < 40; i++){
			assertEquals(Color.RED, shaft.grid[i].getBackground());
		}
		assertEquals(false, shaft.lift.isMoving());
	}
	
	@Test
	public void testGetLift(){
		assertEquals(lift, shaft.getLift());
	}
}
