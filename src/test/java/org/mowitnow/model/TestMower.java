package org.mowitnow.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestMower {

    private Lawn lawn = null;

    @BeforeEach
    void before() {
        lawn = new Lawn(new Coordinate(5, 5));
    }

    @Test
    void should_move_north_to_correct_position_with_same_orientation() {
        Orientation initialOrientation = Orientation.N;
        Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
        mower.move(Movement.FORWARD);
        assertThat(mower.getPosition().getCoordinate()).isEqualTo(new Coordinate(2, 3));
        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.N);
    }

    @Test
    void should_move_south_to_correct_position_with_same_orientation() {
        Orientation initialOrientation = Orientation.S;
        Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
        mower.move(Movement.FORWARD);
        assertThat(mower.getPosition().getCoordinate()).isEqualTo(new Coordinate(2, 1));
        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.S);
    }

    @Test
    void should_move_east_to_correct_position_with_same_orientation() {
        Orientation initialOrientation = Orientation.E;
        Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
        mower.move(Movement.FORWARD);
        assertThat(mower.getPosition().getCoordinate()).isEqualTo(new Coordinate(3, 2));
        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.E);
    }

    @Test
    void should_move_west_to_correct_position_with_same_orientation() {
        Orientation initialOrientation = Orientation.W;
        Mower mower = new Mower(lawn, new Position(new Coordinate(2, 2), initialOrientation));
        mower.move(Movement.FORWARD);
        assertThat(mower.getPosition().getCoordinate()).isEqualTo(new Coordinate(1, 2));
        assertThat(mower.getPosition().getOrientation()).isEqualTo(Orientation.W);
    }

    @Test
    void should_not_move_if_about_to_leave_lawn() {
        Mower mower = new Mower(lawn, new Position(new Coordinate(0, 0), Orientation.W));
        mower.move(Movement.FORWARD);
        assertThat(mower.getPosition().getCoordinate()).isEqualTo(new Coordinate(0, 0));

        Mower mower2 = new Mower(lawn, new Position(new Coordinate(0, 0), Orientation.S));
        mower2.move(Movement.FORWARD);
        assertThat(mower2.getPosition().getCoordinate()).isEqualTo(new Coordinate(0, 0));

        Mower mower3 = new Mower(lawn, new Position(new Coordinate(5, 5), Orientation.N));
        mower3.move(Movement.FORWARD);
        assertThat(mower3.getPosition().getCoordinate()).isEqualTo(new Coordinate(5, 5));

        Mower mower4 = new Mower(lawn, new Position(new Coordinate(5, 5), Orientation.E));
        mower4.move(Movement.FORWARD);
        assertThat(mower4.getPosition().getCoordinate()).isEqualTo(new Coordinate(5, 5));
    }

    @Test
    void should_not_move_to_an_occupied_position() {
        Position initialPositionA = new Position(new Coordinate(2, 2), Orientation.E);
        Position expectedPositionA = new Position(new Coordinate(2, 2), Orientation.E);
        Mower mowerA = new Mower(lawn, initialPositionA);

        // place another mower at the next position
        Position initialPositionB = new Position(Coordinate.computeNextCoordinate(initialPositionA), Orientation.S);
        @SuppressWarnings("unused")
        Mower mowerB = new Mower(lawn, initialPositionB);

        // try to move A
        mowerA.move(Movement.FORWARD);
        // position of A should not have changed (neither coordinate nor
        // orientation)
        assertThat(mowerA.getPosition()).isEqualTo(expectedPositionA);
        assertThat(mowerA.getPosition().getCoordinate()).isEqualTo(expectedPositionA.getCoordinate());
        assertThat(mowerA.getPosition().getOrientation()).isEqualTo(expectedPositionA.getOrientation());
    }

    @Test
    void should_move_to_an_unoccupied_position() {
        Position initialPositionA = new Position(new Coordinate(2, 2), Orientation.E);
        Position expectedPositionA = new Position(new Coordinate(2, 2), Orientation.E);
        Mower mowerA = new Mower(lawn, initialPositionA);

        // place another mower at another position, out of next to A
        Position initialPositionB = new Position(new Coordinate(4, 4), Orientation.S);
        @SuppressWarnings("unused")
        Mower mowerB = new Mower(lawn, initialPositionB);

        // try to move A
        mowerA.move(Movement.FORWARD);
        // position of A should have changed (the orientation remains unchanged)
        assertThat(mowerA.getPosition()).isNotEqualTo(expectedPositionA);
        assertThat(mowerA.getPosition().getCoordinate()).isNotEqualTo(expectedPositionA.getCoordinate());
        assertThat(mowerA.getPosition().getOrientation()).isEqualTo(expectedPositionA.getOrientation());
    }
}
