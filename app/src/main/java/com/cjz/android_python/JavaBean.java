package com.cjz.android_python;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class JavaBean {
    private String name;
    private List<String> data;

    public JavaBean(String n){
        this.name = n;
        data = new ArrayList<String>();
    }

    public void setData(String el){
        this.data.add(el);
    }

    public void print(){
        for (String it: data) {
            Log.d("Java Bean - "+this.name,it);
        }
    }
}
