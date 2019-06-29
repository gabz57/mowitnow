package org.mowitnow.model;

import lombok.Data;

/**
 * Simple Cartesian coordinate composed of a couple of integer values.
 * <p>
 * <i>Immutable object</i>
 *
 * @author Arnaud
 */
@Data
public class Coordinate {

    /**
     * The origin in the Cartesian system (0, 0).
     */
    public static final Coordinate ORIGIN = new Coordinate(0, 0);

    /**
     * The <i>x</i> axis value.
     */
    private final int x;
    /**
     * The <i>y</i> axis value.
     */
    private final int y;

    /**
     * Creates a new coordinate.
     *
     * @param x the <i>x</i> axis value
     * @param y the <i>y</i> axis value
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates an instance of the next {@link Coordinate} based on the current
     * {@link Orientation} of the {@link Position}.
     *
     * @param position the current position
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

    @Override
    public String toString() {
        return "Coordinate [x=" + x + ", y=" + y + "]";
    }

}
