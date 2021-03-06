package com.example.hemant.paydigitaly;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class BotClass extends AppCompatActivity {

    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private boolean Leftside = false;
    private boolean Rightside = true;
    String url;
    String msg,message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_class);

        buttonSend = (Button) findViewById(R.id.send);

        listView = (ListView) findViewById(R.id.msgview);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.msg);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });

    }

    private void addResponseMessage(String message) throws JSONException {
        JSONObject jsonObject= new JSONObject(message);
        String action = jsonObject.getString("action");
        if(action.equals("no_action")){
            String data = jsonObject.getString("data");
            chatArrayAdapter.add(new ChatMessage(Rightside, data));
            return;
        }
        if(action.equals("view_pdf")){
            String data = jsonObject.getString("data");
            url=ProjectConfig.URL+"/view/pdf/"+data;
            Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }

    private void sendChatMessage() {
        String message = chatText.getText().toString();
        chatArrayAdapter.add(new ChatMessage(Leftside, message));
        Map<String, String> params = new HashMap<String, String>();
        params.put("query", message);
        //
        // params.put("AuthID",Config.AuthID);

        RequestQueue rq = Volley.newRequestQueue(getBaseContext());

        JsonObjectRequest st = new JsonObjectRequest(Request.Method.POST, ProjectConfig.URL+"/bot", new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject ob1 = new JSONObject(response.toString());
                    String Rmessage=response.toString();
                    addResponseMessage(Rmessage);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                //         Toast.makeText(getApplicationContext(), "Response" + response, Toast.LENGTH_LONG).show();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getApplicationContext(), "Response" + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap map = new HashMap<String,String>();
                map.put("authID",ProjectConfig.AuthID);

                return map;
            }
        };
        chatText.setText("");
        rq.add(st);
    }
}
