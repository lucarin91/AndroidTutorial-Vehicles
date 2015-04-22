package com.lucarin91.beginandroidtutorial;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lucarin91.beginandroidtutorial.Adapter.VehicleAdapter;
import com.lucarin91.beginandroidtutorial.Helper.HTTP;
import com.lucarin91.beginandroidtutorial.Helper.JsonConverter;
import com.lucarin91.beginandroidtutorial.Model.Vehicle;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        final ListView list = (ListView) findViewById(R.id.listView);

        HTTP.asyncDownload("/vehicle",new HTTP.CallBack() {
            @Override
            public void onPreExecution() {
              bar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPostExecution(String data) {
                try {
                    ArrayList<Vehicle> listVehicle = JsonConverter.getVehicle(data);

                    list.setAdapter(new VehicleAdapter(getApplicationContext(),listVehicle));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onError(IOException e) {
                Log.e("MAIN", e.getMessage());
                bar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
