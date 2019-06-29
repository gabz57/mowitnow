package org.mowitnow.instruction;

import lombok.Data;
import org.mowitnow.model.Movement;
import org.mowitnow.model.Mower;

/**
 * Move a {@link Mower} with a specific {@link Movement}.
 */
@Data
public class MovementInstruction implements Instruction {

    /**
     * The movement to use.
     */
    private final Movement movement;

    /**
     * Creates a movement instruction with the given movement.
     *
     * @param movement the {@link Movement} to use
     */
    public MovementInstruction(Movement movement) {
        this.movement = movement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Mower mower) {
        mower.move(movement);
    }

}
