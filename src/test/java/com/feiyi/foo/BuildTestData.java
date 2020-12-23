package com.feiyi.foo;

import com.feiyi.foo.utils.FooUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by feiyi123 on 21/12/23.
 */
public class BuildTestData {

    @Test
    public void buildData() throws  Exception {
        File file = new File(FooUtils.loadDataSource);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FooUtils.loadDataSource));
        for (int i = 1;  i <= 10000 ; i ++ ) {
            String s = i  + FooUtils.delim + i % 100;
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}
