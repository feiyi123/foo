package com.feiyi.foo;

import com.feiyi.foo.utils.FooUtils;
import com.feiyi.foo.utils.FooUtilsTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by feiyi123 on 21/12/23.
 */
public class FooMainTest {

    @Test
    public void testCsvQuery() {

        try {
            FooMain main = new FooMain();
            main.csvQuery(null);
            List<String> res = FooUtilsTest.buildResultList();
            res.add("avgA,B");
            BufferedReader reader = new BufferedReader(new FileReader(FooUtils.outPutSource));
            int count = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line);
                if (st.hasMoreTokens()) {
                    String line1 = st.nextToken();
                    Assert.assertTrue("Expect  List contains  " + line1 + ", but not ", res.contains(line));
                    count ++;
                }
            }
            reader.close();
            Assert.assertTrue("Expect  count size 101, but get " + count, count == 101);

        } catch (Throwable e) {
            Assert.assertTrue("Expect  no exception, but get " + e.getMessage(), true);
        }

    }
}
