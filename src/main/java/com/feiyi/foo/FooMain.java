package com.feiyi.foo;

import com.feiyi.foo.entity.CSVTable;
import com.feiyi.foo.utils.FooUtils;

import java.util.Map;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class FooMain {

    public static void main(String[] args) throws Exception {
        FooMain fooMain = new FooMain();
        fooMain.csvQuery(args);
    }

    public void csvQuery(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //build temp Table
        String tableName = "foo";
        CSVTable csvTable = new CSVTable(tableName);

        //绑定文件
        csvTable.setDataSource(FooUtils.loadDataSource);
        if (args != null && args.length == 2) {
            csvTable.setDataSource(args[0]);
            FooUtils.outPutSource = args[1];
        }
        long buildTable = System.currentTimeMillis();
        //加载数据到内存
        FooUtils.loadData(csvTable);
        long loadData = System.currentTimeMillis();
        //query
        Map<Integer, Integer> result = FooUtils.query(csvTable);
        long query = System.currentTimeMillis();
        //out put
        FooUtils.writeToFile(result);
        long writeToFile = System.currentTimeMillis();
        System.out.println("build table consume: " + (buildTable - start) + "ms");
        System.out.println("load data into memory consume : " + (loadData - buildTable) + "ms");
        System.out.println("query consume : " + (query - loadData) + "ms");
        System.out.println("create file and write consume : " + (writeToFile - query) + "ms");
        System.out.println("total time : " + (writeToFile - start) + "ms");
    }

}
