package com.example.hemant.paydigitaly;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button loginBtn;
    EditText txtUserName,txtUserPassword;
    String Username, Userpassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        txtUserName=(EditText)this.findViewById(R.id.editText3);
        txtUserPassword=(EditText)this.findViewById(R.id.editText6);
        loginBtn=(Button)this.findViewById(R.id.button2);

        loginBtn.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

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

        Username = txtUserName.getText().toString();
        Userpassword = txtUserPassword.getText().toString();

        if ((!Username.equals("")) && Username.matches("[a-zA-Z]+") ) {
            if (!Userpassword.equals("")) {

                checkLogin();
            } else {
                txtUserPassword.setText("");
                txtUserPassword.setHint("Please Enter Password");
                txtUserPassword.requestFocus();
            }
        } else {
            txtUserName.setText("");
            txtUserName.setHint("Please Enter Valid Name");
            txtUserName.requestFocus();
        }

    }

    void checkLogin(){
        progressDialog.show();

        Username = txtUserName.getText().toString();
        Userpassword = txtUserPassword.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=ProjectConfig.URL+"/login";

        Map<String, String> params = new HashMap<>();
        params = new HashMap<>();
        params.put("username", Username);
        params.put("password", Userpassword);
        final Context me = this;

        JsonObjectRequest request = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {

        public void onResponse(JSONObject response) {
            progressDialog.hide();

           try {
                if(response.getString("AuthID")!=null){

                    ProjectConfig.AuthID = response.getString("AuthID");
                    Toast.makeText(getApplicationContext(), "Login Successfully ", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),UserHome.class);
                    startActivity(i);
                    finish();


                }else{
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),Login.class);
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