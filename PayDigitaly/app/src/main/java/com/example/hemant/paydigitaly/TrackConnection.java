package com.example.hemant.paydigitaly;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hemant.paydigitaly.ProjectConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TrackConnection extends AppCompatActivity {

    private ProgressDialog progressDialog;
    TextView msg;
    EditText tID;
    ImageButton rBtn;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_connection);
        setTitle("Track your connection");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        rBtn=(ImageButton)this.findViewById(R.id.refreshButton);
        tID=(EditText) this.findViewById(R.id.trackID);

        msg=(TextView)this.findViewById(R.id.textView3);

//        tID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                id=tID.getText().toString();
//                ProjectConfig.TrackID=id;
//
//                getTrack();
//            }
//        });

        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id=tID.getText().toString();
                ProjectConfig.TrackID=id;
//                Intent intent = new Intent(getApplicationContext(),TrackConnection.class);
//                startActivity(intent);

                getTrack();
            }
        });


    }

    public void getTrack()
    {
        Toast.makeText(getApplicationContext(),""+ProjectConfig.TrackID,Toast.LENGTH_SHORT).show();
        tID.setText(ProjectConfig.TrackID);

        Map<String,String> params =new HashMap<>();
        params.put("tracking_id",ProjectConfig.TrackID);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/track";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.PUT,url, new JSONObject(params), new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                progressDialog.hide();

                try {

                    JSONObject ob1 = new JSONObject(response.toString());
                    //JSONArray array = ob1.getJSONArray("Complaint");
                    JSONObject ob2 = ob1.getJSONObject("result");
                        String message = ob2.getString("Message");
                        String status = ob2.getString("Status");

                        String tracking_info = "Message: " + message + "\n" + "Status: " + status;

                        //Toast.makeText(getApplicationContext(),history,Toast.LENGTH_SHORT).show();
                        msg.setText(tracking_info);

                    } catch (JSONException e1) {
                    e1.printStackTrace();
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
