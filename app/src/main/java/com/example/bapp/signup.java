package com.example.bapp;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    private static final   String url = "http://192.168.0.100/api/pi/signup.php";
    EditText rfname ,rlname , remail , rpassword , rphone ;
    Spinner rgender ;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    rfname = findViewById(R.id.fname);
    rlname = findViewById(R.id.lname);
    remail = findViewById(R.id.semail);
    rpassword = findViewById(R.id.spassword);
    rphone = findViewById(R.id.sphone);
    rgender = findViewById(R.id.gender);
    btnRegister = findViewById(R.id.sbutton);

    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String fname = rfname.getText().toString();
            String lname = rlname.getText().toString();
            String email = remail.getText().toString();
            String password = rpassword.getText().toString();
            String phone = rphone.getText().toString();
            String getgender = rgender.getSelectedItem().toString();
            String reg = "[A-Za-z+_.]+@(.+)$";
           Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){

                signup_user(fname,lname,email,password,phone,getgender);

            }
        }
    });

    }
    public void signup_user(final String fname , final String lname , final String email,
                            final String password ,final String phone , final String gender){


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"DBERROR",Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String ,String>getParams()throws AuthFailureError{
                Map<String, String> map = new HashMap<String, String>();

                map.put("fname",fname);
                map.put("lname",lname);
                map.put("email",email);
                map.put("password",password);
                map.put("phone",phone);
                map.put("gender",gender);

                return map;
            }
        };
    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(request);

    }



}