package com.example.hemant.paydigitaly;

import android.app.ActivityGroup;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabView extends ActivityGroup {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup(this.getLocalActivityManager());

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Complaints");
        spec.setContent(new Intent(getApplicationContext(),History.class));
        host.addTab(spec);


        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Bill History");
        spec.setContent(new Intent(getApplicationContext(),History2.class));
        host.addTab(spec);
    }
}
