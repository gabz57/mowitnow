package org.mowitnow.parser;

import java.util.List;

import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.model.Lawn;
import org.mowitnow.model.Position;

/**
 * The Mowinow parser, for translating generic inputs into instances of the
 * Mowinow model.
 * 
 * @author Arnaud
 *
 * @param <T>
 *            the type of input
 */
public interface MowitnowParser<T> {

	/**
	 * Translate a lawn input into a {@link Lawn} instance.
	 * 
	 * @param lawnInput
	 *            the input
	 * @return a {@link Lawn} instance
	 * @throws MowitnowParseException
	 *             when an invalid input is encountered
	 */
	Lawn parseLawn(T lawnInput) throws MowitnowParseException;

	/**
	 * Translate a position input into a {@link Position} instance.
	 * 
	 * @param positionInput
	 *            the input
	 * @return a {@link Position} instance
	 * @throws MowitnowParseException
	 *             when an invalid input is encountered
	 */
	Position parsePosition(T positionInput) throws MowitnowParseException;

	/**
	 * Translate an instructions input into a list of {@link Instruction}
	 * instances.
	 * 
	 * @param instructionsInput
	 *            the input
	 * @return a list of {@link Instruction} instances
	 * @throws MowitnowParseException
	 *             when an invalid input is encountered
	 */
	List<Instruction> parseInstructions(T instructionsInput) throws MowitnowParseException;

}