package com.example.test3;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<Activity> arraylist=new ArrayList<Activity>();

    public static void add_item(Activity activity){
        arraylist.add(activity);
    }

    public static void remove_item(Activity activity){
        if(activity!=null){
            activity.finish();
        }
    }

    public static void finishAll(){
        for(Activity activity:arraylist){
            activity.finish();
        }
    }
}
