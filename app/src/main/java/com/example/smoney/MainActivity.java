package com.example.smoney;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private BottomNavigationView bottom_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addControls();
        Not();
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
        toolbar = getSupportActionBar();
        toolbar.setTitle("Lịch sử");
        bottom_view = findViewById(R.id.bottom_view);
        loadFragment(new HistoryFragment());
    }

    private void Not(){
        bottom_view = findViewById(R.id.bottom_view);
        loadFragment(new NotificationsFragment());
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
