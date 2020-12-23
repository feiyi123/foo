package com.feiyi.foo.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class CSVTableTest {

    @Test
    public void testBuildCSVTable() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        String exceptRes = "CSVTable{tableName='foo', columnList={}, columnCount=0, dataSource='', cacheData=[]}";
        String actuallyRes = csvTable.toString();
        Assert.assertTrue("Expect  " + exceptRes + ", but get "
                + actuallyRes, exceptRes.equals(actuallyRes));
    }

    @Test
    public void testAddColumn() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //添加 列名为 H 类型为Integer的列
        Column columnH = new Column("H", Type.INTEGER);
        csvTable.addColumn(columnH);

        //验证
        Column columnRes = (Column) csvTable.columnList.get("H");

        Assert.assertTrue("Expect  " + columnH + ", but get "
                + columnRes.toString(), columnH.equals(columnRes));

        // 重复添加
        try {
            csvTable.addColumn(columnH);
        } catch (Error e) {
            String res = "Column H already exists";
            Assert.assertTrue("Expect  " + res + "  , but get "
                    + e.getMessage(), res.equals(e.getMessage()));
        }
    }

    @Test
    public void testAddColumnNoCheck() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //添加 列名为 H 类型为Integer的列
        Column columnH = new Column("H", Type.INTEGER);
        csvTable.addColumn(columnH);

        //验证
        Column columnRes = (Column) csvTable.columnList.get("H");

        Assert.assertTrue("Expect  " + columnH + ", but get "
                + columnRes.toString(), columnH.equals(columnRes));

        // 重复添加
        try {
            csvTable.addColumnNoCheck(columnH);
        } catch (Error e) {
            Assert.assertTrue("Except No exception "
                    + " actually  Caught exception : "
                    + e.getMessage(), false);
        }
    }

    @Test
    public void testFindColumn() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);

        //添加 列名为 H 类型为Integer的列
        String columnName = "H";
        Column columnH = new Column(columnName, Type.INTEGER);
        csvTable.addColumn(columnH);

        //验证Column H 是否存在
        Assert.assertTrue("Expect  Find Column H , but get "
                + csvTable.findColumn(columnName).toString(), csvTable.findColumn(columnName) != null);

        //验证不存在的Column HH 是否存在
        try {
            String columnName_HH = "HH";
            csvTable.findColumn(columnName_HH).toString();
            Assert.assertTrue("Expect Can't Find Column HH , but get "
                    + csvTable.findColumn(columnName_HH), false);
        } catch (Throwable e) {
            Assert.assertTrue("Expect Can't Find Column HH  , but get "
                    + e.getMessage(),  true);
        }
    }

    @Test
    public void testSetGetTableName() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);

        //验证
        Assert.assertTrue("Expect  " + tableName + ", but get "
                + csvTable.getTableName(), tableName.equals(csvTable.getTableName()));

        //reset tablename
        String newTableName = "foo2";
        csvTable.setTableName(newTableName);

        //验证
        Assert.assertTrue("Expect  " + newTableName + ", but get "
                + csvTable.getTableName(), newTableName.equals(csvTable.getTableName()));

    }

    @Test
    public void testSetGetColumns() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //添加 列名为 H 类型为Integer的列
        String columnH = "H";
        List<String> columnList = new ArrayList<String>();
        columnList.add(columnH);
        csvTable.setColumns(columnList);

        //验证
        Column columnRes = (Column) csvTable.columnList.get("H");

        Assert.assertTrue("Expect  " + columnH + ", but get "
                + columnRes.toString(), columnH.equals(columnRes.getColumnName()));

        // 重复添加
        try {
            List<String> columnList2 = new ArrayList<String>();
            columnList.add(columnH);
            csvTable.setColumns(columnList2);
        } catch (Error e) {
            String res = "Column H already exists";
            Assert.assertTrue("Expect  " + res + "  , but get "
                    + e.getMessage(), res.equals(e.getMessage()));
        }

    }

    @Test
    public void testSetGetDataSource() {
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        String path = "G:\\fmatrix\\foo\\src\\test\\java\\com\\feiyi\\foo\\entity\\CSVTableTest.java";
        csvTable.setDataSource(path);
        //验证
        Assert.assertTrue("Expect  " + path + ", but get "
                + csvTable.getDataSource(), path.equals(csvTable.getDataSource()));

        //reset tablename
        String newPath = "G:\\fmatrix\\foo\\src\\test\\java\\com\\feiyi\\foo\\entity\\CSVTableTest2.java";
        csvTable.setDataSource(newPath);

        //验证
        Assert.assertTrue("Expect  " + newPath + ", but get "
                + csvTable.getDataSource(), newPath.equals(csvTable.getDataSource()));

    }

    @Test
    public void testSetGetCacheData() {
        String tableName = "foo";

        CSVTable csvTable = new CSVTable(tableName);

        List<Foo> fooList = new ArrayList<Foo>();
        Foo foo1 = new Foo(1,1);
        Foo foo2 = new Foo(2,2);
        fooList.add(foo1);
        fooList.add(foo2);

        csvTable.setCacheData(fooList);

        //验证
        List<Foo> dataList = csvTable.getCacheData();
        for (int i = 0; i < dataList.size(); i++) {
            if (i == 0) {
                String exceptRes = "Foo{columnA=1, columnB=1}";
                Assert.assertTrue("Expect " + exceptRes + ", but get "
                        + dataList.get(i), exceptRes.equals(dataList.get(i).toString()));
            } else if (i == 1) {
                String exceptRes = "Foo{columnA=2, columnB=2}";
                Assert.assertTrue("Expect " + exceptRes + ", but get "
                        + dataList.get(i), exceptRes.equals(dataList.get(i).toString()));
            } else {
                Assert.assertTrue("Expect only two values, but there are more", false);
                break;
            }
        }
    }


}
