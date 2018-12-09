package com.example.hemant.paydigitaly;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView regBtn, newConnBtn, logBtn, payOthersBtn;
    ImageView i1,i2,i3,i4;
    Animation animFade;
    LinearLayout l;
    Button tID,homebtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regBtn=findViewById(R.id.t1);
        newConnBtn = findViewById(R.id.t2);
        logBtn = findViewById(R.id.t3);
        payOthersBtn = findViewById(R.id.t4);
        homebtn=findViewById(R.id.home);


        tID=(Button)this.findViewById(R.id.trackID);

        i1=findViewById(R.id.i1);
        i2=findViewById(R.id.i2);
        i3=findViewById(R.id.i3);
        i4=findViewById(R.id.i4);

        // l=(LinearLayout) this.findViewById(R.id.lay1);

        regBtn.setOnClickListener(this);
        logBtn.setOnClickListener(this);
        newConnBtn.setOnClickListener(this);
        payOthersBtn.setOnClickListener(this);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        tID.setOnClickListener(this);
        homebtn.setOnClickListener(this);

        animFade = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

    }

    public void onClick(View v) {

        if (v.getId() == R.id.t1) {
            //l.startAnimation(animFade);
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
        }

        if (v.getId() == R.id.t2) {
            if (ProjectConfig.AuthID == null) {
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, UserHome.class);
                startActivity(i);
            }
        }
        if (v.getId() == R.id.t3) {
            Intent i = new Intent(this, NewConnection.class);
            startActivity(i);
        }
        if (v.getId() == R.id.t4) {
            Intent i = new Intent(this, QuickPay.class);
            startActivity(i);
        }
        if (v.getId() == R.id.i1) {
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
        }
        if (v.getId() == R.id.i2) {

            if (ProjectConfig.AuthID == null) {
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, UserHome.class);
                startActivity(i);
            }

        }
        if (v.getId() == R.id.i3) {
            Intent i = new Intent(this, NewConnection.class);
            startActivity(i);
        }
        if (v.getId() == R.id.i4) {
            Intent i = new Intent(this, QuickPay.class);
            startActivity(i);
        }
        if (v.getId() == R.id.trackID) {
            Intent i = new Intent(this, TrackConnection.class);
            startActivity(i);
        }
         if(v.getId()== R.id.home)
         {

             Intent i = new Intent(this, UserHome.class);
             startActivity(i);

         }

        }
    }
