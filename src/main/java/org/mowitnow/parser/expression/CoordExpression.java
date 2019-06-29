package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.model.Coordinate;

/**
 * Parse a String and creates a {@link Coordinate}. The parsed String must
 * contain two positive integers values separated by a space, such as "4 0"
 */
public class CoordExpression extends AbstractMowitnowExpression<Coordinate> {

    public CoordExpression(String inputString) {
        super(inputString);
    }

    @Override
    protected Coordinate parseExpression() throws MowitnowParseException {

        String[] integerStrings = inputString.split(MowitnowExpressionParser.SPACE_REGEX);
        if (integerStrings.length != 2) {
            throw new MowitnowParseException("A coordinate expression must be composed of two values");
        }
        Coordinate coordinate;
        try {
            coordinate = new Coordinate(Integer.parseInt(integerStrings[0]), Integer.parseInt(integerStrings[1]));
        } catch (NumberFormatException nbe) {
            throw new MowitnowParseException("A coordinate must be composed of two integers");
        }
        if (coordinate.getX() < 0 || coordinate.getY() < 0) {
            throw new MowitnowInvalidDataException("A coordinate cannot have a negative value");
        }
        return coordinate;
    }

}
