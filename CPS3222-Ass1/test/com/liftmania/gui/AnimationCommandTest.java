package com.liftmania.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnimationCommandTest {

	AnimationCommand ac;
	
	@Before
	public void setUp() throws Exception {
		ac = new AnimationCommand(AnimationCommand.Command.move, 2);
	}

	@Test
	public void testAnimationCommand() {
		assertEquals(ac.command, AnimationCommand.Command.move);
		assertEquals(ac.toFloor, 2);
	}
	
	@Test
	public void testSetCommand(){
		ac.setCommand(AnimationCommand.Command.open);
		assertEquals(AnimationCommand.Command.open, ac.command);
	}
	
	@Test
	public void testGetCommand(){
		assertEquals(AnimationCommand.Command.move, ac.getCommand());
	}
}
