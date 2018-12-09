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
import android.widget.Spinner;
import android.widget.TextView;
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

public class NewConnection extends AppCompatActivity implements View.OnClickListener {

    Spinner sp,sp1;
    ArrayAdapter adp,adp1;
    String str[];
    String str1[];
    String consumerCat, supplyTy;
    TextView txtname, txtemail,txtsociety, txtsurvey;
    TextView txtvillage,txttaluka,txtdistrict,txtpin;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_connection);
        setTitle("New Connection");

        sp=(Spinner)this.findViewById(R.id.spinner);
        sp1=(Spinner)this.findViewById(R.id.spinner1);
        txtname=(TextView)this.findViewById(R.id.editText8);
        txtemail=(TextView)this.findViewById(R.id.editText2);
        txtsurvey=(TextView)this.findViewById(R.id.editText5);
        txtsociety=(TextView)this.findViewById(R.id.editText6);
        txtvillage=(TextView)this.findViewById(R.id.editText);
        txttaluka=(TextView)this.findViewById(R.id.editText7);
        txtdistrict=(TextView)this.findViewById(R.id.editText10);
        txtpin=(TextView)this.findViewById(R.id.editText12);

        reg=(Button)this.findViewById(R.id.button1);

        str= new String[]{"--Select--","LT-SUPPLY","HT-SUPPLY","EHV"};
        str1= new String[]{"--Select--","Single Phase","Three Phase","HT Supply"};

        adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,str);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                consumerCat = sp.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


        adp1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,str1);
        sp1.setAdapter(adp1);

        sp1.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                supplyTy = sp1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));


        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        reg.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String name=txtname.getText().toString();
        String email=txtemail.getText().toString();
        String survey =txtsurvey.getText().toString();
        String society=txtsociety.getText().toString();
        String village=txtvillage.getText().toString();
        String taluka=txttaluka.getText().toString();
        String district = txtdistrict.getText().toString();
        String pincode = txtpin.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/connection";

        Map<String, String> params = new HashMap<>();
        params.put("consumerType", consumerCat);
        params.put("supplyType", supplyTy);
        params.put("name", name);
        params.put("emailId", email);
        params.put("surveyNumber", survey);
        params.put("societyName", society);
        params.put("village", village);
        params.put("taluka", taluka);
        params.put("district", district);
        params.put("pincode", pincode);
        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
               // progressDialog.hide();

                try {
                    if(response.getString("result").equals("hello")){

                        Toast.makeText(getApplicationContext(), "Connection Successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();


                    }else{
                        Toast.makeText(getApplicationContext(), "Connection Unsuccessful", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),NewConnection.class);
                        startActivity(i);
                        finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(me,"Error came",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Connection Successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        requestQueue.add(request);
    }

}
