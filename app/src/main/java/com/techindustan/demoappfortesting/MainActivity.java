package com.techindustan.demoappfortesting;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.shruti.currentlocation.CurrentLocationActivity;
import com.shruti.currentlocation.CurrentLocationView;
import com.techindustan.myfirstlibrary.ItemClick;
import com.techindustan.myfirstlibrary.MyView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CurrentLocationActivity implements ItemClick, CurrentLocationView.GetCurrentLocation {

    MyView myView;
    List<String> alList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);


        alList.add("ABC");
        alList.add("EFG");
        alList.add("JHK");
        alList.add("MNOO");


        myView.setArrayList((ArrayList<String>) alList);


    }

    @Override
    public void itemClick(int position, Object val) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
            stopLocation();
    }

    @Override
    public void getCurrentLocation(Location loc) {
        Toast.makeText(this, "location is :" + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        //stopLocation();

    }


}
