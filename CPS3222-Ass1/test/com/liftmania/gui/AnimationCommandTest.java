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
		assertEquals(ac.command, AnimationCommand.Command.valueOf("move"));
		assertEquals(ac.toFloor, 2);
	}
	
	@Test
	public void testAnimationCommand2() {
		ac = new AnimationCommand(AnimationCommand.Command.close, 3);
		assertEquals(ac.command, AnimationCommand.Command.valueOf("close"));
		assertEquals(ac.toFloor, 3);
	}
	
	@Test
	public void testAnimationCommand3() {
		ac = new AnimationCommand(AnimationCommand.Command.call, 4);
		assertEquals(ac.command, AnimationCommand.Command.valueOf("call"));
		assertEquals(ac.toFloor, 4);
	}
	
	@Test
	public void testAnimationCommand4() {
		ac = new AnimationCommand(AnimationCommand.Command.open, 5);
		assertEquals(ac.command, AnimationCommand.Command.valueOf("open"));
		assertEquals(ac.toFloor, 5);
	}
}
