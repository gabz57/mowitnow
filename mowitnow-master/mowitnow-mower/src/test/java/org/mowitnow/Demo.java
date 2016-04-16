package org.mowitnow;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mowitnow.MowitnowApp;

public class Demo {

	@Test
	public void run_demo_from_file() {
		try {

			final String inputParameter = TestFilesUtil.getResourceFilePath(TestFilesUtil.XEBIA_DATA);
			System.out.println("demo input parameter : " + inputParameter);
			MowitnowApp.main(new String[] { inputParameter });

		} catch (Exception exception) {
			fail(exception.getMessage());
		}
	}

}
