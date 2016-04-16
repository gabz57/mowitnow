package org.mowitnow.lexic;

import org.mowitnow.model.Rotation;

public enum RotationLexic {
	/** Left (Gauche). */
	G(Rotation.LEFT),
	/** Right (Droite). */
	D(Rotation.RIGHT);

	/**
	 * The relative Object representation.
	 */
	private Rotation rotationValue;

	/**
	 * Binds an {@link RotationLexic} value to its Object representation.
	 * 
	 * @param rotationInstance
	 *            the relative Object representation
	 */
	private RotationLexic(Rotation rotationInstance) {
		this.rotationValue = rotationInstance;
	}

	/**
	 * Converts a {@link RotationLexic} value to its java representation.
	 * 
	 * @return a {@link Rotation} instance
	 */
	public Rotation toRotation() {
		return rotationValue;
	}

}
