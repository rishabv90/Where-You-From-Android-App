package com.example.karthikslaptop.wuatest1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class Detail extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //Set the ScrollView
        ScrollView scroller =new ScrollView(this);
        //Set the TextView
        TextView tv =new TextView(this);

        //Set the data at TextView
        tv.setText(NewsActivity.newsdescriptionvector.get(NewsActivity.index));

        scroller.addView(tv);

        setContentView(scroller);
    }



}
