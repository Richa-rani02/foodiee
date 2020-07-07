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
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.Package_listAdapter;
import config.BaseURL;
import model.package_model;
import utils.AppController;
import utils.MyDividerItemDecoration;
import welcome.in.foodiee.R;


public class Package_list extends Fragment {
  ShimmerFrameLayout shimmer_container;
  RecyclerView package_list;
  Package_listAdapter package_listAdapter;
  public ArrayList<package_model> packages=new ArrayList<>();
    public Package_list() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_package_list, container, false);

        shimmer_container=view.findViewById(R.id.shimmer_view_container);
        package_list= view.findViewById(R.id.package_list);
        package_list.setHasFixedSize(true);
        package_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        package_list.setItemAnimator(new DefaultItemAnimator());
        package_list.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        package_listAdapter=new Package_listAdapter(packages);

        package_list.setAdapter(package_listAdapter);

        getpackagedata();
        return view;
    }

    private void getpackagedata() {

        StringRequest stringRequest=new StringRequest(Request.Method.GET, BaseURL.package_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("package", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optString("response").equals("true")) {
                        String dataarray=obj.getString("data");

                        //JSONArray dataarray = obj.getJSONArray("data");
                        packages.clear();
                        List<package_model> data= Arrays.asList(new Gson().fromJson(dataarray,package_model[].class));
                        packages.addAll(data);
                        package_listAdapter.notifyDataSetChanged();
                       /* JSONArray dataarray = obj.getJSONArray("data");
                        for (int i = 0; i < dataarray.length(); i++) {
                            package_model pack = new package_model();
                            JSONObject dataobj = dataarray.getJSONObject(i);
                            pack.setPack_id(dataobj.getString("pack_id"));
                            pack.setPack_desc(dataobj.getString("pack_desc"));
                            pack.setPack_img(dataobj.getString("pack_img"));
                            pack.setPack_name(dataobj.getString("pack_name"));
                            pack.setPack_price(dataobj.getString("pack_price"));
                            packages.add(pack);
                            package_listAdapter.notifyDataSetChanged();*/
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
