package com.example.hemant.paydigitaly;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hemant.paydigitaly.MainActivity;
import com.example.hemant.paydigitaly.ProjectConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    Button registerBtn;
    EditText txtName,txtPassword, txtContact, txtID;
    String name, password, cid, contact;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.hemant.paydigitaly.R.layout.activity_registration);
        setTitle("Registration");

        txtName=(EditText)this.findViewById(com.example.hemant.paydigitaly.R.id.editText);
        txtPassword=(EditText)this.findViewById(com.example.hemant.paydigitaly.R.id.editText2);
        txtID=(EditText)this.findViewById(com.example.hemant.paydigitaly.R.id.editText4);
        txtContact=(EditText)this.findViewById(com.example.hemant.paydigitaly.R.id.editText5);
        registerBtn=(Button)this.findViewById(com.example.hemant.paydigitaly.R.id.button1);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        registerBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
    }

    @Override
    public void onClick(View v) {

        checkValidation();
            }

    void checkValidation(){

        name = txtName.getText().toString();
        password = txtPassword.getText().toString();
        cid = txtID.getText().toString();
        contact = txtContact.getText().toString();

        if ((!name.equals("")) && name.matches("[a-zA-Z]+") ) {
            if (!cid.equals(""))  {
                    if (!password.equals("")) {
                        if ((!contact.equals(""))&&contact.length()==10&&contact.matches("^[7|8|9][0-9]+"))
                        {
                            registerOnServer();

                        } else {
                            txtID.setText("");
                            txtContact.setHint("Please Enter Contact");
                            txtID.requestFocus();
                        }

                    } else {
                        txtPassword.setText("");
                        txtPassword.setHint("Please Enter Password");
                        txtPassword.requestFocus();
                    }

            }else {
                txtContact.setText("");
                txtID.setHint("Please Enter Valid Customer ID");
                txtContact.requestFocus();
            }
        } else {
            txtName.setText("");
            txtName.setHint("Please Enter Valid Name");
            txtName.requestFocus();
        }

    }



    void registerOnServer(){
        progressDialog.show();
        name = txtName.getText().toString();
        password = txtPassword.getText().toString();
        cid = txtID.getText().toString();
        contact = txtContact.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/user";

        Map<String, String> params = new HashMap<>();
        params = new HashMap<>();
        params.put("username", name);
        params.put("customerID", cid);
        params.put("password", password);
        params.put("phone", contact);
        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("result").equals("success")){

                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Registration Successful", Toast.LENGTH_SHORT).show();
                        finish();

                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Registration Unsuccessful: ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
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