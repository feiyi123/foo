package com.feiyi.foo.entity;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class Foo {
    Integer columnA;
    Integer columnB;

    public Foo(Integer columnA, Integer columnB) {
        this.columnA = columnA;
        this.columnB = columnB;
    }

    public Integer getColumnA() {
        return columnA;
    }

    public void setColumnA(Integer columnA) {
        this.columnA = columnA;
    }

    public Integer getColumnB() {
        return columnB;
    }

    public void setColumnB(Integer columnB) {
        this.columnB = columnB;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "columnA=" + columnA +
                ", columnB=" + columnB +
                '}';
    }
}
