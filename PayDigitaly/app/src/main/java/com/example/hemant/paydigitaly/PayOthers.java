package com.example.hemant.paydigitaly;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PayOthers extends AppCompatActivity implements View.OnClickListener{

    Button payBtn,viewBtn;
    EditText eID, eAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_others);
        setTitle("Pay Others");

        payBtn =(Button)this.findViewById(R.id.button15);
        viewBtn =(Button)this.findViewById(R.id.button16);
        eID=(EditText)this.findViewById(R.id.editText10);
        eAmount=(EditText)this.findViewById(R.id.editText11);


        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        payBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);

            }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button15)
        {
            payMethod();
        }

        if(v.getId()==R.id.button16)
        {
            viewMethod();
        }

    }


    public void payMethod() {

        String id=eID.getText().toString();

        Map<String, String> m = new HashMap<>();
        m.put("customer_id",id);

        String url=ProjectConfig.URL+"/pay";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,url, new JSONObject(m), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("Payment successful"))
                    {
                        Toast.makeText(getApplicationContext(),"Payment successful",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Payment unsuccessful",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Toast.makeText(getApplicationContext(),""+response.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error came",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);

    }

    public void viewMethod() {

        String id=eID.getText().toString();

        Map<String, String> m = new HashMap<>();
        m.put("customer_id",id);

        String url=ProjectConfig.URL+"/amount";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url, new JSONObject(m), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.toString() != null) {
                    try {

                        JSONObject ob1 = new JSONObject(response.toString());

                        JSONObject ob2 = ob1.getJSONObject("Amount");
                        String amount = ob2.getString("bill_amount");
                        eAmount.setText(amount);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    eAmount.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error came",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);

 //       eAmount.setText("Congratulations...free bill...");

    }
}
