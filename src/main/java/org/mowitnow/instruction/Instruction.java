package org.mowitnow.instruction;

import org.mowitnow.model.Mower;

/**
 * A command to run on a mower instance.
 */
public interface Instruction {

    /**
     * Executes the {@link Instruction} on the given mower
     *
     * @param mower a mower instance
     */
    void execute(Mower mower);

}
