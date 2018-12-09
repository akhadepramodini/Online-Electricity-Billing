package com.example.hemant.paydigitaly;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class History2 extends AppCompatActivity {

    private ProgressDialog progressDialog;
    ListView lv;
    int i;
    ArrayAdapter adp;
    String array2[];
    ArrayList<String> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);
        setTitle("Bills");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        lv=(ListView)this.findViewById(R.id.list1);

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/bill_history?AuthID="+ProjectConfig.AuthID;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {

                    JSONObject ob1 = new JSONObject(response.toString());
                    JSONArray array = ob1.getJSONArray("billing_history");
                    array2 = new String[]{};
                    lst = new ArrayList<String>(Arrays.asList(array2));
                    adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, lst);

                    for(i=0;i<array.length();i++) {
                        JSONObject ob2 = array.getJSONObject(i);
                        String last_date = ob2.getString("last_date");
                        String billing_units = ob2.getString("billing_units");
                        String bill_amount = ob2.getString("bill_amount");

                        String history = "Last_date: " + last_date + "\n" + "Bill_units: " + billing_units + "\n" + "Bill_amount: " + bill_amount;

                       // Toast.makeText(getApplicationContext(),history,Toast.LENGTH_SHORT).show();

                        lst.add(history);
                        lv.setAdapter(adp);
                        //lv.setBackgroundColor(808080);
                        adp.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(),"Error came",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
  public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
