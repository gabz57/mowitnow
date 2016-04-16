package org.mowitnow.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mowitnow.TestFilesUtil;
import org.mowitnow.exception.MowitnowParseException;
import org.mowitnow.model.MowitnowAppData;

public class MowitnowFileParserTest {

	@Test
	public void should_not_throw_mowitnow_invalid_data_exception_on_mower_coord_inside_lawn() {
		List<String> mowerAndInstructionsLines = Arrays.asList("0 0 N", "GA", "1 2 N", "GA", "4 4 N", "GA");
		try {
			new MowitnowFileParser.MowitnowAppDataBuilder("4 4", mowerAndInstructionsLines).build();
		} catch (MowitnowParseException mpe) {
			fail("An exception was thrown : " + mpe.getMessage());
		}
	}

	@Test
	public void should_read_xebia_file_data() {
		MowitnowAppData data = null;
		try {
			data = new MowitnowFileParser().read(new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.XEBIA_DATA)));
		} catch (MowitnowParseException mpe) {
			fail("An exception was thrown : " + mpe.getMessage());
		}
		assertNotNull(data);
		assertNotNull(data.getLawn());
		assertEquals(2, data.getMowerInitialDataList().size());
	}

	@Test(expected = MowitnowParseException.class)
	public void should_throw_mowitnow_parse_exception_on_empty_file() throws Exception {
		File file = new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.EMPTY_FILE));
		new MowitnowFileParser().read(file);
	}

	@Test(expected = MowitnowParseException.class)
	public void should_throw_mowitnow_parse_exception_on_directory_file() throws Exception {
		File file = new File(TestFilesUtil.getResourceFilePath(TestFilesUtil.XEBIA_DATA));
		new MowitnowFileParser().read(file.getParentFile());
	}

	@Test(expected = MowitnowParseException.class)
	public void should_throw_mowitnow_parse_exception_on_null_file() throws Exception {
		new MowitnowFileParser().read(null);
	}
}
