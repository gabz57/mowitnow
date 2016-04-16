package org.mowitnow.instruction;

import org.mowitnow.model.Mower;
import org.mowitnow.model.Rotation;

/**
 * Rotate a {@link Mower} with a specific Rotation.
 * 
 * @author Arnaud
 *
 */
public class RotationInstruction implements Instruction {

	/**
	 * The rotation to use.
	 */
	private final Rotation rotation;

	/**
	 * Creates a rotation instruction with the given rotation.
	 * 
	 * @param rotation
	 *            the {@link Rotation} to use
	 */
	public RotationInstruction(Rotation rotation) {
		this.rotation = rotation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(Mower mower) {
		mower.rotate(rotation);
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
		result = prime * result + ((rotation == null) ? 0 : rotation.hashCode());
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
		RotationInstruction other = (RotationInstruction) obj;
		if (rotation != other.rotation) {
			return false;
		}
		return true;
	}
}
