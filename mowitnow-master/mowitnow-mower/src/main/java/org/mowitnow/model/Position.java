package org.mowitnow.model;

/**
 * Represents a place on the lawn by its {@link Coordinate}, with an
 * {@link Orientation}.
 * <p>
 * Neither the coordinate nor the orientation can be null, otherwise an
 * exception is raised.
 * 
 * @author Arnaud
 *
 */
public class Position {

	/**
	 * The current coordinate of this position.
	 */
	private Coordinate coordinate;

	/**
	 * The current orientation of this position.
	 */
	private Orientation orientation;

	/**
	 * Creates a {@link Position} from a {@link Coordinate} and an
	 * {@link Orientation}.
	 * 
	 * @param coordinate
	 *            the {@link Coordinate}
	 * @param orientation
	 *            the {@link Orientation}
	 */
	public Position(Coordinate coordinate, Orientation orientation) {
		super();
		checkInvariant(coordinate, orientation);
		this.coordinate = coordinate;
		this.orientation = orientation;
	}

	/**
	 * Raises and exception when the coordinate or the orientation is nulled.
	 * 
	 * @param coordinate
	 *            a coordinate
	 * @param orientation
	 *            an orientation
	 */
	private void checkInvariant(Coordinate coordinate, Orientation orientation) {
		if (coordinate == null) {
			throw new IllegalArgumentException("Cannot use a null coordinate");
		}
		if (orientation == null) {
			throw new IllegalArgumentException("Cannot use a null orientation");
		}
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Defines the new coordinate of this position.
	 * 
	 * @param nextCoordinate
	 *            the new {@link Coordinate}
	 */
	public void setCoordinate(Coordinate nextCoordinate) {
		checkInvariant(nextCoordinate, orientation);
		coordinate = nextCoordinate;
	}

	/**
	 * Get the current orientation.
	 * 
	 * @return the current {@link Orientation}
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Defines the new orientation of this position.
	 * 
	 * @param nextOrientation
	 *            the new {@link Orientation}
	 */
	public void setOrientation(Orientation nextOrientation) {
		checkInvariant(coordinate, nextOrientation);
		orientation = nextOrientation;
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
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
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
		Position other = (Position) obj;
		if (coordinate == null) {
			if (other.coordinate != null) {
				return false;
			}
		} else if (!coordinate.equals(other.coordinate)) {
			return false;
		}
		if (orientation != other.orientation) {
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
		return "Position [coordinate=" + coordinate + ", orientation=" + orientation + "]";
	}

}
