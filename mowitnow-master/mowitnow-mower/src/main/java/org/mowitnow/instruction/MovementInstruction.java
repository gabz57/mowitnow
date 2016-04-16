package org.mowitnow.instruction;

import org.mowitnow.model.Movement;
import org.mowitnow.model.Mower;

/**
 * Move a {@link Mower} with a specific {@link Movement}.
 * 
 * @author Arnaud
 *
 */
public class MovementInstruction implements Instruction {

	/**
	 * The movement to use.
	 */
	private final Movement movement;

	/**
	 * Creates a movement instruction with the given movement.
	 * 
	 * @param movement
	 *            the {@link Movement} to use
	 */
	public MovementInstruction(Movement movement) {
		this.movement = movement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Mower mower) {
		mower.move(movement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movement == null) ? 0 : movement.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MovementInstruction other = (MovementInstruction) obj;
		if (movement == null) {
			if (other.movement != null) {
				return false;
			}
		} else if (!movement.equals(other.movement)) {
			return false;
		}
		return true;
	}

}
