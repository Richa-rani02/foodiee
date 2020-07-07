package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import utils.MyFirebaseRegister;
import utils.Session_management;
import welcome.in.foodiee.R;

public class LoginActivity extends AppCompatActivity {
    Button login, signup, changpass;
    EditText username, pass;
    private Session_management session_management;
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        changpass = (Button) findViewById(R.id.chngpass);
        session_management = new Session_management(LoginActivity.this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.username, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        if (session_management.isLoggedIn()) {

            Intent intent = new Intent(this, MainActivity.class);


            startActivity(intent);
            finish();

        }
        changpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(LoginActivity.this, Changepass.class);
                startActivity(i2);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {

                    String mobile = username.getText().toString().trim();
                    String password = pass.getText().toString().trim();
                    if (!mobile.isEmpty() || !password.isEmpty()) {
                        //Toast.makeText(LoginActivity.this, "LOgin succsful" , Toast.LENGTH_SHORT).show();
                        AttemptLogin(mobile, password);
                    }


                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i1);
            }
        });

    }

    private void AttemptLogin(final String mobile, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseURL.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("login", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("response").equals("true")) {
                        JSONObject data = obj.getJSONObject("data");
                        String user_id = data.getString("user_id");
                        String user_name = data.getString("user_name");
                        String user_email = data.getString("user_email");
                        String user_mobile = data.getString("user_mobile");
                        // String user_image = data.getString("user_image");
                        Toast.makeText(LoginActivity.this, "Login Succesful..", Toast.LENGTH_SHORT).show();
                        session_management = new Session_management(LoginActivity.this);
                        session_management.createLoginSession(user_id, user_email, user_name, user_mobile);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                       MyFirebaseRegister myFirebaseRegister=new MyFirebaseRegister(LoginActivity.this);
                        myFirebaseRegister.RegisterUser(user_id);

                    } else {
                        String error = obj.getString("message");
                        Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_mobile", mobile);
                params.put("user_password", password);


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
