package com.app.test.aidlsecondproject;

import android.content.Intent;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private EditText edt;
    String s1;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.start);
        edt = (EditText) findViewById(R.id.edt);
        intent = new Intent(MainActivity.this, ServerService.class);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("data", edt.getText().toString());
                startService(intent);
            }
        });
    }

}
