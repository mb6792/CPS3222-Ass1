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
	public void testGetId() {
		int id = lift.getId();
		assertEquals(id, 0);
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
		int id = lift.getId();
		assertEquals(1, id);
	}

	@Test
	public void testSetFloor() {
		lift.setFloor(3);
		int floor = lift.getFloor();
		assertEquals(3, floor);
	}
	
	@Test
	public void testSetFloor2() {
		lift.setFloor(-2);
		int floor = lift.floor;
		assertEquals(-2, floor);
	}

	@Test
	public void testGetFloor() {
		lift.setFloor(-2);
		int floor = lift.getFloor();
		assertEquals(-2, floor);
	}

	@Test
	public void testIsOpen() {
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
		boolean ans = lift.doorsOpen;
		assertEquals(false, ans);
	}

	@Test
	public void testOpenDoors() {
		lift.openDoors();
		boolean ans = lift.doorsOpen;
		assertEquals(true, ans);
	}

	@Test
	public void testDistanceFromFloor() {
		lift.setFloor(0);
		int dist = lift.distanceFromFloor(3);
		assertEquals(3, dist);
	}

}
