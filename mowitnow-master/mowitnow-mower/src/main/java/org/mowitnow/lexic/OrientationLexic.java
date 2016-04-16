package org.mowitnow.lexic;

import org.mowitnow.model.Orientation;

public enum OrientationLexic {
	/** North. */
	N(Orientation.N),
	/** East. */
	E(Orientation.E),
	/** West. */
	W(Orientation.W),
	/** South. */
	S(Orientation.S);

	/**
	 * The relative Object representation.
	 */
	private Orientation orientationValue;

	/**
	 * Binds an {@link OrientationLexic} value to its Object representation.
	 * 
	 * @param orientationInstance
	 *            the relative Object representation
	 */
	private OrientationLexic(Orientation orientationInstance) {
		this.orientationValue = orientationInstance;
	}

	/**
	 * Converts a {@link OrientationLexic} value to its java representation.
	 * 
	 * @return a {@link Orientation} instance
	 */
	public Orientation toOrientation() {
		return orientationValue;
	}

}
