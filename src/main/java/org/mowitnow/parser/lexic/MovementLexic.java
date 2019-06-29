package org.mowitnow.parser.lexic;

import org.mowitnow.model.Movement;

public enum MovementLexic {
    /**
     * Move forward.
     */
    F(Movement.FORWARD);


    /**
     * The relative Object representation.
     */
    private Movement MovementValue;

    /**
     * Binds an {@link MovementLexic} value to its Object representation.
     *
     * @param MovementInstance the relative Object representation
     */
    MovementLexic(Movement MovementInstance) {
        this.MovementValue = MovementInstance;
    }

    /**
     * Converts a {@link MovementLexic} value to its java representation.
     *
     * @return a {@link Movement} instance
     */
    public Movement toMovement() {
        return MovementValue;
    }

}
