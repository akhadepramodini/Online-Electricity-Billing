package com.example.hemant.paydigitaly;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hemant.paydigitaly.ProjectConfig;
import com.example.hemant.paydigitaly.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewBill extends AppCompatActivity {

    String months[],month;
    ArrayAdapter adp;
    Spinner sp;
    TextView txtname,txtunits,txtcost,txtdate;
    Button payBtn, pdfBtn;
    int i;
    String name, units, cost, date, filename, pdfViewURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);
        setTitle("My Bill");

        sp=(Spinner)this.findViewById(R.id.spinner3);
        txtname=(TextView)this.findViewById(R.id.t4);
        txtunits=(TextView)this.findViewById(R.id.t6);
        txtcost=(TextView) this.findViewById(R.id.t5);
        txtdate=(TextView)this.findViewById(R.id.t9);
        payBtn=(Button)this.findViewById(R.id.button);


        months= new String[]{"-- Select --","January","February","March","April","May",
                "June","July","August","September","October","November","December"};

        name = units = cost = date ="-";
        txtname.setText(name);
        txtunits.setText(units);
        txtcost.setText(cost);
        txtdate.setText(date);


        adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,months);
        sp.setAdapter(adp);

        sp.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                month = sp.getSelectedItem().toString();
                //Toast.makeText(getApplication(),month,Toast.LENGTH_SHORT).show();
                getBill(month);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }));

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url=ProjectConfig.URL+"/bill_history";

                Map<String, String> params = new HashMap<>();
                params = new HashMap<>();
                params.put("AuthID",ProjectConfig.AuthID);
                params.put("month",month);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,url,new JSONObject(params), new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        //progressDialog.hide();
                        try {
                            if(response.getString("success").equals("paid"))
                            {
                                Toast.makeText(getApplicationContext(),"Successfully paid",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error came",Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);

            }
        });

        pdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url=ProjectConfig.URL+"/bill";

                Map<String, String> params = new HashMap<>();
                params = new HashMap<>();
                params.put("AuthID",ProjectConfig.AuthID);
                params.put("month",month);


                JsonObjectRequest request = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        //progressDialog.hide();
                        //read name of pdf file append it to pdfViewURL and open webview with that URL.

                        try {
                           filename =  response.getString("billPath");
                            getPDF(filename);
                            Toast.makeText(getApplicationContext(),filename,Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(me,"Error came",Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(request);

            }



        });

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void getBill(String month)
    {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/bill_history";

        Map<String, String> params = new HashMap<>();
        params = new HashMap<>();
        params.put("month", month);
        params.put("AuthID",ProjectConfig.AuthID);
        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                //progressDialog.hide();

                if(response.toString()!=null) {
                    try {

                        JSONObject ob1 = new JSONObject(response.toString());

                        JSONObject ob2 = ob1.getJSONObject("billing_history");
                        name = ob2.getString("name");
                        units = ob2.getString("billing_units");
                        cost = ob2.getString("bill_amount");
                        date = ob2.getString("last_date");

                        txtname.setText(name);
                        txtunits.setText(units);
                        txtcost.setText(cost);
                        txtdate.setText(date);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    name = units = cost = date ="-";
                    txtname.setText(name);
                    txtunits.setText(units);
                    txtcost.setText(cost);
                    txtdate.setText(date);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(me,"Error came",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);

    }

    public  void getPDF(String filename)
    {
        final String pdfViewURL  = ProjectConfig.URL+"/view/pdf/"+filename;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfViewURL));
                            startActivity(browserIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }}
