package com.example.smoney;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
=======
import android.widget.Toast;

import java.util.ArrayList;
>>>>>>> feature_model

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private BottomNavigationView bottom_view;
<<<<<<< HEAD

=======
    private Model model;
>>>>>>> feature_model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        bottom_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_history:
                    toolbar.setTitle("Lịch sử");
                    loadFragment(new HistoryFragment());
                    return true;
                case R.id.nav_notification:
                    toolbar.setTitle("Thông báo");
                    loadFragment(new NotificationsFragment());
                    return true;
                case R.id.nav_chart:
                    toolbar.setTitle("Biểu đồ");
                    loadFragment(new ChartFragment());
                    return true;
                case R.id.nav_tax:
                    toolbar.setTitle("Tính thuế TNCN");
                    loadFragment(new TaxFragment());
                    return true;
            }
            return false;
        }
    };

    private void addControls() {
<<<<<<< HEAD
=======
        model = new Model(this);
>>>>>>> feature_model
        toolbar = getSupportActionBar();
        toolbar.setTitle("Lịch sử");
        bottom_view = findViewById(R.id.bottom_view);
        loadFragment(new HistoryFragment());
<<<<<<< HEAD
=======

>>>>>>> feature_model
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
