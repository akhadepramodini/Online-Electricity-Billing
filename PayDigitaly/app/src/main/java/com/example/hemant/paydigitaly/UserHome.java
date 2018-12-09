package com.example.hemant.paydigitaly;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserHome extends AppCompatActivity implements View.OnClickListener{

    TextView viewBillBtn, historyBtn, complaintBtn, notificationBtn;
    ImageButton logout;
    ImageView i1,i2,i3,i4;
    Button botBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        setTitle("User Home");

        viewBillBtn=(TextView)this.findViewById(R.id.t5);
        historyBtn = (TextView) this.findViewById(R.id.t6);
        complaintBtn = (TextView) this.findViewById(R.id.t7);
        notificationBtn = (TextView) this.findViewById(R.id.t8);
        logout=(ImageButton) this.findViewById(R.id.logout);
        botBtn = (Button)this.findViewById(R.id.button5);

        viewBillBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        complaintBtn.setOnClickListener(this);
        notificationBtn.setOnClickListener(this);
        logout.setOnClickListener(this);

        i1=(ImageView)this.findViewById(R.id.i1);
        i2=(ImageView)this.findViewById(R.id.i2);
        i3=(ImageView)this.findViewById(R.id.i3);
        i4=(ImageView)this.findViewById(R.id.i4);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        botBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.t5) {
            Intent i = new Intent(this, ViewBill.class);
            startActivity(i);
        }

        if(v.getId() == R.id.t6) {
            Intent i = new Intent(this, History.class);
            startActivity(i);
        }
        if(v.getId() == R.id.t7) {
            Intent i = new Intent(this, Complaint.class);
            startActivity(i);
        }
        if(v.getId() == R.id.t8) {
            Intent i = new Intent(this, Notifications.class);
            startActivity(i);
        }
        if(v.getId() == R.id.logout) {
            ProjectConfig.AuthID=null;
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if(v.getId() == R.id.i1) {
            Intent i = new Intent(this, ViewBill.class);
            startActivity(i);
        }
        if(v.getId() == R.id.i2) {
            Intent i = new Intent(this, TabView.class);
            startActivity(i);
        }
        if(v.getId() == R.id.i3) {
            Intent i = new Intent(this, Complaint.class);
            startActivity(i);
        }
        if(v.getId() == R.id.i4) {
            Intent i = new Intent(this, Notifications.class);
            startActivity(i);
        }
        if(v.getId() == R.id.button5) {
            Intent i = new Intent(this, BotClass.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
