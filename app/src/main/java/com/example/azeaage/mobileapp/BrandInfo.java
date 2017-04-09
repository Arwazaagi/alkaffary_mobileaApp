package com.example.azeaage.mobileapp;

import java.util.ArrayList;

/**
 * Created by AZahrani on 4/5/2017.
 */

public class BrandInfo {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<ListInfo> getList() {
        return list;
    }

    public void setList(ArrayList<ListInfo> list) {
        this.list = list;
    }

    private String Name;
    private ArrayList<ListInfo> list=new ArrayList<ListInfo>();

}
