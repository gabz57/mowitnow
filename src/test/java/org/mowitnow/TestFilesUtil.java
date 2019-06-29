package org.mowitnow;

public class TestFilesUtil {

    /**
     * Target the demo file, with content defined in the test document.
     */
    public static final String DEMO_DATA = "demo/" + "demoData";
    /**
     * Target an empty file.
     */
    public static final String EMPTY_FILE = "erroneous/" + "emptyFile";

    public static String getResourceFilePath(String filename) {
        return TestFilesUtil.class.getClassLoader().getResource(filename).getFile();
    }

}
