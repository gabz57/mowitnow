package org.mowitnow.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMower {

	private Lawn lawn = null;

	@Before
	public void before() {
		lawn = new Lawn(new Coordinate(5, 5));
	}

	@Test
	public void should_move_north_to_correct_position_with_same_orientation() {
		Orientation initialOrientation = Orientation.N;
		Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
		mower.move(new Movement());
		assertEquals(new Coordinate(2, 3), mower.getPosition().getCoordinate());
		assertEquals(Orientation.N, mower.getPosition().getOrientation());
	}

	@Test
	public void should_move_south_to_correct_position_with_same_orientation() {
		Orientation initialOrientation = Orientation.S;
		Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
		mower.move(new Movement());
		assertEquals(new Coordinate(2, 1), mower.getPosition().getCoordinate());
		assertEquals(Orientation.S, mower.getPosition().getOrientation());
	}

	@Test
	public void should_move_east_to_correct_position_with_same_orientation() {
		Orientation initialOrientation = Orientation.E;
		Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
		mower.move(new Movement());
		assertEquals(new Coordinate(3, 2), mower.getPosition().getCoordinate());
		assertEquals(Orientation.E, mower.getPosition().getOrientation());
	}

	@Test
	public void should_move_west_to_correct_position_with_same_orientation() {
		Orientation initialOrientation = Orientation.W;
		Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
		mower.move(new Movement());
		assertEquals(new Coordinate(1, 2), mower.getPosition().getCoordinate());
		assertEquals(Orientation.W, mower.getPosition().getOrientation());
	}

	@Test
	public void should_not_move_if_about_to_leave_lawn() {
		Mower mower = new Mower(lawn, new Position(new Coordinate(0, 0), Orientation.W));
		mower.move(new Movement());
		assertEquals(new Coordinate(0, 0), mower.getPosition().getCoordinate());

		Mower mower2 = new Mower(lawn, new Position(new Coordinate(0, 0), Orientation.S));
		mower2.move(new Movement());
		assertEquals(new Coordinate(0, 0), mower2.getPosition().getCoordinate());

		Mower mower3 = new Mower(lawn, new Position(new Coordinate(5, 5), Orientation.N));
		mower3.move(new Movement());
		assertEquals(new Coordinate(5, 5), mower3.getPosition().getCoordinate());

		Mower mower4 = new Mower(lawn, new Position(new Coordinate(5, 5), Orientation.E));
		mower4.move(new Movement());
		assertEquals(new Coordinate(5, 5), mower4.getPosition().getCoordinate());
	}

	@Test
	public void should_not_move_to_an_occupied_position() {
		Position initialPositionA = new Position(new Coordinate(2, 2), Orientation.E);
		Position expectedPositionA = new Position(new Coordinate(2, 2), Orientation.E);
		Mower mowerA = new Mower(lawn, initialPositionA);

		// place another mower at the next position
		Position initialPositionB = new Position(Coordinate.computeNextCoordinate(initialPositionA), Orientation.S);
		@SuppressWarnings("unused")
		Mower mowerB = new Mower(lawn, initialPositionB);

		// try to move A
		mowerA.move(new Movement());
		// position of A should not have changed (neither coordinate nor
		// orientation)
		assertEquals(expectedPositionA, mowerA.getPosition());
		assertEquals(expectedPositionA.getCoordinate(), mowerA.getPosition().getCoordinate());
		assertEquals(expectedPositionA.getOrientation(), mowerA.getPosition().getOrientation());
	}

	@Test
	public void should_move_to_an_unoccupied_position() {
		Position initialPositionA = new Position(new Coordinate(2, 2), Orientation.E);
		Position expectedPositionA = new Position(new Coordinate(2, 2), Orientation.E);
		Mower mowerA = new Mower(lawn, initialPositionA);

		// place another mower at another position, out of next to A
		Position initialPositionB = new Position(new Coordinate(4, 4), Orientation.S);
		@SuppressWarnings("unused")
		Mower mowerB = new Mower(lawn, initialPositionB);

		// try to move A
		mowerA.move(new Movement());
		// position of A should have changed (the orientation remains unchanged)
		assertNotEquals(expectedPositionA, mowerA.getPosition());
		assertNotEquals(expectedPositionA.getCoordinate(), mowerA.getPosition().getCoordinate());
		assertEquals(expectedPositionA.getOrientation(), mowerA.getPosition().getOrientation());
	}
}
