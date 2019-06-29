package org.mowitnow.parser.expression;

import org.junit.jupiter.api.Test;
import org.mowitnow.exception.MowitnowInvalidDataException;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.instruction.Instruction;
import org.mowitnow.instruction.MovementInstruction;
import org.mowitnow.instruction.RotationInstruction;
import org.mowitnow.model.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MowitnowExpressionParserTest {

    @Test
    void parse_coord_should_parse_well_defined_coord() {

        Coordinate coord_5_5 = null;
        try {
            coord_5_5 = new CoordExpression("5 5").parse();
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(coord_5_5).isNotNull();
        assertThat(coord_5_5).isEqualTo(new Coordinate(5, 5));

        Coordinate coord_0_0 = null;
        try {
            coord_0_0 = new CoordExpression("0 0").parse();
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(coord_0_0).isNotNull();
        assertThat(coord_0_0).isEqualTo(new Coordinate(0, 0));

        Coordinate coord_1_2 = null;
        try {
            coord_1_2 = new CoordExpression("1 2").parse();
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(coord_1_2).isNotNull();
        assertThat(coord_1_2).isEqualTo(new Coordinate(1, 2));
    }

    @Test
    void parse_coord_should_throw_mowitnow_invalid_data_exception_on_null_coord() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new CoordExpression(null).parse()
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an null input");
    }

    @Test
    void parse_coord_should_throw_mowitnow_invalid_data_exception_on_empty_string() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new CoordExpression(" ").parse()
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an empty String");
    }

    @Test
    void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_x() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new CoordExpression("-1 2").parse()
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate cannot have a negative value");
    }

    @Test
    void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_y() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new CoordExpression("2 -1").parse()
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate cannot have a negative value");
    }

    @Test
    void parse_coord_should_throw_mowitnow_invalid_data_exception_on_negative_x_y() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new CoordExpression("-2 -1").parse()
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate cannot have a negative value");
    }

    @Test
    void parse_instructions_should_parse_well_defined_instructions() throws Exception {
        List<Instruction> instructions = new MowitnowExpressionParser().parseInstructions("FLR");
        assertThat(instructions).isNotNull();
        assertThat(instructions).hasSize(3);
        assertThat(instructions.get(0)).isEqualTo(new MovementInstruction(Movement.FORWARD));
        assertThat(instructions.get(1)).isEqualTo(new RotationInstruction(Rotation.LEFT));
        assertThat(instructions.get(2)).isEqualTo(new RotationInstruction(Rotation.RIGHT));
    }

    @Test
    void parse_instruction_should_parse_movement() throws Exception {
        Instruction instruction = new MovementInstructionExpression("F").parse();
        assertThat(instruction).isNotNull();
        assertThat(instruction).isEqualTo(new MovementInstruction(Movement.FORWARD));
    }

    @Test
    void parse_instruction_should_parse_rotate_to_left() throws Exception {
        Instruction instruction = new RotationInstructionExpression("L").parse();
        assertThat(instruction).isNotNull();
        assertThat(instruction).isEqualTo(new RotationInstruction(Rotation.LEFT));
    }

    @Test
    void parse_instruction_should_parse_rotate_to_right() throws Exception {
        Instruction instruction = new RotationInstructionExpression("R").parse();
        assertThat(instruction).isNotNull();
        assertThat(instruction).isEqualTo(new RotationInstruction(Rotation.RIGHT));
    }

    @Test
    void parse_instruction_should_throw_mowitnow_invalid_data_exception_on_empty_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new InstructionsExpression(" ").parse()
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an empty String");
    }

    @Test
    void parse_instruction_should_throw_mowitnow_invalid_data_exception_on_null_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new InstructionsExpression(null).parse()
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an null input");
    }

    @Test
    void parse_lawn_should_parse_well_defined_lawn() {
        Lawn lawn_14_5 = null;
        try {
            lawn_14_5 = new MowitnowExpressionParser().parseLawn("14 5");
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(lawn_14_5).isNotNull();
        assertThat(lawn_14_5).isEqualTo(new Lawn(new Coordinate(14, 5)));
    }

    @Test
    void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_empty_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new MowitnowExpressionParser().parseLawn("")
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an empty String");
    }

    @Test
    void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_null_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new MowitnowExpressionParser().parseLawn(null)
        );
        assertThat(exception.getMessage()).isEqualTo("Cannot parse an null input");
    }

    @Test
    void parse_lawn_should_throw_mowitnow_invalid_data_on_negative_x_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new MowitnowExpressionParser().parseLawn("-1 5")
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate cannot have a negative value");
    }

    @Test
    void parse_lawn_should_throw_mowitnow_invalid_data_exception_on_negative_y_value() {
        Exception exception = assertThrows(
            MowitnowInvalidDataException.class,
            () -> new MowitnowExpressionParser().parseLawn("1 -5")
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate cannot have a negative value");
    }

    @Test
    void parse_lawn_should_throw_mowitnow_parse_exception_on_too_many_values() {
        Exception exception = assertThrows(
            MowitnowParseException.class,
            () -> new MowitnowExpressionParser().parseLawn("10 5 12")
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate expression must be composed of two values");
    }

    @Test
    void parse_position_should_parse_well_defined_position() {
        Position position_3_4_E = null;
        try {
            position_3_4_E = new MowitnowExpressionParser().parsePosition("3 4 E");
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(position_3_4_E).isNotNull();
        assertThat(position_3_4_E).isEqualTo(new Position(new Coordinate(3, 4), Orientation.E));

        Position position_1_2_S = null;
        try {
            position_1_2_S = new MowitnowExpressionParser().parsePosition("1 2 S");
        } catch (MowitnowParseException mpe) {
            fail(mpe.getMessage());
        }
        assertThat(position_1_2_S).isNotNull();
        assertThat(position_1_2_S).isEqualTo(new Position(new Coordinate(1, 2), Orientation.S));
    }

    @Test
    void parse_position_should_throw_mowitnow_parse_exception_on_invalid_orientation() {
        Exception exception = assertThrows(
            MowitnowParseException.class,
            () -> new MowitnowExpressionParser().parsePosition("3 4 4")
        );
        assertThat(exception.getMessage()).isEqualTo("Unknown orientataion input, expected one of [N, E, W, S], was 4");
    }

    @Test
    void parse_position_should_throw_mowitnow_parse_exception_on_invalid_coord() {
        Exception exception = assertThrows(
            MowitnowParseException.class,
            () -> new MowitnowExpressionParser().parsePosition("3 E W")
        );
        assertThat(exception.getMessage()).isEqualTo("A coordinate must be composed of two integers");
    }
}
