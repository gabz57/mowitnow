package org.mowitnow.model;

import lombok.Data;

/**
 * Represents a place on the lawn by its {@link Coordinate}, with an
 * {@link Orientation}.
 * <p>
 * Neither the coordinate nor the orientation can be null, otherwise an
 * exception is raised.
 *
 * @author Arnaud
 */
@Data
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
     * @param coordinate  the {@link Coordinate}
     * @param orientation the {@link Orientation}
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
     * @param coordinate  a coordinate
     * @param orientation an orientation
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
     * Defines the new coordinate of this position.
     *
     * @param nextCoordinate the new {@link Coordinate}
     */
    public void setCoordinate(Coordinate nextCoordinate) {
        checkInvariant(nextCoordinate, orientation);
        coordinate = nextCoordinate;
    }

    /**
     * Defines the new orientation of this position.
     *
     * @param nextOrientation the new {@link Orientation}
     */
    public void setOrientation(Orientation nextOrientation) {
        checkInvariant(coordinate, nextOrientation);
        orientation = nextOrientation;
    }

    @Override
    public String toString() {
        return "Position [coordinate=" + coordinate + ", orientation=" + orientation + "]";
    }

}
