package com.feiyi.foo;

import com.feiyi.foo.entity.CSVTableTest;
import com.feiyi.foo.entity.ColumnTest;
import com.feiyi.foo.entity.FooTest;
import com.feiyi.foo.entity.TypeTest;
import com.feiyi.foo.utils.FooUtilsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ColumnTest.class,
        FooTest.class,
        TypeTest.class,
        FooTest.class,
        CSVTableTest.class,
        FooUtilsTest.class,
        FooMainTest.class
})
public class CsvQuerySuite {
}
