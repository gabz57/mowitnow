package org.mowitnow;

public class TestFilesUtil {

	private static final String DEMO_DIR = "demo/";
	private static final String ERRONEOUS_DIR = "erroneous/";

	/**
	 * Target the demo file, with content defined in the test document.
	 */
	public static final String XEBIA_DATA = DEMO_DIR + "xebiaData";

	/**
	 * Target an empty file.
	 */
	public static final String EMPTY_FILE = ERRONEOUS_DIR + "emptyFile";

	public static String getResourceFilePath(String filename) {
		return TestFilesUtil.class.getClassLoader().getResource(filename).getFile();
	}

}
