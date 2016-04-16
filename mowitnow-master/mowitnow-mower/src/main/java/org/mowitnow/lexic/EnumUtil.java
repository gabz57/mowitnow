package org.mowitnow.lexic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EnumUtil {

	private static Map<Class<? extends Enum<?>>, Pattern> isOneOfPatterns = new HashMap<>();
	private static final Collector<CharSequence, ?, String> IS_ONE_OF_PATTERN_BUILDER = Collectors.joining("|", "^[",
			"]{1}$");
	private static final Collector<CharSequence, ?, String> ENUMS_TO_STRING_COLLECTOR = Collectors.joining(", ", "[",
			"]");

	/**
	 * Produce a pattern to compile for matching input against a list of enum
	 * values
	 * 
	 * @param enumList
	 *            a list of enum values to use in the pattern to produce
	 * @return a pattern to compile for matching input against a list of enum
	 *         values
	 */
	private static String oneOfEnumPattern(List<? extends Enum<?>> enumList) {
		return enumList.stream().map(Enum::toString).collect(IS_ONE_OF_PATTERN_BUILDER);
	}

	/**
	 * Safely checks whether a String can be mapped to an enum value.
	 * 
	 * @param enumType
	 *            an enum type
	 * @param input
	 *            the String to check
	 * @return whether the given String is a value of the given enum
	 */
	public static boolean isOneOf(Class<? extends Enum<?>> enumType, String input) {
		if (input == null) {
			return false;
		}
		Pattern pattern = isOneOfPatterns.get(enumType);
		if (pattern == null) {
			pattern = Pattern.compile(oneOfEnumPattern(Arrays.asList(enumType.getEnumConstants())));
			isOneOfPatterns.put(enumType, pattern);
		}
		return pattern.matcher(input).matches();
	}

	/**
	 * Creates a string for simple printing the values of an Enum type.
	 * <p>
	 * Typically used with the <code>values()</code> method of an {@link Enum}
	 * <p>
	 * The output will look like [VALUE_A, VALUE_B, VALUE_C]
	 * 
	 * @param <E>
	 *            the enum type, implicit parameter
	 * @param enums
	 *            values of an Enum type
	 * 
	 * @return a String representation of the values of an enum
	 */
	public static <E extends Enum<E>> String toString(E[] enums) {
		return Arrays.asList(enums).stream().map(Enum::toString).collect(ENUMS_TO_STRING_COLLECTOR);
	}
}
