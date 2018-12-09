package com.example.hemant.paydigitaly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmScan extends AppCompatActivity implements View.OnClickListener {
EditText customerID,amount;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_scan);
        customerID=(EditText)this.findViewById(R.id.customerID);
        amount=(EditText)this.findViewById(R.id.amount);
        button2=(Button)this.findViewById(R.id.button2);

 Intent t=getIntent();
//
     customerID.setText(t.getStringExtra("customerID"));
       amount.setText(t.getStringExtra("amount"));

        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_LONG).show();
    }
}
