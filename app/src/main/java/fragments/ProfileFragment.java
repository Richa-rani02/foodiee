package fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import config.BaseURL;
import utils.Session_management;
import welcome.in.foodiee.R;


public class ProfileFragment extends Fragment implements View.OnClickListener {
    TextView name, mobile, whatsapp, logout;
    private Session_management session_management;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        session_management = new Session_management(getActivity());
        name = view.findViewById(R.id.name);
        mobile = view.findViewById(R.id.mobile);
        whatsapp = view.findViewById(R.id.whatsapp);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        name.setText(session_management.getUsersDetail().get(BaseURL.KEY_USER));
        mobile.setText(session_management.getUsersDetail().get(BaseURL.KEY_MOBILE));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.logout) {
            session_management.logoutSession();


        } else if (id == R.id.whatsapp) {
            String smsNumber = "9457494495";
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
            startActivity(sendIntent);

        }
    }


}
