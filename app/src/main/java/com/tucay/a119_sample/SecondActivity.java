package com.tucay.a119_sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.R.attr.data;
import static android.R.id.message;

public class SecondActivity extends AppCompatActivity {

    TextView tvshowmessage;
    EditText etshowfilename;
    Button btnshowSP, btnshowIS, btnshowIC, btnshowEC, btnshowES, btnshowEPS, btBack;
    FileInputStream fis;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etshowfilename = (EditText) findViewById(R.id.et_filename);
        tvshowmessage = (TextView) findViewById(R.id.tv_message);
        btnshowSP = (Button) findViewById(R.id.btn_showSP);
        btnshowIS = (Button) findViewById(R.id.btn_showIS);
        btnshowIC = (Button) findViewById(R.id.btn_showIC);
        btnshowEC = (Button) findViewById(R.id.btn_showEC);
        btnshowES = (Button) findViewById(R.id.btn_showES);
        btnshowEPS = (Button) findViewById(R.id.btn_showEPS);
        btBack = (Button) findViewById(R.id.btn_previous);

        preferences = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
    }

    public void showSP(View view){
        String message = preferences.getString(etshowfilename.getText().toString(),"");
        tvshowmessage.setText(message);
    }

    public void showIS(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = openFileInput(etshowfilename.getText().toString());
            while((read = fis.read()) != -1) {
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvshowmessage.setText(buffer.toString());
    }

    public void showIC(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis =  new FileInputStream(new File(getCacheDir(),etshowfilename.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        tvshowmessage.setText(buffer.toString());
    }

    public void showEC(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis =  new FileInputStream(new File(getExternalCacheDir(),etshowfilename.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        tvshowmessage.setText(buffer.toString());
    }

    public void showES(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis =  new FileInputStream(new File(getExternalFilesDir("temp"),etshowfilename.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvshowmessage.setText(buffer.toString());
    }

    public void showEPS(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis =  new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),etshowfilename.getText().toString()));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tvshowmessage.setText(buffer.toString());

    }


    public void previous(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
