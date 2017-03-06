package com.example.a2grahj29.mapping;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Double2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;

public class HelloMap extends Activity
{
    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(40.1,22.5));


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.choosemap) {
            // react to the menu item being selected...
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);
            return true;
        } else if (item.getItemId() == R.id.setlocationmenu) {
            Intent intent = new Intent(this, MapSetLocation.class);
            startActivityForResult(intent, 1);
            return true;
        } else if (item.getItemId() == R.id.prefactivity) {
            Intent intent = new Intent(this, MyPrefsActivity.class);
            startActivityForResult(intent, 2);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if (cyclemap) {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                } else {
                    mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Bundle extras1=intent.getExtras();
                double latitude = extras1.getDouble("com.example.latitude");
                double longitude = extras1.getDouble("com.example.longitude");

                mv.getController().setCenter(new GeoPoint(latitude, longitude));
            }
        }
    }
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble (prefs.getString("lat", "50.9"));
        double lon = Double.parseDouble (prefs.getString("lon", "-1.4"));
        int zoom = Integer.parseInt(prefs.getString("zoom", "14"));

        mv.getController().setZoom(zoom);
        mv.getController().setCenter(new GeoPoint(lat, lon));

    }
}

