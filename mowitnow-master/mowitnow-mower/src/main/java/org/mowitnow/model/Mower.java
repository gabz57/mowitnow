package org.mowitnow.model;

import java.util.List;

import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.instruction.Instruction;

/**
 * Represents a mower for a specific lawn, with a required initial position
 * which must be inside the limits of the lawn.
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
	}

	/**
	 * Processes all the given {@link Instruction}s in order.
	 * 
	 * @param instructions
	 *            instructions to process
	 */
	public void processInstructions(List<Instruction> instructions) {
		for (Instruction instruction : instructions) {
			instruction.execute(this);
		}
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
		if (lawn.contains(nextPosition)) {
			position.setCoordinate(nextPosition);
		} // else skip move
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
