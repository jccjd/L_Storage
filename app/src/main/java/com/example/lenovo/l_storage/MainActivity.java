package com.example.lenovo.l_storage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btInternal,btnExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInternal = (Button) findViewById(R.id.btn_internal);
        btnExternal = (Button) findViewById(R.id.btn_external);

        btnExternal.setOnClickListener(this);
        btInternal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_internal :
                startActivity(new Intent(this,InternalActivity.class));
                break;
            case R.id.btn_external :
                startActivity(new Intent(this,ExternalActivity.class));
                break;
        }
    }
}
