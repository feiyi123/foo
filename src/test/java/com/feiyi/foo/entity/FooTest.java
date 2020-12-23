package com.feiyi.foo.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class FooTest {
    @Test
    public void testBuildFoo() {
        //构造Foo对象
        Foo foo = new Foo(1,1);
        String exceptRes = "Foo{columnA=1, columnB=1}";
        String actuallyRes = foo.toString();
        Assert.assertTrue("Expect  " + exceptRes + ", but get "
                + actuallyRes, exceptRes.equals(actuallyRes));
    }

    @Test
    public void testSetGet() {
        //构造Foo对象
        Foo foo = new Foo(1,1);
        String exceptRes = "Foo{columnA=1, columnB=1}";
        String actuallyRes = foo.toString();
        Assert.assertTrue("Expect  " + exceptRes + ", but get "
                + actuallyRes, exceptRes.equals(actuallyRes));

        //Foo columnA 为 2
        foo.setColumnA(2);
        Integer columnARes = foo.getColumnA();
        Assert.assertTrue("Expect  " + 2
                + ", but get " + columnARes, columnARes==2);

        //Foo columnB 为 6
        foo.setColumnB(6);
        Integer columnBRes = foo.getColumnA();
        Assert.assertTrue("Expect  " + 6
                + ", but get " + columnBRes, columnBRes==2);
    }
}
