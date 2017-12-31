package com.example.lenovo.l_storage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText etInputData;
    private Button btnSave,btnGetData;
    private TextView tvShowData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);



        etInputData = (EditText) findViewById(R.id.et_input_data);
        btnSave = (Button) findViewById(R.id.btn_save_data);
        btnGetData = (Button) findViewById(R.id.btn_get_data);
        tvShowData = (TextView) findViewById(R.id.tv_show_data);

        btnGetData.setOnClickListener(this);
        btnSave.setOnClickListener(this);

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
        String strInputData = etInputData.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
            fos.write(strInputData.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getData() {
        try {
            FileInputStream fis = openFileInput("data.txt");
            StringBuffer sb = new StringBuffer();
            byte[] buffer = new byte[512];
            int hasRead = -1;
            while ((hasRead = fis.read(buffer))!= -1) {
                sb.append(new String (buffer,0,hasRead));

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
