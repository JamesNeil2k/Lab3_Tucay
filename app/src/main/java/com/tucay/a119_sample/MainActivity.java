package com.tucay.a119_sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etmessage, etfileName;
    Button btnSharedPref, btnInternalStorage, btnInternalCache, btnExternalCache, btnExternalStorage, btnExternalPublicStorage, btnNext;
    FileOutputStream fos;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etfileName = (EditText) findViewById(R.id.et_filename);
        etmessage = (EditText) findViewById(R.id.et_message);
        btnSharedPref = (Button) findViewById(R.id.btn_sharedPref);
        btnInternalStorage = (Button) findViewById(R.id.btn_internalStorage);
        btnInternalCache = (Button) findViewById(R.id.btn_internalCache);
        btnExternalCache = (Button) findViewById(R.id.btn_externalCache);
        btnExternalStorage = (Button) findViewById(R.id.btn_externalStorage);
        btnExternalPublicStorage = (Button) findViewById(R.id.btn_externalPublicStorage);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = getSharedPreferences("Preferences",MODE_WORLD_READABLE);
    }

    public void saveSharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(etfileName.getText().toString(),etmessage.getText().toString());
        editor.commit();

        Toast.makeText(this,"Saved in Shared Preferences",Toast.LENGTH_LONG).show();
    }

    public void saveInternalStorage(View view){
        String message = etmessage.getText().toString();
        try{
            fos = openFileOutput(etfileName.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this,"Saved in Internal Storage",Toast.LENGTH_LONG).show();
    }

    public void saveInternalCache(View view) {
        File folder = getCacheDir();
        File file = new File(folder,etfileName.getText().toString());
        String message = etmessage.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Cache", Toast.LENGTH_LONG).show();
    }



    public void saveExternalCache(View view) {
        File folder = getExternalCacheDir();
        File file = new File(folder,etfileName.getText().toString());
        String message = etmessage.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in External Cache", Toast.LENGTH_LONG).show();
    }

    public void saveExternalStorage(View view) {
        File folder = getExternalFilesDir("temp");
        File file = new File(folder,etfileName.getText().toString());
        String message = etmessage.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in External Storage", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublicStorage(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,etfileName.getText().toString());

        String message = etmessage.getText().toString();

        writeData(file, message);

        Toast.makeText(this, "Saved in External Public Storage", Toast.LENGTH_LONG).show();
    }

    public void writeData(File file, String message) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void next(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
