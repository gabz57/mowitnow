package org.mowitnow.parser.expression;

import java.util.List;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.model.Lawn;
import org.mowitnow.model.Position;
import org.mowitnow.parser.MowitnowParser;

/**
 * An implementation of the {@link MowitnowParser} using expressions to parse
 * String inputs.
 * 
 * @author Arnaud
 *
 */
public class MowitnowExpressionParser implements MowitnowParser<String> {
	public final static String SPACE_REGEX = "\\s";
	public final static String SPACE_STRING = " ";

	/**
	 * {@inheritDoc}
	 */
	public Lawn parseLawn(String lawnString) throws MowitnowParseException {
		return new LawnExpression(lawnString).parse();
	}

	/**
	 * {@inheritDoc}
	 */
	public Position parsePosition(String positionString) throws MowitnowParseException {
		return new PositionExpression(positionString).parse();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Instruction> parseInstructions(String instructionsString) throws MowitnowParseException {
		return new InstructionsExpression(instructionsString).parse();
	}

}
