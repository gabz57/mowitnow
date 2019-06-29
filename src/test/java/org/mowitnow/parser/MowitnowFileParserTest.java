package org.mowitnow.parser;

import org.junit.jupiter.api.Test;
import org.mowitnow.TestFilesUtil;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.model.MowitnowAppData;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MowitnowFileParserTest {

    @Test
    void should_not_throw_mowitnow_invalid_data_exception_on_mower_coord_inside_lawn() {
        List<String> mowerAndInstructionsLines = Arrays.asList("0 0 N", "LF", "1 2 N", "LF", "4 4 N", "LF");
        try {
            new MowitnowFileParser.MowitnowAppDataBuilder("4 4", mowerAndInstructionsLines).build();
        } catch (MowitnowParseException mpe) {
            fail("An exception was thrown : " + mpe.getMessage());
        }
    }

    @Test
    void should_read_demo_file_data() {
        MowitnowAppData data = null;
        try {
            data = new MowitnowFileParser().read(new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.DEMO_DATA)));
        } catch (MowitnowParseException mpe) {
            fail("An exception was thrown : " + mpe.getMessage());
        }
        assertThat(data).isNotNull();
        assertThat(data.getLawn()).isNotNull();
        assertThat(data.getMowerInitialDataList()).hasSize(2);
    }

    @Test
    void should_throw_mowitnow_parse_exception_on_empty_file() {
        Exception exception = assertThrows(MowitnowParseException.class, () -> {
            File file = new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.EMPTY_FILE));
            new MowitnowFileParser().read(file);
        });
        assertThat(exception.getMessage()).isEqualTo("No lines to read");
    }

    @Test
    void should_throw_mowitnow_parse_exception_on_directory_file() {
        Exception exception = assertThrows(MowitnowParseException.class, () -> {
            File file = new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.DEMO_DATA));
            new MowitnowFileParser().read(file.getParentFile());
        });
        assertThat(exception.getMessage()).startsWith("Input file is a directory '");
        assertThat(exception.getMessage()).endsWith("/build/resources/test/demo'");
    }

    @Test
    void should_throw_mowitnow_parse_exception_on_null_file() {
        Exception exception = assertThrows(MowitnowParseException.class, () -> new MowitnowFileParser().read(null));
        assertThat(exception.getMessage()).isEqualTo("Cannot read null file");
    }
}
