package com.lucarin91.beginandroidtutorial.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucarin91.beginandroidtutorial.Model.Vehicle;
import com.lucarin91.beginandroidtutorial.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luca on 4/22/15.
 */
public class VehicleAdapter extends ArrayAdapter<Vehicle> {

        public VehicleAdapter(Context context, ArrayList<Vehicle> veicoliList) {
            super(context, R.layout.veicolo_list_row, veicoliList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.veicolo_list_row, parent, false);
            }

            TextView name = (TextView) convertView.findViewById(R.id.name);
            ImageView busy = (ImageView) convertView.findViewById(R.id.busy);

            Vehicle vehicle = getItem(position);

            if (vehicle.busy){
                busy.setImageResource(R.drawable.red);
            }else{
                busy.setImageResource(R.drawable.green);
            }
            name.setText(vehicle.name);

            return convertView;
        }
}
