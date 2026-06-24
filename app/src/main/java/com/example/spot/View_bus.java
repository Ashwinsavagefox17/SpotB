package com.example.spot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class View_bus extends AppCompatActivity {
    ListView li;
    SharedPreferences sh;
    EditText e1,e2;
    String url, ip;
    String[] bus_no, bus_name, start_time, end_time, pid,bid,fr,er;
//    Button searchButton = findViewById(R.id.button9);



    public static  String bus_id;   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);

        e1=(EditText)findViewById(R.id.editTextTextPersonName12);
        e2=(EditText)findViewById(R.id.editTextTextPersonName13);

        e1.addTextChangedListener(textWatcher);
        e2.addTextChangedListener(textWatcher);

        li = findViewById(R.id.li);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("url", "");
        url = ip + "view_bus";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js = jsonObj.getJSONArray("q");
                                pid = new String[js.length()];
                                bus_no = new String[js.length()];
                                bus_name = new String[js.length()];
                                start_time = new String[js.length()];
                                end_time = new String[js.length()];
                                bid = new String[js.length()];
                                er = new String[js.length()];
                                fr = new String[js.length()];


                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    pid[i] = u.getString("id");
                                    bus_no[i] = u.getString("bus_number");
                                    bus_name[i] = u.getString("bus_name");
                                    start_time[i] = u.getString("from_time");
                                    end_time[i] = u.getString("end_time");
                                    bid[i] = u.getString("bid");
                                    fr[i] = u.getString("end_route");
                                    er[i] = u.getString("from_route");
                                }

                                // Set adapter for ListView
                                li.setAdapter(new custom_view_bus(getApplicationContext(), pid, bus_no, bus_name, start_time, end_time,bid,er,fr));
                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pid", sh.getString("pid", ""));
                params.put("bid", sh.getString("bid", ""));
                // Uncomment and pass bus_id if needed

                return params;

            }
        };




        int MY_SOCKET_TIMEOUT_MS = 100000;
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No need to implement
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No need to implement
        }

        @Override
        public void afterTextChanged(Editable editable) {
            makeVolleyRequest();
        }
    };



    private void makeVolleyRequest() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("url", "");
        String url = ip + "and_view_route_search";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("q");
                                pid = new String[js.length()];
                                bus_no = new String[js.length()];
                                bus_name = new String[js.length()];
                                start_time = new String[js.length()];
                                end_time = new String[js.length()];
                                bid = new String[js.length()];
                                er = new String[js.length()];
                                fr = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    pid[i] = u.getString("id");
                                    bus_no[i] = u.getString("bus_number");
                                    bus_name[i] = u.getString("bus_name");
                                    start_time[i] = u.getString("from_time");
                                    end_time[i] = u.getString("end_time");
                                    bid[i] = u.getString("bid");
                                    fr[i] = u.getString("end_route");
                                    er[i] = u.getString("from_route");
                                }

                                li.setAdapter(new custom_view_bus(getApplicationContext(), pid, bus_no, bus_name, start_time, end_time, bid, er, fr));
                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("from_route", e1.getText().toString());
                params.put("end_route", e2.getText().toString());
                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}

