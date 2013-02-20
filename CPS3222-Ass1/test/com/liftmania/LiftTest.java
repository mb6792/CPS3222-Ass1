package com.liftmania;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LiftTest {
	
	Lift lift;
	
	@Before
	public void setUp(){
		lift = new Lift(0);
	}

	@Test
	public void testSetToFloor() {
		lift.setToFloor(3);
		assertEquals(3, lift.toFloor);
	}
	
	@Test
	public void testGetToFloor() {
		lift.setToFloor(3);
		assertEquals(3, lift.getToFloor());
	}
	
	@Test
	public void testResetToFloor() {
		lift.resetToFloor();
		assertEquals(-1, lift.toFloor);
	}
	
	@Test
	public void testGetId() {
		int id = lift.getId();
		assertEquals(id, 0);
	}
	
	@Test
	public void testGetIdNeg() {
		lift = new Lift(-1);
		int id = lift.getId();
		assertEquals(-1, id);
	}
	
	@Test
	public void testIsMoving() {
		boolean ans = lift.isMoving();
		assertEquals(false, ans);
	}

	@Test
	public void testSetMoving() {
		lift.setMoving(true);
		boolean ans = lift.isMoving();
		assertEquals(true, ans);
	}

	@Test
	public void testLift() {
		lift = new Lift(1);
		assertEquals(1, lift.id);
	}

	@Test
	public void testSetFloor() {
		lift.setFloor(3);
		assertEquals(3, lift.floor);
	}
	
	@Test
	public void testSetFloorNeg() {
		lift.setFloor(-2);
		assertEquals(-2, lift.floor);
	}

	@Test
	public void testGetFloor() {
		lift.setFloor(-2);
		int floor = lift.getFloor();
		assertEquals(-2, floor);
	}

	@Test
	public void testIsOpenFalse() {
		lift.doorsOpen = false;
		boolean ans = lift.isOpen();
		assertEquals(false, ans);
	}
	
	@Test
	public void testIsOpenTrue() {
		lift.doorsOpen = true;
		boolean ans = lift.isOpen();
		assertEquals(true, ans);
	}

	@Test
	public void testCloseDoors() {
		lift.closeDoors();
		assertEquals(false, lift.doorsOpen);
	}

	@Test
	public void testOpenDoors() {
		lift.openDoors();
		assertEquals(true, lift.doorsOpen);
	}

	@Test
	public void testDistanceFromFloor() {
		lift.setFloor(0);
		assertEquals(3, lift.distanceFromFloor(3));
	}
	
	@Test
	public void testDistanceFromNegFloor() {
		lift.setFloor(-2);
		assertEquals(5, lift.distanceFromFloor(3));
	}

}
