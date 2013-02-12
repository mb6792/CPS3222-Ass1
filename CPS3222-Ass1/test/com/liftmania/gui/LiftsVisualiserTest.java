package com.liftmania.gui;

import static org.junit.Assert.*;

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
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetShafts() {
		assertEquals(2, lv.getShafts().length);
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnimateLiftMovement() {
		final Shaft shaft = context.mock(Shaft.class);
		context.checking(new Expectations(){{
			oneOf(shaft).addAnimationCommand(new AnimationCommand(AnimationCommand.Command.move, 4));
		}});
		
		lv.animateLiftMovement(0, 4);
		
		context.assertIsSatisfied();
	}

	@Test
	public void testActionPerformed() {
		fail("Not yet implemented");
	}

}
