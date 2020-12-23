package com.feiyi.foo.utils;

import com.feiyi.foo.entity.CSVTable;
import com.feiyi.foo.entity.Foo;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class FooUtilsTest {

    @Test
    public void testLoadData() {
        long start = System.currentTimeMillis();
        //build temp Table
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //绑定文件
        csvTable.setDataSource(FooUtils.loadDataSource);
        long buildTable = System.currentTimeMillis();
        //加载数据到内存
        FooUtils.loadData(csvTable);
        List<Foo> fooList = csvTable.getCacheData();
        int res = 10000;
        Assert.assertTrue("Expect  " + res + "  , but get "
                + fooList.size(), res == fooList.size());

        String res1 = "Foo{columnA=1, columnB=1}";
        Assert.assertTrue("Expect  " + res1 + "  , but get "
                + fooList.get(0), res1.equals(fooList.get(0).toString()));

        String res10000 = "Foo{columnA=10000, columnB=0}";
        Assert.assertTrue("Expect  " + res10000 + "  , but get "
                + fooList.get(9999), res10000.equals(fooList.get(9999).toString()));
    }

    @Test
    public void testQuery() {
        //build temp Table
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //绑定文件
        csvTable.setDataSource(FooUtils.loadDataSource);
        //加载数据到内存
        FooUtils.loadData(csvTable);
        //Query
        Map<Integer, Integer> result = FooUtils.query(csvTable);
        List<String> res = buildResultList();
        Assert.assertTrue("Expect  List size 100, but get " + result.size(), result.size() == 100);

        for (Iterator<Map.Entry<Integer, Integer>> iterator = result.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            String line = entry.getValue() + FooUtils.delim + entry.getKey();
            Assert.assertTrue("Expect  List contains  " + line + ", but not ", res.contains(line));
        }
    }

    @Test
    public void testWriteToFile() throws Exception{
        //build temp Table
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);
        //绑定文件
        csvTable.setDataSource(FooUtils.loadDataSource);
        //加载数据到内存
        FooUtils.loadData(csvTable);
        //Query
        Map<Integer, Integer> result = FooUtils.query(csvTable);

        //刪除文件
        File file = new File(FooUtils.outPutSource);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }

        //创建文件并输出结果
        FooUtils.writeToFile(result);

        //校验
        try {
            List<String> res = buildResultList();
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

    public static List<String> buildResultList() {
        List<String> result = new ArrayList<String>();
        result.add("4951,1");
        result.add("4952,2");
        result.add("4953,3");
        result.add("4954,4");
        result.add("4955,5");
        result.add("4956,6");
        result.add("4957,7");
        result.add("4958,8");
        result.add("4959,9");
        result.add("4960,10");
        result.add("4961,11");
        result.add("4962,12");
        result.add("4963,13");
        result.add("4964,14");
        result.add("4965,15");
        result.add("4966,16");
        result.add("4967,17");
        result.add("4968,18");
        result.add("4969,19");
        result.add("4970,20");
        result.add("4971,21");
        result.add("4972,22");
        result.add("4973,23");
        result.add("4974,24");
        result.add("4975,25");
        result.add("4976,26");
        result.add("4977,27");
        result.add("4978,28");
        result.add("4979,29");
        result.add("4980,30");
        result.add("4981,31");
        result.add("4982,32");
        result.add("4983,33");
        result.add("4984,34");
        result.add("4985,35");
        result.add("4986,36");
        result.add("4987,37");
        result.add("4988,38");
        result.add("4989,39");
        result.add("4990,40");
        result.add("4991,41");
        result.add("4992,42");
        result.add("4993,43");
        result.add("4994,44");
        result.add("4995,45");
        result.add("4996,46");
        result.add("4997,47");
        result.add("4998,48");
        result.add("4999,49");
        result.add("5000,50");
        result.add("5001,51");
        result.add("5002,52");
        result.add("5003,53");
        result.add("5004,54");
        result.add("5005,55");
        result.add("5006,56");
        result.add("5007,57");
        result.add("5008,58");
        result.add("5009,59");
        result.add("5010,60");
        result.add("5011,61");
        result.add("5012,62");
        result.add("5013,63");
        result.add("5014,64");
        result.add("5015,65");
        result.add("5016,66");
        result.add("5017,67");
        result.add("5018,68");
        result.add("5019,69");
        result.add("5020,70");
        result.add("5021,71");
        result.add("5022,72");
        result.add("5023,73");
        result.add("5024,74");
        result.add("5025,75");
        result.add("5026,76");
        result.add("5027,77");
        result.add("5028,78");
        result.add("5029,79");
        result.add("5030,80");
        result.add("5031,81");
        result.add("5032,82");
        result.add("5033,83");
        result.add("5034,84");
        result.add("5035,85");
        result.add("5036,86");
        result.add("5037,87");
        result.add("5038,88");
        result.add("5039,89");
        result.add("5040,90");
        result.add("5041,91");
        result.add("5042,92");
        result.add("5043,93");
        result.add("5044,94");
        result.add("5045,95");
        result.add("5046,96");
        result.add("5047,97");
        result.add("5048,98");
        result.add("5049,99");
        result.add("5050,0");
        return result;
    }

}
