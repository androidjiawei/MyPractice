package com.mylhyl.demopicbulge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutMoneyDialog dialog = new CheckoutMoneyDialog();
                dialog.show(getSupportFragmentManager(), "loginDialog");
            }
        });
    }
}
