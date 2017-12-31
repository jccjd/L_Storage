package com.example.lenovo.l_storage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class ExternalActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etInputData;
    private Button btnSaveData ,btnGetData;
    private TextView tvShowData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        etInputData = (EditText) findViewById(R.id.et_input_data);
        btnGetData = (Button) findViewById(R.id.btn_get_data);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        tvShowData = (TextView) findViewById(R.id.tv_show_data);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_data :
                saveData();
                break;
            case R.id.btn_get_data :
                getData();
                break;
        }

    }

    private void saveData() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            } else {
            doSaveData();
        }
    }

    private void doSaveData() {
        String strData = etInputData.getText().toString();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(),"data.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(strData.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void getData() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
            File file = new File(Environment.getExternalStorageDirectory(),"data.txt" );
            try {
                FileInputStream fis = new FileInputStream(file);
                StringBuffer sb = new StringBuffer();
                byte[] buffer = new byte[512];
                int hasread = -1;
                while ((hasread = fis.read(buffer))!= -1) {
                    sb.append(new String(buffer,0,hasread));

                }
                tvShowData.setText(sb.toString());
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSaveData();
                }
                break;
        }
    }
}
