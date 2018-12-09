package com.example.hemant.paydigitaly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class History extends AppCompatActivity {

    private ProgressDialog progressDialog;
    ListView lv;
    int i;
    ArrayAdapter adp;
    String array2[];
    ArrayList<String> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Complaints");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        lv=(ListView)this.findViewById(R.id.list1);


        Map<String,String> params =new HashMap<>();
        params.put("AuthID",ProjectConfig.AuthID);

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url=ProjectConfig.URL+"/complaint";

            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.PUT,url, new JSONObject(params), new Response.Listener<JSONObject>() {

                public void onResponse(JSONObject response) {

                    progressDialog.hide();

                    try {

                        JSONObject ob1 = new JSONObject(response.toString());
                        JSONArray array = ob1.getJSONArray("Complaint");
                        array2 = new String[]{};
                        lst = new ArrayList<String>(Arrays.asList(array2));
                        adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, lst);

                        for(i=0;i<array.length();i++) {
                            JSONObject ob2 = array.getJSONObject(i);
                            String title = ob2.getString("title");
                            String complaint = ob2.getString("complaint_text");
                            String status = ob2.getString("status");

                            String history = "Title: " + title + "\n" + "Complaint: " + complaint + "\n" + "Status: " + status;

                            //Toast.makeText(getApplicationContext(),history,Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(getApplicationContext(),"Error came",Toast.LENGTH_LONG).show();
                }
            });

            requestQueue.add(jsObjRequest);
    }

}
