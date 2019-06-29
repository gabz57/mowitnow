package org.mowitnow.instruction;

import lombok.Data;
import org.mowitnow.model.Mower;
import org.mowitnow.model.Rotation;

/**
 * Rotate a {@link Mower} with a specific Rotation.
 *
 * @author Arnaud
 */
@Data
public class RotationInstruction implements Instruction {

    /**
     * The rotation to use.
     */
    private final Rotation rotation;

    /**
     * Creates a rotation instruction with the given rotation.
     *
     * @param rotation the {@link Rotation} to use
     */
    public RotationInstruction(Rotation rotation) {
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Mower mower) {
        mower.rotate(rotation);
    }

}
