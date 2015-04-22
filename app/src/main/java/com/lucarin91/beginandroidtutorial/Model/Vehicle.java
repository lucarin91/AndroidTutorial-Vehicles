package com.lucarin91.beginandroidtutorial.Model;

/**
 * Created by luca on 4/22/15.
 */
public class Vehicle {
    public String id;
    public String name;
    public Boolean busy;

    public Vehicle(String id, String name, Boolean busy){
        this.id = id;
        this.name = name;
        this.busy = busy;
    }
}
