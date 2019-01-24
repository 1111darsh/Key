package com.example.darsh.key;

import android.content.Context;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class StoreInFile extends AppCompatActivity {
    private static final String TAG ="File" ;
    private static final String MY_PREFS_NAME ="hi.txt" ;
    private static Environment Environmet ;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_in_file);
        Intent intent=getIntent();
        String Data="Data";
        Data = intent.getStringExtra("Data");
        Log.d(TAG, "onCreate: "+Data);

        textView=(TextView)findViewById(R.id.textView);
        textView.setText(Data);
        String filename="hello.txt";
        String data="hello";

        File file= new File(Environmet.getExternalStorageDirectory() + "/hi");
        Log.d(TAG, "onCreate: "+file);
        FileOutputStream fos;
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(),filename + " saved",
                    Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {e.printStackTrace();
            Log.d(TAG, "onCreate: "+e);}
        catch (IOException e) {e.printStackTrace();}




        /*File mFolder = new File(Environment.getExternalStorageDirectory(), "Folder_Name");

        Log.d(TAG, "onCreate: "+mFolder);
        if (!mFolder.exists()) {
            boolean b =  mFolder.mkdirs();

        }*/




        //Context context= getApplicationContext();
        /*File mydir = context.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
        File fileWithinMyDir = new File(mydir, "myfile"); //Getting a file within the dir
        Log.d(TAG, "onCreate: "+mydir);// .
        try {
            FileOutputStream out = new FileOutputStream(fileWithinMyDir);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "onCreate: "+e);
        }*/



        /*



        Context context=getApplicationContext();

        String fileName =  "hi.txt";//like 2016_01_12.txt


        try{
            FileOutputStream fileOut=openFileOutput("myText.txt",MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            outputWriter.write("fi".toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

*/
    }


}
