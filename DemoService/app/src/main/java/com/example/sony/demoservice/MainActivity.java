package com.example.sony.demoservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    public static final String bc1 = "bc1";
    public static final String bc2 = "bc2";
    private IntentFilter intentFilter;
    private Button btn;
    Intent serviceIntent;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv.setText(tv.getText()
                    + "\n");
            if (intent.getAction().equals(bc1)) {
                tv.setText(tv.getText()
                        + intent.getStringExtra("Data") + "\n\n");
            } else if (intent.getAction().equals(bc2)) {
                tv.setText(tv.getText().toString()
                        + intent.getIntExtra("Data", 0) + "\n\n");
                Intent stopIntent = new Intent(MainActivity.this,
                        DemoService.class);
                stopService(stopIntent);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textview);
        btn = (Button) findViewById(R.id.btn);
        intentFilter = new IntentFilter();
        intentFilter.addAction(bc1);
        intentFilter.addAction(bc2);
        serviceIntent = new Intent(this, DemoService.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startService(serviceIntent);

            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
}
