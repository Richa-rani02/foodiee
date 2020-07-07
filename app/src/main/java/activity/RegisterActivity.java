package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import config.BaseURL;
import utils.AppController;
import utils.Session_management;
import welcome.in.foodiee.R;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Session_management session_management;
    Button signup;
    String mobile_no,name,emailid,password;
    EditText username, email, phone, pass;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        signup = (Button) findViewById(R.id.signup);
        username = findViewById(R.id.username);
        email =  findViewById(R.id.email);
        phone =  findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        session_management = new Session_management(RegisterActivity.this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.phone, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
       // awesomeValidation.addValidation(this, R.id.pass, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passerror);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    mobile_no = phone.getText().toString().trim();
                    name = username.getText().toString().trim();
                    emailid = email.getText().toString().trim();
                    password = pass.getText().toString().trim();
                    // Toast.makeText(RegisterActivity.this, "" + mobile_no + name + emailid + password, Toast.LENGTH_SHORT).show();
                    if (!mobile_no.isEmpty() || !name.isEmpty() || !emailid.isEmpty() || !password.isEmpty()) {
                        submituserdetail(mobile_no, name, emailid, password);


                    }


               /* Intent i = new Intent(RegisterActivity.this, LoginActivity.class);

              startActivity(i);*/
                }
            }
        });
    }

    private void submituserdetail(final String mobile_no, final String name, final String emailid, final String password) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, BaseURL.SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("signup", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("response").equals("true")) {
                        Toast.makeText(RegisterActivity.this, ""+obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                      //  String error = obj.getString("message");
                        Toast.makeText(RegisterActivity.this, "Field must contain a unique value", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag",""+error);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_name",name);
                params.put("user_mobile",mobile_no);
                params.put("user_email",emailid);
                params.put("user_password",password);


                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
