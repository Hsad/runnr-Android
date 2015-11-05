package com.runnr.runnrandroid;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Larry Li on 11/5/2015.
 */
public class Run {
    private ArrayList<LatLng> points;
    public Run(){
        points = new ArrayList<LatLng>();
    }
    public void addPoint(LatLng point){
        points.add(point);
    }
    public ArrayList<LatLng> getPoints(){
        return points;
    }
    public double getTotalDistance(){
        double total = 0;
        //Get the sum of euclidean distances between each point
        for(int i = 0; i < points.size()-1; i++){
            double deltax = Math.abs(points.get(i).latitude - points.get(i+1).latitude);
            double deltay = Math.abs(points.get(i).longitude - points.get(i+1).longitude);
            total += Math.sqrt(Math.pow(deltax,2) + Math.pow(deltay,2));
        }
        return total;
    }
}
