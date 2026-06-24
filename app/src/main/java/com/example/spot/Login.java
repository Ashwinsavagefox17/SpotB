package com.example.spot;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText u_id,p_id;
    Button btn1;
    TextView signup;
    public static String lid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u_id = findViewById(R.id.editTextTextPersonName2);
        p_id = findViewById(R.id.editTextTextPassword);
        signup = findViewById(R.id.textView);
        btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn1){
            String username = u_id.getText().toString();
            String password = p_id.getText().toString();

            if (username.length()<1){
                u_id.setError("");
            }
            else if (password.length()<1){
                p_id.setError("");
            }
            else{
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String url= sh.getString("url","")+"login_and";
//                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                try {


                                    JSONObject obj = new JSONObject(new String(response.data));

                                    if (obj.getString("status").equals("ok")) {
//                                        Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


                                        String usertype = obj.getString("usertype");
//                                        Toast.makeText(getApplicationContext(), usertype, Toast.LENGTH_SHORT).show();
                                        lid = obj.getString("lid");
                                        if ("driver".equals((usertype))) {
                                            Toast.makeText(getApplicationContext(), "driver login successfully", Toast.LENGTH_SHORT).show();
                                            SharedPreferences.Editor ed = sh.edit();
                                            ed.putString("lid", obj.getString("lid"));
                                            ed.putString("did", obj.getString("did"));
                                            ed.putString("bid", obj.getString("bid"));
                                            ed.commit();

//                                            Intent s = new Intent(getApplicationContext(), LocationService.class);
//                                            startService(s);
                                            Intent in=new Intent(getApplicationContext(),gpstracker.class);
                                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startService(in);


                                            Intent i = new Intent(getApplicationContext(), Driver_home.class);
                                            startActivity(i);

                                        } else if ("passenger".equalsIgnoreCase((usertype))) {
//                                            Toast.makeText(getApplicationContext(), "Helooooooooo", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), "Passenger login successfully", Toast.LENGTH_SHORT).show();
                                            SharedPreferences.Editor ed = sh.edit();
                                            ed.putString("lid", obj.getString("lid"));
                                            ed.putString("pid", obj.getString("pid"));
                                            ed.commit();


                                            Intent i = new Intent(getApplicationContext(), Passenger_home.class);
                                            startActivity(i);
                                            //      Intent ii=new Intent(getApplicationContext(),LocationService.class);startService(ii);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Handle the case where status is not "ok"
                                        Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        params.put("username", username);//passing to python
                        params.put("password", password);//passing to python

                        return params;
                    }


                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        long imagename = System.currentTimeMillis();
//                        params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                        return params;
                    }
                };

                Volley.newRequestQueue(this).add(volleyMultipartRequest);

            }
        }else{
            Intent i = new Intent(getApplicationContext(),Signup.class);
            startActivity(i);
        }

    }
}