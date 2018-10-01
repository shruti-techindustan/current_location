<b>Get Current Location</b>
<p><ul>
<li>Eliminate all the repeate process of requesting the runtime permission for getting the current location</li>
<li>Just extend the CurrentLocation Activity and implement the CurrentLocationView.GetCurrentLocation. The location get updated in getCurrentLocation(Location loc) method.</li>
</p>





<b>Example:</b><br/>
<code><br/>
package com.techindustan.example;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import com.shruti.currentlocation.CurrentLocationActivity;
import com.shruti.currentlocation.CurrentLocationView;




public class MainActivity extends CurrentLocationActivity implements  CurrentLocationView.GetCurrentLocation {

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set location update interval time in millis
        setLocationDuration(5000);
        
    }

    @Override
    public void getCurrentLocation(Location loc) {
        Toast.makeText(this, "location is :" + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        //stopLocation();

    }


}
</code>
