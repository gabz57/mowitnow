package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.lexic.EnumUtil;
import org.mowitnow.lexic.OrientationLexic;
import org.mowitnow.model.Orientation;

/**
 * Parse a String and creates a {@link Orientation}. A orientation input must be
 * one the values of the {@link OrientationLexic}.
 * 
 * @author Arnaud
 *
 */
public class OrientationExpression extends AbstractMowitnowExpression<Orientation> {

	public OrientationExpression(String inputString) {
		super(inputString);
	}

	@Override
	protected Orientation parseExpression() throws MowitnowParseException {
		OrientationLexic orientationLexic;
		try {
			orientationLexic = OrientationLexic.valueOf(inputString);
		} catch (IllegalArgumentException iae) {
			throw new MowitnowParseException("Unknown orientataion input, expected one of "
					+ EnumUtil.toString(OrientationLexic.values()) + ", was " + inputString);
		}
		return orientationLexic.toOrientation();
	}

}
