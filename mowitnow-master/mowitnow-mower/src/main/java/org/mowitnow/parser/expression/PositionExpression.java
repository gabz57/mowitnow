package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.model.Coordinate;
import org.mowitnow.model.Orientation;
import org.mowitnow.model.Position;

/**
 * Parses a String and creates a {@link Position}. A position input is composed
 * of 3 concatenated inputs separated by a space, the two first for the
 * coordinate, the third for the orientation.
 * 
 * @see CoordExpression
 * @see OrientationExpression
 * 
 * @author Arnaud
 *
 */
public class PositionExpression extends AbstractMowitnowExpression<Position> {

	public PositionExpression(String inputString) {
		super(inputString);
	}

	@Override
	protected Position parseExpression() throws MowitnowParseException {
		String[] positionStrings = inputString.split(MowitnowExpressionParser.SPACE_REGEX);
		if (positionStrings.length != 3) {
			throw new MowitnowParseException(
					"A position expression must be composed of three values separated by a space, was " + inputString);
		}
		String coordinateString = String.join(MowitnowExpressionParser.SPACE_STRING, positionStrings[0],
				positionStrings[1]);
		Coordinate coordinate = parseCoord(coordinateString);
		Orientation orientation = parseOrientation(positionStrings[2]);
		Position position = new Position(coordinate, orientation);
		return position;
	}

	private Coordinate parseCoord(String inputString) throws MowitnowParseException {
		return new CoordExpression(inputString).parse();
	}

	private Orientation parseOrientation(String inputString) throws MowitnowParseException {
		return new OrientationExpression(inputString).parse();
	}

}
