package org.mowitnow.lexic;

import org.mowitnow.model.Movement;

public enum MovementLexic {
	/** Move forward. */
	A;

	/**
	 * Converts a {@link MovementLexic} value to its java representation.
	 * 
	 * @return a {@link Movement} instance
	 */
	public Movement toMovement() {
		return new Movement();
	}

}
