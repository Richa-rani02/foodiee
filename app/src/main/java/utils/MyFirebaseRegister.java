package utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import config.BaseURL;

public class MyFirebaseRegister {
    Activity context;
    public SharedPreferences settings;

    public MyFirebaseRegister(Activity context) {
        this.context = context;
    }

    public void RegisterUser(String user_id) {
        String token= FirebaseInstanceId.getInstance().getToken();
        //FirebaseMessaging.getInstance().subscribeToTopic("grocery");
        // [END subscribe_topics]
        //  mLogTask = new fcmRegisterTask();
        //   mLogTask.execute(user_id,token);
        Log.d("token",">>"+token);
        Log.d("user_id",">>"+user_id);
        checkLogin(user_id, token);
    }

    private void checkLogin(final String user_id,final String token) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, BaseURL.REGISTER_FCM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("firebase", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("response").equals("true")) {

                    } else {
                        String error = obj.getString("message");
                        Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("token",token);
                params.put("device","android");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
