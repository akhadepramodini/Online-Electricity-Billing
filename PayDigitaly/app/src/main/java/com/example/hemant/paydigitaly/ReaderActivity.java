package com.example.hemant.paydigitaly;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hemant.paydigitaly.ConfirmScan;
import com.example.hemant.paydigitaly.ProjectConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null)
            {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {


                String res=null;
                res= result.getContents();
                String [] str=res.split(":");
                Intent rIntent = new Intent(this, ConfirmScan.class);
                rIntent.putExtra("customerID",str[0]);

                rIntent.putExtra("amount",str[1]);


                Map<String, String> params = new HashMap<String, String>();
                params.put("bill_id", str[0]);



                RequestQueue rq = Volley.newRequestQueue(getBaseContext());

                JsonObjectRequest st = new JsonObjectRequest(Request.Method.POST,ProjectConfig.URL+"/pay", new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject ob1 = new JSONObject(response.toString());
                            Toast.makeText(getApplicationContext(), "successful" , Toast.LENGTH_LONG).show();

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Response" + error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

                rq.add(st);
                startActivity(rIntent);

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

