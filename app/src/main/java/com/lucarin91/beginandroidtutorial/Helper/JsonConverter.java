package com.lucarin91.beginandroidtutorial.Helper;

import com.lucarin91.beginandroidtutorial.Model.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by luca on 4/22/15.
 */

public class JsonConverter {

    public static ArrayList<Vehicle> getVehicle (String vehicle) throws JSONException {
        ArrayList<Vehicle> veicoli = new ArrayList<Vehicle>();
        JSONArray jsonVehicle = new JSONArray(vehicle);
        for (int i = 0; i < jsonVehicle.length(); i++) {
            JSONObject obj = jsonVehicle.getJSONObject(i);
            veicoli.add(new Vehicle(
                    obj.getString("id"),
                    obj.getString("name"),
                    obj.getBoolean("busy")
                ));
        }
        return veicoli;
    }
}
