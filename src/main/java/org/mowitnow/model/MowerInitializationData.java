package org.mowitnow.model;

import lombok.Data;
import org.mowitnow.instruction.Instruction;

import java.util.List;

/**
 * Contains the data to initialize a mower.
 * <ul>
 * <li>a {@link Position}</li>
 * <li>a list of {@link Instruction}</li>
 * </ul>
 *
 * @author Arnaud
 */
@Data
public class MowerInitializationData {

    /**
     * The initial {@link Position} of a mower.
     */
    private final Position initialPosition;

    /**
     * A list of instructions to be processed by a mower.
     */
    private final List<Instruction> instructions;

    /**
     * Creates a container object with required data for creating a mower.
     *
     * @param initialPosition the initial {@link Position} of a mower
     * @param instructions    a list of instructions to be processed by this mower
     */
    public MowerInitializationData(Position initialPosition, List<Instruction> instructions) {
        if (initialPosition == null) {
            throw new IllegalArgumentException("An initial position is required");
        }
        if (instructions == null) {
            throw new IllegalArgumentException("A list of instructions is required");
        }
        this.initialPosition = initialPosition;
        this.instructions = instructions;
    }

}
