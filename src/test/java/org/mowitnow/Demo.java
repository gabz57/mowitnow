package org.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.fail;

class Demo {

    @Test
    void run_demo_from_file() {
        try {

            final String inputParameter = TestFilesUtil.getResourceFilePath(TestFilesUtil.DEMO_DATA);
            System.out.println("demo input parameter : " + inputParameter);
            MowitnowApp.main(new String[]{inputParameter});

        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

}
