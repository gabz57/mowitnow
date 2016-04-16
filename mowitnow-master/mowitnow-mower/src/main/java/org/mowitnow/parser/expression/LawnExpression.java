package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.model.Coordinate;
import org.mowitnow.model.Lawn;

/**
 * Parse a String and creates a {@link Lawn}. A lawn is defined by its top right
 * corner coordinate, thus it must follow the {@link CoordExpression}
 * convention.
 * 
 * @author Arnaud
 *
 */
public class LawnExpression extends AbstractMowitnowExpression<Lawn> {

	public LawnExpression(String inputString) {
		super(inputString);
	}

	@Override
	protected Lawn parseExpression() throws MowitnowParseException {
		return new Lawn(parseCoord(inputString));
	}

	private Coordinate parseCoord(String inputString) throws MowitnowParseException {
		return new CoordExpression(inputString).parse();
	}
}
