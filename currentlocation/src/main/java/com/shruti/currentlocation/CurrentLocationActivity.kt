package com.shruti.currentlocation

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log

import android.widget.Toast


open class CurrentLocationActivity : AppCompatActivity() {


    var permissions = listOf<String>(Manifest.permission.ACCESS_FINE_LOCATION)
    val PERMISSION_REQUEST_CODE = 100

    var isNeverAskAgain = false

    lateinit var currentLocationView: CurrentLocationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentLocationView = CurrentLocationView(this)

        if (!hasPermission()) {
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), PERMISSION_REQUEST_CODE)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE ->
                for (i in permissions.indices) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED || grantResults[i] == PackageManager.PERMISSION_GRANTED) {


                    } else {

                        var isDontAsk = ActivityCompat.shouldShowRequestPermissionRationale(this,
                                permissions[0])
                        if (!isDontAsk) {
                            //goToPermissionSettings()
                            isNeverAskAgain = true
                        } else {

                            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)


                        }


                    }


                }
        }

    }

    fun hasPermission(): Boolean {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        if (hasPermission()) {
            if (isLocationEnabled())
                currentLocationView.startLocationUpdates()
            else
                goToLocationSetting()

        } else {
            if (isNeverAskAgain) {
                if (!hasPermission()) {
                    goToPermissionSettings()
                    // isLocationSetting = false
                }

            }
        }

    }

    override fun onStop() {
        super.onStop()
        currentLocationView.stopLocationUpdates()
    }


    public fun isLocationEnabled(): Boolean {

        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gps_enabled = false
        var network_enabled = false

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        } catch (ex: Exception) {
        }


        return network_enabled || gps_enabled

    }

    fun goToLocationSetting() {

        // notify user
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(resources.getString(R.string.gps_network_not_enabled))
        dialog.setPositiveButton(resources.getString(R.string.open_location_settings), DialogInterface.OnClickListener { paramDialogInterface, paramInt ->

            paramDialogInterface.cancel()
            paramDialogInterface.dismiss()

            // TODO Auto-generated method stub
            val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(myIntent)


            //get gps
        })
        dialog.setNegativeButton("No", DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
            //paramDialogInterface.cancel()

            finish()
        })
        dialog.show()


    }


    fun goToPermissionSettings() {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setMessage("Please allow the location permissions")
        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
            paramDialogInterface.cancel()
            paramDialogInterface.dismiss()
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)


        })
        dialog.setNegativeButton("No", DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
            finish()

        })
        dialog.show()
    }

    fun setLocationDuration(duration: Long) {

        currentLocationView.setDuration(duration)
    }

    fun stopLocation() {

        currentLocationView.stopLocationUpdates()
    }


}
