package org.mowitnow.parser.expression;

import org.mowitnow.exception.MowitnowParseException;

/**
 * Parses an input to produce a Mowitnow Object representation.
 *
 * @param <T> the type of the data to produce
 * @author Arnaud
 */
public interface Expression<T> {

    /**
     * Produces an object representation from an input value.
     *
     * @return the produced data, the type follows the {@link Expression} type
     * in use
     * @throws MowitnowParseException if any invalid data is encountered during the parsing
     */
    T parse() throws MowitnowParseException;
}
