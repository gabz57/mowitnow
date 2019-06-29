package org.mowitnow.model;

/**
 * Orientation following the english cardinal notation.
 *
 * @author Arnaud
 */
public enum Orientation {
    /**
     * North.
     */
    N,
    /**
     * East.
     */
    E,
    /**
     * West.
     */
    W,
    /**
     * South.
     */
    S;

    /**
     * Apply a 90 degrees rotation on the given orientation, and return the new
     * orientation.
     *
     * @param orientation the current orientation
     * @param rotation    the {@link Rotation} to apply
     * @return the new orientation
     */
    public static Orientation computeNextOrientation(Orientation orientation, Rotation rotation) {
        Orientation nextOrientation = null;
        switch (rotation) {
            case LEFT:
                switch (orientation) {
                    case N:
                        nextOrientation = Orientation.W;
                        break;
                    case E:
                        nextOrientation = Orientation.N;
                        break;
                    case W:
                        nextOrientation = Orientation.S;
                        break;
                    case S:
                        nextOrientation = Orientation.E;
                        break;
                    default:
                        // can't happen by design
                        break;
                }
                break;
            case RIGHT:
                switch (orientation) {
                    case N:
                        nextOrientation = Orientation.E;
                        break;
                    case E:
                        nextOrientation = Orientation.S;
                        break;
                    case W:
                        nextOrientation = Orientation.N;
                        break;
                    case S:
                        nextOrientation = Orientation.W;
                        break;
                    default:
                        // can't happen by design
                        break;
                }
                break;
            default:
                // can't happen by design
                break;
        }
        return nextOrientation;
    }
}
