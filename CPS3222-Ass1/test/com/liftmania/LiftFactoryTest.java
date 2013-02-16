package com.liftmania;

import static org.junit.Assert.*;

import org.junit.Test;

public class LiftFactoryTest {
	
	LiftFactory factory = new LiftFactory();

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetShafts() {
		factory.main(null);
		factory.setShafts();
		
		for (int i = 0; i < 2; i++) {
			assertEquals(4, factory.shafts[i].numFloors);
			assertEquals(factory.lc.getLifts()[i], factory.shafts[i].getLift());
		}
	}

}
