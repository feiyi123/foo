package com.feiyi.foo.entity;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class Column {
    private String columnName;
    protected Type dataType;

    public Column(String name, Type type) {
        this.columnName = name;
        this.dataType = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Type getDataType() {
        return dataType;
    }

    public void setDataType(Type dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType=" + dataType +
                '}';
    }
}
