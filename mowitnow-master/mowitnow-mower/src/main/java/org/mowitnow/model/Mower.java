package org.mowitnow.model;

import java.util.List;

import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.instruction.Instruction;

/**
 * Represents a mower for a specific lawn, with a required initial position
 * which must be inside the limits of the lawn.
 * <p>
 * When the position changes, the change is effective inside the
 * {@link Position} instance, which is not replaced by a new instance.
 * 
 * @author Arnaud
 *
 */
public class Mower {

	/** The lawn to mow. */
	private final Lawn lawn;

	/** The current position of the mower on the lawn. */
	private final Position position;

	/**
	 * Creates a mower instance for a given lawn and its initial position on
	 * this lawn.
	 * 
	 * @param lawn
	 *            the lawn to mow
	 * @param initialPosition
	 *            the initial position of the mower on the lawn, must be inside
	 *            the limits of the lawn
	 */
	public Mower(Lawn lawn, Position initialPosition) {
		if (lawn == null) {
			throw new MowitnowInvalidDataException("A lawn is required to initialise a mower");
		}
		if (initialPosition == null) {
			throw new MowitnowInvalidDataException("A position is required to initialise a mower");
		}
		if (!lawn.contains(initialPosition.getCoordinate())) {
			throw new MowitnowInvalidDataException("Can't initialise a mower outside of the lawn");
		}
		this.lawn = lawn;
		this.position = initialPosition;
		this.lawn.register(this);
	}

	/**
	 * Processes all the given {@link Instruction}s in order.
	 * 
	 * @param instructions
	 *            instructions to process
	 */
	public void processInstructions(List<Instruction> instructions) {
		instructions.forEach(i -> i.execute(this));
	}

	/**
	 * Process a movement action, which updates the {@link Position} of the
	 * mower.
	 * <p>
	 * The action will be skipped if the next position of the mower is out of
	 * the limits of the lawn.
	 * 
	 * @param movement
	 *            the movement to process
	 */
	public void move(Movement movement) {
		final Coordinate nextPosition = Coordinate.computeNextCoordinate(position);
		if (canMoveTo(nextPosition)) {
			position.setCoordinate(nextPosition);
		} // else skip move
	}

	/**
	 * Tests whether nothing prevents this Mower to go to the next position on
	 * the lawn.
	 * <p>
	 * This method should remain private since the next position to test must be
	 * directly next to the current position.
	 * <p>
	 * This method ensures that the next position is on the lawn and that the
	 * next position is free before the move (no other mower is stopped on that
	 * position).
	 * <p>
	 * If a mower would be next to this one and moving in the same direction,
	 * the move would be possible, but this method doesn't allow this move. As
	 * the mowers don't move concurrently, we don't check whether multiple mower
	 * are about to go to the same position, this implies to know next
	 * instructions of the other mowers on the lawn.
	 * 
	 * @param nextCoordinate
	 *            the next coordinate of the mower
	 * @return true if the mower can move to the given position
	 */
	private boolean canMoveTo(final Coordinate nextCoordinate) {
		return lawn.contains(nextCoordinate) && lawn.isCoordinateFree(nextCoordinate);
	}

	/**
	 * Process a rotation action, which updates the {@link Position} of the
	 * mower.
	 * 
	 * @param rotation
	 *            the rotation to process
	 */
	public void rotate(Rotation rotation) {
		Orientation nextOrientation = Orientation.computeNextOrientation(position.getOrientation(), rotation);
		position.setOrientation(nextOrientation);
	}

	/**
	 * The current position of the mower.
	 * 
	 * @return the current {@link Position} of the mower
	 */
	public Position getPosition() {
		return position;
	}
}
