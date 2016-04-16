package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.MovementInstruction;
import org.mowitnow.lexic.EnumUtil;
import org.mowitnow.lexic.MovementLexic;

/**
 * Parse a String and creates a {@link MovementInstruction}. A movement input
 * must be one the values of the {@link MovementLexic}.
 * 
 * @author Arnaud
 *
 */
public class MovementInstructionExpression extends AbstractMowitnowExpression<MovementInstruction> {

	public MovementInstructionExpression(String inputString) {
		super(inputString);
	}

	@Override
	protected MovementInstruction parseExpression() throws MowitnowParseException {
		MovementLexic movementLexic;
		try {
			movementLexic = MovementLexic.valueOf(inputString);
		} catch (IllegalArgumentException iae) {
			throw new MowitnowParseException("Unknown movement input, expected one of "
					+ EnumUtil.toString(MovementLexic.values()) + ", was " + inputString, iae);
		}
		return new MovementInstruction(movementLexic.toMovement());
	}

}
