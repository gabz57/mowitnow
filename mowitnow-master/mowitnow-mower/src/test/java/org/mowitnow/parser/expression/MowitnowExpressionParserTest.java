package org.mowitnow.parser.expression;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.instruction.MovementInstruction;
import org.mowitnow.instruction.RotationInstruction;
import org.mowitnow.model.Coordinate;
import org.mowitnow.model.Lawn;
import org.mowitnow.model.Movement;
import org.mowitnow.model.Orientation;
import org.mowitnow.model.Position;
import org.mowitnow.model.Rotation;
import org.mowitnow.parser.expression.MowitnowExpressionParser;

public class MowitnowExpressionParserTest {

	@Test
	public void parse_coord_should_parse_well_defined_coord() {

		Coordinate coord_5_5 = null;
		try {
			coord_5_5 = new CoordExpression("5 5").parse();
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(coord_5_5);
		assertEquals(new Coordinate(5, 5), coord_5_5);

		Coordinate coord_0_0 = null;
		try {
			coord_0_0 = new CoordExpression("0 0").parse();
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(coord_0_0);
		assertEquals(new Coordinate(0, 0), coord_0_0);

		Coordinate coord_1_2 = null;
		try {
			coord_1_2 = new CoordExpression("1 2").parse();
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(coord_1_2);
		assertEquals(new Coordinate(1, 2), coord_1_2);
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_coord_should_throw_mowitnow_invalid_data_exception_on_null_coord() throws Exception {
		new CoordExpression(null).parse();
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_coord_should_throw_mowitnow_invalid_data_exception_on_empty_string() throws Exception {
		new CoordExpression(" ").parse();
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_x() throws Exception {
		new CoordExpression("-1 2").parse();
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_y() throws Exception {
		new CoordExpression("2 -1").parse();
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_x_y() throws Exception {
		new CoordExpression("-2 -1").parse();
	}

	@Test
	public void parse_instructions_should_parse_well_defined_instructions() throws Exception {
		List<Instruction> instructions = new MowitnowExpressionParser().parseInstructions("AGD");
		assertNotNull(instructions);
		assertEquals(3, instructions.size());
		assertEquals(new MovementInstruction(new Movement()), instructions.get(0));
		assertEquals(new RotationInstruction(Rotation.LEFT), instructions.get(1));
		assertEquals(new RotationInstruction(Rotation.RIGHT), instructions.get(2));
	}

	@Test
	public void parse_instruction_should_parse_movement() throws Exception {
		Instruction instruction = new MovementInstructionExpression("A").parse();
		assertNotNull(instruction);
		assertEquals(new MovementInstruction(new Movement()), instruction);
	}

	@Test
	public void parse_instruction_should_parse_rotate_to_left() throws Exception {
		Instruction instruction = new RotationInstructionExpression("G").parse();
		assertNotNull(instruction);
		assertEquals(new RotationInstruction(Rotation.LEFT), instruction);
	}

	@Test
	public void parse_instruction_should_parse_rotate_to_right() throws Exception {
		Instruction instruction = new RotationInstructionExpression("D").parse();
		assertNotNull(instruction);
		assertEquals(new RotationInstruction(Rotation.RIGHT), instruction);
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_instruction_should_throw_mowitnow_invalid_data_exception_on_empty_value() throws Exception {
		new InstructionsExpression(" ").parse();
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_instruction_should_throw_mowitnow_invalid_data_exception_on_null_value() throws Exception {
		new InstructionsExpression(null).parse();
	}

	@Test
	public void parse_lawn_should_parse_well_defined_lawn() {
		Lawn lawn_14_5 = null;
		try {
			lawn_14_5 = new MowitnowExpressionParser().parseLawn("14 5");
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(lawn_14_5);
		assertEquals(new Lawn(new Coordinate(14, 5)), lawn_14_5);
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_empty_value() throws Exception {
		new MowitnowExpressionParser().parseLawn("");
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_null_value() throws Exception {
		new MowitnowExpressionParser().parseLawn(null);
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_lawn_should_throw_mowitnow_invalid_data_on_negative_x_value() throws Exception {
		new MowitnowExpressionParser().parseLawn("-1 5");
	}

	@Test(expected = MowitnowInvalidDataException.class)
	public void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_negative_y_value() throws Exception {
		new MowitnowExpressionParser().parseLawn("1 -5");
	}

	@Test(expected = MowitnowParseException.class)
	public void parse_lawn_should_throw_mowitnow_parse_exception_on_too_many_values() throws Exception {
		new MowitnowExpressionParser().parseLawn("10 5 12");
	}

	@Test
	public void parse_position_should_parse_well_defined_position() {
		Position position_3_4_E = null;
		try {
			position_3_4_E = new MowitnowExpressionParser().parsePosition("3 4 E");
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(position_3_4_E);
		assertEquals(new Position(new Coordinate(3, 4), Orientation.E), position_3_4_E);

		Position position_1_2_S = null;
		try {
			position_1_2_S = new MowitnowExpressionParser().parsePosition("1 2 S");
		} catch (MowitnowParseException mpe) {
			fail(mpe.getMessage());
		}
		assertNotNull(position_1_2_S);
		assertEquals(new Position(new Coordinate(1, 2), Orientation.S), position_1_2_S);
	}

	@Test(expected = MowitnowParseException.class)
	public void parse_position_should_throw_mowitnow_parse_exception_on_invalid_orientation() throws Exception {
		new MowitnowExpressionParser().parsePosition("3 4 4");
	}

	@Test(expected = MowitnowParseException.class)
	public void parse_position_should_throw_mowitnow_parse_exception_on_invalid_coord() throws Exception {
		new MowitnowExpressionParser().parsePosition("3 E W");
	}
}
