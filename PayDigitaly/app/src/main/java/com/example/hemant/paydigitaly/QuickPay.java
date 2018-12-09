package com.example.hemant.paydigitaly;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class QuickPay extends AppCompatActivity implements View.OnClickListener {
    TextView t9,t10,t11,t12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_pay);
        setTitle("Quick Pay");

        t9=(TextView) this.findViewById(R.id.t9);
        t10=(TextView)this.findViewById(R.id.t10);
        t11=(TextView) this.findViewById(R.id.t11);
        t12=(TextView) this.findViewById(R.id.t12);
        t9.setOnClickListener(this);
        t10.setOnClickListener(this);
        t11.setOnClickListener(this);
        t12.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.t9) {
            Intent i = new Intent(this, ReaderActivity.class);
            startActivity(i);
        }

        if(v.getId() == R.id.t10) {
            Intent i = new Intent(this, ReaderActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.t11) {
            Intent i = new Intent(this, PayOthers.class);
            startActivity(i);
        }

        if(v.getId() == R.id.t12) {
            Intent i = new Intent(this, PayOthers.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
