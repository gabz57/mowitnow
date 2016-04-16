package org.mowitnow.model;

/**
 * Simple Cartesian coordinate composed of a couple of integer values.
 * <p>
 * <i>Immutable object</i>
 * 
 * @author Arnaud
 *
 */
public class Coordinate {

	/** The origin in the Cartesian system. */
	public static final Coordinate ORIGIN = new Coordinate(0, 0);

	/** The <i>x</i> axis value. */
	private final int x;
	/** The <i>y</i> axis value. */
	private final int y;

	/**
	 * Creates a new coordinate.
	 * 
	 * @param x
	 *            the <i>x</i> axis value
	 * @param y
	 *            the <i>y</i> axis value
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the <i>x</i> axis value
	 * 
	 * @return the <i>x</i> value
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the <i>y</i> axis value.
	 * 
	 * @return the <i>y</i> value
	 */
	public int getY() {
		return y;
	}

	/**
	 * Creates an instance of the next {@link Coordinate} based on the current
	 * {@link Orientation} of the {@link Position}.
	 * 
	 * @param position
	 *            the current position
	 * @return the next position
	 */
	public static Coordinate computeNextCoordinate(Position position) {
		Coordinate currentCoord = position.getCoordinate();
		Coordinate nextCoordinate = null;
		switch (position.getOrientation()) {
		case E:
			nextCoordinate = new Coordinate(currentCoord.x + 1, currentCoord.y);
			break;
		case N:
			nextCoordinate = new Coordinate(currentCoord.x, currentCoord.y + 1);
			break;
		case S:
			nextCoordinate = new Coordinate(currentCoord.x, currentCoord.y - 1);
			break;
		case W:
			nextCoordinate = new Coordinate(currentCoord.x - 1, currentCoord.y);
			break;
		default:
			// Can't happen by design
			break;
		}
		return nextCoordinate;
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
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}

}
