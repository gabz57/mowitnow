package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.RotationInstruction;
import org.mowitnow.parser.lexic.EnumUtil;
import org.mowitnow.parser.lexic.RotationLexic;

/**
 * Parse a String and creates a {@link RotationInstruction}. A rotation input
 * must be one the values of the {@link RotationLexic}.
 *
 * @author Arnaud
 */
public class RotationInstructionExpression extends AbstractMowitnowExpression<RotationInstruction> {

    public RotationInstructionExpression(String inputString) {
        super(inputString);
    }

    @Override
    protected RotationInstruction parseExpression() throws MowitnowParseException {
        RotationLexic rotationLexic;
        try {
            rotationLexic = RotationLexic.valueOf(inputString);
        } catch (IllegalArgumentException iae) {
            throw new MowitnowParseException(
                "Unknown rotation input, expected one of " + EnumUtil.toString(RotationLexic.values()) + ", was " + inputString,
                iae
            );
        }
        return new RotationInstruction(rotationLexic.toRotation());
    }

}
