package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.instruction.MovementInstruction;
import org.mowitnow.instruction.RotationInstruction;
import org.mowitnow.parser.lexic.EnumUtil;
import org.mowitnow.parser.lexic.MovementLexic;
import org.mowitnow.parser.lexic.RotationLexic;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse a series of instructions.
 *
 * @author Arnaud
 */
public class InstructionsExpression extends AbstractMowitnowExpression<List<Instruction>> {

    public InstructionsExpression(String inputString) {
        super(inputString);
    }

    @Override
    protected List<Instruction> parseExpression() throws MowitnowParseException {
        List<Instruction> instructions = new ArrayList<>(inputString.length());
        for (char instructionChar : inputString.toCharArray()) {
            instructions.add(parseInstruction(String.valueOf(instructionChar)));
        }
        return instructions;
    }

    private Instruction parseInstruction(String instructionChar) throws MowitnowParseException {
        Instruction instruction;
        if (EnumUtil.isOneOf(MovementLexic.class, instructionChar)) {
            instruction = parseMovementInstruction(instructionChar);
        } else if (EnumUtil.isOneOf(RotationLexic.class, instructionChar)) {
            instruction = parseRotationInstruction(instructionChar);
        } else {
            throw new MowitnowParseException("Unknown instruction input, was " + instructionChar);
        }
        return instruction;
    }

    private RotationInstruction parseRotationInstruction(String inputString) throws MowitnowParseException {
        return new RotationInstructionExpression(inputString).parse();
    }

    private MovementInstruction parseMovementInstruction(String inputString) throws MowitnowParseException {
        return new MovementInstructionExpression(inputString).parse();
    }
}
