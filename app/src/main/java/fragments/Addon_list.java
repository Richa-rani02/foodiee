package fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.Addon_listAdapter;
import adapter.Package_listAdapter;
import config.BaseURL;
import model.Addon_model;
import utils.AppController;
import utils.MyDividerItemDecoration;
import welcome.in.foodiee.R;


public class Addon_list extends Fragment {
    ShimmerFrameLayout shimmer_container;
RecyclerView Addon_list;
Addon_listAdapter addon_listAdapter;
private ArrayList<Addon_model> Addon=new ArrayList<>();

    public Addon_list() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_addon_list, container, false);
        Addon_list=view.findViewById(R.id.addon_list);
        Addon_list.setHasFixedSize(true);
        Addon_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        shimmer_container=view.findViewById(R.id.shimmer_view_container);
        Addon_list.setItemAnimator(new DefaultItemAnimator());
        Addon_list.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        addon_listAdapter=new Addon_listAdapter(Addon);

        Addon_list.setAdapter(addon_listAdapter);
        getaddon();

        return view;
    }

    private void getaddon() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, BaseURL.addon_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Addon", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("response").equals("true")) {
                        String dataarray=obj.getString("data");
                        Addon.clear();
                        List<Addon_model> data= Arrays.asList(new Gson().fromJson(dataarray,Addon_model[].class));
                        Addon.addAll(data);
                        addon_listAdapter.notifyDataSetChanged();
                        //JSONArray dataarray = obj.getJSONArray("data");
                        /*for (int i = 0; i < dataarray.length(); i++) {
                            Addon_model addon = new Addon_model();
                            JSONObject dataobj = dataarray.getJSONObject(i);
                            addon.setExtra_id(dataobj.getString("extra_id"));
                            addon.setExtra_desc(dataobj.getString("extra_desc"));
                            addon.setExtra_name(dataobj.getString("extra_name"));
                            addon.setExtra_price(dataobj.getString("extra_price"));
                            addon.setExtra_img(dataobj.getString("extra_img"));
                            Addon.add(addon);
                            addon_listAdapter.notifyDataSetChanged();*/
                            shimmer_container.stopShimmer();
                            shimmer_container.setVisibility(View.GONE);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "error" + error);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public void onResume() {
        shimmer_container.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        shimmer_container.stopShimmer();
    }
}
