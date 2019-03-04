package com.company;

import java.util.ArrayList;

public class CustomTable {
    private String tableName;
    private ArrayList<String> columnNames;

    public CustomTable(String tableName, String id, String name, String price, String location){
        this.tableName = tableName;
        this.columnNames = new ArrayList<String>();

        // Gemmer columnNames som arrayList
        this.columnNames.add(id);
        this.columnNames.add(name);
        this.columnNames.add(price);
        this.columnNames.add(location);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(ArrayList<String> columnNames) {
        this.columnNames = columnNames;
    }
}
