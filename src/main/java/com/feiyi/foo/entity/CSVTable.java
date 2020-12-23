package com.feiyi.foo.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class CSVTable {

    protected String tableName;
    public LinkedHashMap columnList = new LinkedHashMap();
    protected int columnCount;
    String dataSource = "";

    public List<Foo> cacheData = new ArrayList<Foo>();

    /**
     * Constructs a new CSVTable from the given arguments.
     *
     * @param tableName the table's Name
     */
    public CSVTable(String tableName) {
        this.tableName = tableName;
    }

    /**
     * add a column to the table
     */
    public void addColumn(Column column) {

        String name = column.getColumnName();

        if (findColumn(name) != null) {
            throw new Error("Column " + name + " already exists");
        }

        addColumnNoCheck(column);
    }

    /**
     * Returns the index of given column name or -1 if not found.
     */
    public Object findColumn(String name) {
        Object colunm = columnList.get(name);

        return colunm;
    }

    public void setColumns(List<String> colimnNames) {
        for (String columnName: colimnNames) {
            addColumn(new Column(columnName, Type.INTEGER));
        }
    }


        public void addColumnNoCheck(Column column) {

        columnList.put(column.getColumnName(), column);

        columnCount++;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LinkedHashMap getColumnList() {
        return columnList;
    }

    public void setColumnList(LinkedHashMap columnList) {
        this.columnList = columnList;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource.trim();
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public List<Foo> getCacheData() {
        return cacheData;
    }

    public void setCacheData(List<Foo> cacheData) {
        this.cacheData = cacheData;
    }

    @Override
    public String toString() {
        return "CSVTable{" +
                "tableName='" + tableName + '\'' +
                ", columnList=" + columnList +
                ", columnCount=" + columnCount +
                ", dataSource='" + dataSource + '\'' +
                ", cacheData=" + cacheData +
                '}';
    }
}
