package com.liftmania.gui;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.liftmania.Lift;
import com.liftmania.LiftController;

public class LiftsVisualiserTest {

	LiftsVisualiser lv;
	Mockery context;
	
	@Before
	public void setUp() throws Exception {
		context = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		lv = new LiftsVisualiser(4, 2);
	}

	@Test
	public void testLiftsVisualiser() {
		assertEquals(4, lv.numFloors);
		assertEquals(2, lv.numLifts);
		assertEquals(2, lv.shafts.length);
	}

	@Test
	public void testSetController() {
		LiftController controller = context.mock(LiftController.class);
		lv.setController(controller);
		assertEquals(controller, lv.controller);
	}

	@Test
	public void testSetShafts() {
		Shaft shaft = new Shaft(lv, 4, new Lift(1));
		Shaft shaft2 = new Shaft(lv, 4, new Lift(2));
		Shaft[] shafts = {shaft, shaft2};
		
		lv.setShafts(shafts);
		
		assertEquals(2, lv.shafts.length);
		assertEquals(shaft, lv.shafts[0]);
		assertEquals(shaft2, lv.shafts[1]);
	}
	
	@Test
	public void testGetShafts() {
		Shaft shaft = new Shaft(lv, 4, new Lift(1));
		Shaft shaft2 = new Shaft(lv, 4, new Lift(2));
		Shaft[] shafts = {shaft, shaft2};
		
		lv.setShafts(shafts);
		
		assertEquals(shaft, lv.getShafts()[0]);
		assertEquals(shaft2, lv.getShafts()[1]);
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimateLiftMovement() {
		Shaft shaft = new Shaft(lv, 4, new Lift(1));
		Shaft[] shafts = {shaft};
		lv.setShafts(shafts);
		
		lv.animateLiftMovement(0, 3);
	
		AnimationCommand ac = lv.shafts[0].animationCommands.peek();
		
		assertEquals(3, lv.shafts[0].lift.getToFloor());
		assertEquals(AnimationCommand.Command.move, ac.command);
		assertEquals(3, ac.toFloor);
	}

	@Test
	public void testActionPerformedMove() {
		final ActionEvent action = context.mock(ActionEvent.class);
		final LiftController controller = context.mock(LiftController.class);
		
		final String actions = "move,0,2";
		
		context.checking(new Expectations(){{
			oneOf(action).getActionCommand();
			will(returnValue(actions));
			oneOf(controller).moveLift(0, 2);
		}});
		
		lv.setController(controller);
		lv.actionPerformed(action);
		
		context.assertIsSatisfied();
	}

	@Test
	public void testActionPerformedOpen() {
		final ActionEvent action = context.mock(ActionEvent.class);
		final LiftController controller = context.mock(LiftController.class);
		
		final String actions = "open,0";
		
		context.checking(new Expectations(){{
			oneOf(action).getActionCommand();
			will(returnValue(actions));
			oneOf(controller).openLiftDoor(0);
		}});
		
		lv.setController(controller);
		lv.actionPerformed(action);
		
		context.assertIsSatisfied();
	}
	
	@Test
	public void testActionPerformedClose() {
		final ActionEvent action = context.mock(ActionEvent.class);
		final LiftController controller = context.mock(LiftController.class);
		
		final String actions = "close,0";
		
		context.checking(new Expectations(){{
			oneOf(action).getActionCommand();
			will(returnValue(actions));
			oneOf(controller).closeLiftDoor(0);
		}});
		
		lv.setController(controller);
		lv.actionPerformed(action);
		
		context.assertIsSatisfied();
	}
	
	@Test
	public void testActionPerformedCall() {
		final ActionEvent action = context.mock(ActionEvent.class);
		final LiftController controller = context.mock(LiftController.class);
		
		final String actions = "call,2";
		
		context.checking(new Expectations(){{
			oneOf(action).getActionCommand();
			will(returnValue(actions));
			oneOf(controller).callLiftToFloor(2);
		}});
		
		lv.setController(controller);
		lv.actionPerformed(action);
		
		context.assertIsSatisfied();
	}
}
