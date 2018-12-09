package com.example.hemant.paydigitaly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Complaint extends AppCompatActivity implements View.OnClickListener{

    Spinner sp,sp1;
    ArrayAdapter adp,adp1;
    String str[],bill[],connection[],infra[],maintance[];
    Button sendBtn, sendBtn2;
    String txt,txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        setTitle("Complaint");

        sp=(Spinner)this.findViewById(R.id.spinner1);
        sp1=(Spinner)this.findViewById(R.id.spinner2);
        sendBtn=(Button)this.findViewById(R.id.button14) ;
        sendBtn2=(Button)this.findViewById(R.id.button16) ;

        str= new String[]{"--Select--","Bill Related","Connection realated","Meter Related","Infra Related","Maintance Related"};

        maintance=new String[]{"--Select--","Flickering light","Thifing of electicity","Regular power cut","electricity fluctuation and request to change transformer","Complaint is not resolved","Electricity overhead wires problem Regularly","No electricity"};
        bill= new String[]{"--Select--","meter reding","WRONG BILL","Wrong and disproportionate bills","High electric bill","Bill without consumption",};
        connection= new String[]{"--Select--","Disconnection without notice and without reason",};
        infra=new String[]{"--Select--","Removal of electric pole new and old which is about to fall","Remove electricity pole","Less electricity pole","Re-Route Overhead 33 KV Transmission line/Wire","Request to shift the electronic pole"};


        adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,str);
        sp.setAdapter(adp);

        adp1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,bill);
        sp1.setAdapter(adp1);

        sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txt = sp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        sp1.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                txt1 = sp1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        sendBtn.setOnClickListener(this);
        sendBtn2.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.button14 ) {
            registerOnServer(txt);
        }

        if(v.getId() == R.id.button16 ) {
            registerOnServer();
        }
    }

    void registerOnServer(String txtComplaint){



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/complaint";

        Map<String, String> params = new HashMap<>();
        params.put("AuthID",ProjectConfig.AuthID);
        params.put("title", txt);
        params.put("complaint_text",txt1);

        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("success")){

                        Intent i=new Intent(getApplicationContext(),UserHome.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Complaint Successfully Submitted", Toast.LENGTH_SHORT).show();
                        finish();

                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Complaint is not submitted ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(me,"Error came",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    void registerOnServer(){

        EditText e1 = (EditText)this.findViewById(R.id.editText9);
        txt = e1.getText().toString();
        EditText e2 = (EditText)this.findViewById(R.id.editText7);
        txt1 = e1.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/complaint";

        Map<String, String> params = new HashMap<>();
        params.put("AuthID",ProjectConfig.AuthID);
        params.put("title", txt);
        params.put("complaint_text",txt1);

        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("success")){

                        Intent i=new Intent(getApplicationContext(),UserHome.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Complaint Successfully Submitted", Toast.LENGTH_SHORT).show();
                        finish();

                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Complaint is not submitted ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(me,"Error came",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}