package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import welcome.in.foodiee.R;
import fragments.CartFragment;
import fragments.FavouriteFragment;
import fragments.HomeFragment;
import fragments.ProfileFragment;
import helper.BottomNavigationBehavior;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActionBar toolbar;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = getSupportActionBar();
        getSupportActionBar().setTitle("Foodiee");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationitemselectedlistener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        loadFragment(new HomeFragment());


    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.cart:
                fragment = new CartFragment();
                loadFragment(fragment);
                navigation.setVisibility(View.INVISIBLE);
                return true;

            case R.id.favourite:
                fragment = new FavouriteFragment();
                loadFragment(fragment);
                navigation.setVisibility(View.INVISIBLE);
                return true;

        }
        return false;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationitemselectedlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    navigation.setVisibility(View.VISIBLE);
                    return true;
               /* case R.id.cart:
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    //navigation.setVisibility(View.INVISIBLE);
                    return true;*/
                case R.id.profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    navigation.setVisibility(View.INVISIBLE);
                    return true;
                /*case R.id.favourite:
                    //navigation.setVisibility(View.INVISIBLE);
                    fragment = new FavouriteFragment();
                    loadFragment(fragment);

                    return true;*/

            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
       navigation.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        navigation.setVisibility(View.VISIBLE);
    }
}
