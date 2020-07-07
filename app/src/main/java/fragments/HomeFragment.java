package fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import utils.AppController;
import utils.RecyclerTouchListener;
import welcome.in.foodiee.R;
import adapter.AddonAdapter;
import adapter.PackageAdapter;
import model.Addon_model;
import model.package_model;


public class HomeFragment extends Fragment {
    private static String TAG = HomeFragment.class.getSimpleName();
    private SliderLayout slider;
    RecyclerView package_recycler, addon_recycler;
    private MaterialButton view_pack, view_addon;
    private static final String slider_url = "http://192.168.137.1/FoodAppOwn/Api/slider/slider";
    private static final String package_url = "http://192.168.137.1/FoodAppOwn/Api/products/package";
    private static final String addon_url = "http://192.168.137.1/FoodAppOwn/Api/products/addon";
    private PackageAdapter packageAdapter;
    private AddonAdapter addonAdapter;
    public ArrayList<package_model> packages = new ArrayList<>();
    public ArrayList<Addon_model> Addon = new ArrayList<>();
    HashMap<String, String> url_maps;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        slider = view.findViewById(R.id.slider);
        view_pack = view.findViewById(R.id.view_package);
        view_addon = view.findViewById(R.id.view_addon);

        package_recycler = view.findViewById(R.id.package_recycler);
        addon_recycler = view.findViewById(R.id.addon_recycler);

        package_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        package_recycler.setLayoutManager(layoutManager);
        addon_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        addon_recycler.setLayoutManager(layoutManager1);
        packageAdapter = new PackageAdapter(packages);
        addonAdapter = new AddonAdapter(Addon);
        package_recycler.setAdapter(packageAdapter);
        addon_recycler.setAdapter(addonAdapter);
         package_recycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), package_recycler, new RecyclerTouchListener.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {

             }

             @Override
             public void onItemLongClick(View view, int position) {

             }
         }));

        view_pack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Package_list pack = new Package_list();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, pack, "Package")
                        .addToBackStack(null)
                        .commit();

            }
        });
        view_addon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addon_list addon = new Addon_list();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, addon, "Addon")
                        .addToBackStack(null)
                        .commit();
       }
        });

                getslider();
                retrievePackageData();
                retrieveAddonData();
                return view;


                }

    private void retrieveAddonData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, addon_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("addon", ">>" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optString("response").equals("true")) {
                        String dataarray=jsonObject.getString("data");

                       // JSONArray dataarray = jsonObject.getJSONArray("data");
                         Addon.clear();
                        List<Addon_model> data= Arrays.asList(new Gson().fromJson(dataarray,Addon_model[].class));
                        Addon.addAll(data);
                        addonAdapter.notifyDataSetChanged();
                        /*for (int i = 0; i < dataarray.length(); i++) {
                            Addon_model addon = new Addon_model();
                            JSONObject dataobj = dataarray.getJSONObject(i);
                            addon.setExtra_id(dataobj.getString("extra_id"));
                            addon.setExtra_desc(dataobj.getString("extra_desc"));
                            addon.setExtra_img(dataobj.getString("extra_img"));
                            addon.setExtra_price(dataobj.getString("extra_price"));
                            addon.setExtra_name(dataobj.getString("extra_name"));
                            Addon.add(addon);
                            addonAdapter.notifyDataSetChanged();
                        }*/
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

    private void retrievePackageData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, package_url, new Response.Listener<String>() {
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
                        packageAdapter.notifyDataSetChanged();
                        /*JSONArray dataarray = obj.getJSONArray("data");
                        for (int i = 0; i < dataarray.length(); i++) {
                            package_model pack = new package_model();
                            JSONObject dataobj = dataarray.getJSONObject(i);
                            pack.setPack_id(dataobj.getString("pack_id"));
                            pack.setPack_desc(dataobj.getString("pack_desc"));
                            pack.setPack_price(dataobj.getString("pack_price"));
                            pack.setPack_name(dataobj.getString("pack_name"));
                            pack.setPack_img(dataobj.getString("pack_img"));
                            packages.add(pack);
                            packageAdapter.notifyDataSetChanged();
                        }*/
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void getslider() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(slider_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("slider", ">>" + response);
                url_maps = new HashMap<String, String>();
                try {
                    ArrayList<HashMap<String, String>> listarray = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        //HashMap<String, String> url_maps = new HashMap<String, String>();
                        JSONObject obj = response.getJSONObject(i);
                        url_maps.put(obj.getString("slider_id"), obj.getString("slider_img"));
                      /*url_maps.put("slider_title",obj.getString("title")) ;
                      url_maps.put("slider_image",obj.getString("image")) ;*/

                     /* url_maps.put("slider_id",obj.getString("slider_id"));
                      url_maps.put("slider_img",obj.getString("slider_img"));*/
                        // listarray.add(url_maps);


                    }
                    for (String name : url_maps.keySet()) {

                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        // initialize a SliderLayout
                        textSliderView

                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.CenterCrop);


                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        //textSliderView.getBundle().putString("extra", name);
                        slider.addSlider(textSliderView);

                     /* CustomSlider textSliderView = new CustomSlider(getActivity());
                      textSliderView.description(name.get("")).image(name.get("slider_image")).setScaleType(BaseSliderView.ScaleType.Fit);
                      textSliderView.bundle(new Bundle());
                      textSliderView.getBundle().putString("extra", name.get("slider_title"));
                      *//*textSliderView.description(name.get("")).image(name.get("slider_img")).setScaleType(BaseSliderView.ScaleType.Fit);
                      textSliderView.bundle(new Bundle());
                      textSliderView.getBundle().putString("extra", name.get("slider_id"));*//*
                      //textSliderView.getBundle().putString("extra", name.get("sub_cat"));*/
                        // slider.addSlider(textSliderView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "network issue: please enable wifi/mobile data + " + error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
        // slider.setPresetTransformer(SliderLayout.Transformer.Stack);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        //slider.setCustomAnimation(new DescriptionAnimation());
        //slider.setDuration(3000);

        //slider.setPresetTransformer("Stack");
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
