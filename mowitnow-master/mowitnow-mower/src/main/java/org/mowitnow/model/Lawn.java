package org.mowitnow.model;

/**
 * Represents a lawn to mow, which is a simple rectangle defined by two
 * {@link Coordinate}s, its lower left corner (the {@link Coordinate#ORIGIN})
 * and upper right corners).
 * <p>
 * <i>Immutable object</i>
 * 
 * @author Arnaud
 *
 */
public class Lawn {

	/** The opposite corner of the origin. */
	private final Coordinate upperRightCorner;

	/**
	 * Creates a lawn by defining the coordinate of its upper right corner. Note
	 * that using the origin to define the upper right corner is allowed, as it
	 * is a square of one unit, but not any mower will not be able to move
	 * 
	 * @param upperRightCorner
	 *            the {@link Coordinate} of the upper right corner
	 */
	public Lawn(Coordinate upperRightCorner) {
		super();
		if (upperRightCorner == null) {
			throw new IllegalArgumentException("A coordinate is required to define a lawn");
		}
		this.upperRightCorner = upperRightCorner;
	}

	/**
	 * Whether this lawn contains the given coordinate.
	 * 
	 * @param coord
	 *            the {@link Coordinate} to test
	 * @return true if the coordinate is inside the limits of the lawn, false
	 *         otherwise
	 */
	public boolean contains(Coordinate coord) {
		return coord.getX() >= Coordinate.ORIGIN.getX() && coord.getY() >= Coordinate.ORIGIN.getY()
				&& coord.getX() <= upperRightCorner.getX() && coord.getY() <= upperRightCorner.getY();
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
		result = prime * result + ((upperRightCorner == null) ? 0 : upperRightCorner.hashCode());
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
		Lawn other = (Lawn) obj;
		if (upperRightCorner == null) {
			if (other.upperRightCorner != null) {
				return false;
			}
		} else if (!upperRightCorner.equals(other.upperRightCorner)) {
			return false;
		}
		return true;
	}

}
