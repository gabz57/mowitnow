package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;

/**
 * A parent class for {@link Expression} implementation, ensures that the input
 * is not null and not empty.
 *
 * @param <T> the type of the data to produce
 * @author Arnaud
 */
public abstract class AbstractMowitnowExpression<T> implements Expression<T> {

    /**
     * The input to parse, never null or empty.
     */
    final String inputString;

    /**
     * Constructor for parsing the given input.
     *
     * @param inputString the input to parse
     */
    public AbstractMowitnowExpression(String inputString) {
        checkInputString(inputString);
        this.inputString = inputString.trim();
    }

    /**
     * {@inheritDoc}
     */
    public T parse() throws MowitnowParseException {
        return parseExpression();
    }

    private void checkInputString(String inputString) {
        if (inputString == null) {
            throw new MowitnowInvalidDataException("Cannot parse an null input");
        }
        if (inputString.trim().isEmpty()) {
            throw new MowitnowInvalidDataException("Cannot parse an empty String");
        }
    }

    /**
     * Parses an input into a instance of the Mowitnow model.
     *
     * @return the produced {@literal <T>} object instance
     * @throws MowitnowParseException when an invalid input is encountered
     */
    protected abstract T parseExpression() throws MowitnowParseException;

}
