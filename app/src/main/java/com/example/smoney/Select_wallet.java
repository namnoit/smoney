package com.example.smoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Select_wallet extends AppCompatActivity {
    Button cash;
    Button atm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Chọn ví");
        setContentView(R.layout.activity_select_wallet);
        cash = findViewById(R.id.cash);
        atm = findViewById(R.id.atm);
        addNewWallet(cash);
        addNewWallet(atm);
    }

    void addNewWallet(Button c){
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_wallet.this, Select_category.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}
