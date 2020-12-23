package com.feiyi.foo.utils;

import com.feiyi.foo.entity.CSVTable;
import com.feiyi.foo.entity.Foo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FooUtils {
    public static String rootPath ;
    static {
        File file = new File(".");
        rootPath = file.getAbsolutePath().replace("\\.",  "").replace("\\foo", "");
    }
    public static String loadDataSource = rootPath + "\\src\\main\\resources\\foo.csv";
    public static String outPutSource =  rootPath + "\\src\\test\\java\\com\\feiyi\\foo\\output\\result.csv";
    public final static String delim = ",";



    public static void loadData(CSVTable csvTable) {
        String readpath = csvTable.getDataSource() != null
                ? csvTable.getDataSource() : loadDataSource;
        File inFile = new File(readpath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            Integer columnA;
            Integer columnB;
            while (reader.ready()) {
                String line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, delim);
                if (st.hasMoreTokens()) {
                    columnA = Integer.valueOf(st.nextToken().trim());
                    columnB = Integer.valueOf(st.nextToken().trim());

                    Foo foo = new Foo(columnA, columnB);
                    csvTable.getCacheData().add(foo);
                }
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Integer> query(CSVTable csvTable) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        List<Foo> dataList = csvTable.getCacheData();
        int index = dataList.size();
        Map<Integer, List<Integer>> datas = new HashMap<Integer, List<Integer>>();
        for (int l = 0; l < index; l++) {
            Foo foo = dataList.get(l);
            //SELECT AVG(a),b from foo group by b;
            Integer columnA = foo.getColumnA();
            Integer columnB = foo.getColumnB();
            List<Integer> list = new ArrayList<Integer>();
            if (datas.get(columnB) != null) {
                list = datas.get(columnB);
            }
            list.add(columnA);
            datas.put(columnB, list);
        }
        for (Iterator<Map.Entry<Integer, List<Integer>>> iterator = datas.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, List<Integer>> entry = iterator.next();
            List<Integer> colAList = entry.getValue();
            Integer colB = entry.getKey();

            Integer aDatas = 0;
            for (int a = 0; a < colAList.size(); a++) {
                aDatas += colAList.get(a);
            }
            Integer avgA = aDatas / colAList.size();
            result.put(colB, avgA);
        }

        return result;
    }

    public static void writeToFile(Map<Integer, Integer> datas) throws Exception {
        File file = new File(outPutSource);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outPutSource));
        String header = "avgA,B";
        bufferedWriter.write(header);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        for (Iterator<Map.Entry<Integer, Integer>> iterator = datas.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            String line = entry.getValue() + delim + entry.getKey();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

}
