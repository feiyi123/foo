package com.feiyi.foo.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class ColumnTest {

    @Test
    public void testBuildColumn() {
        //构造column对象
        Column column = new Column("a", Type.INTEGER);
        String exceptRes = "Column{columnName='a', dataType=INTEGER}";
        String actuallyRes = column.toString();
        Assert.assertTrue("Expect  " + exceptRes + ", but get " + actuallyRes, exceptRes.equals(actuallyRes));
    }

    @Test
    public void testSetGet() {

        String exceptRes_A = "Column{columnName='a', dataType=INTEGER}";
        String exceptRes_B = "Column{columnName='b', dataType=INTEGER}";
        //构造Column对象
        Column column = new Column("a", Type.INTEGER);
        String actuallyRes = column.toString();

        Assert.assertTrue("Expect  " + exceptRes_A
                + ", but get " + actuallyRes, exceptRes_A.equals(actuallyRes));
        Assert.assertTrue("Expect  a , but get "
                + column.getColumnName(), "a".equals(column.getColumnName()));

        //设置column name 为 b
        column.setColumnName("b");
        actuallyRes = column.toString();

        Assert.assertTrue("Expect  " + exceptRes_B
                + ", but get " + actuallyRes, exceptRes_B.equals(actuallyRes));
        Assert.assertTrue("Expect  b , but get "
                + column.getColumnName(), "b".equals(column.getColumnName()));
        Assert.assertTrue("Expect  " + Type.INTEGER + " , but get "
                + column.getDataType(), Type.INTEGER.equals(column.getDataType()));

    }

}
