package com.example.a2grahj29.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MapSetLocation extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msl);

        Button regular = (Button) findViewById(R.id.submitbutton);
        regular.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        boolean button = false;

        //Retrieve the latitude
        EditText latitudeEditText = (EditText) findViewById(R.id.latitudeEditText);
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());
        bundle.putDouble("com.example.latitude",latitude);

        //Retrieve the longitude
        EditText longitudeEditText = (EditText) findViewById(R.id.longitudeEditText);
        double longitude = Double.parseDouble(longitudeEditText.getText().toString());
        bundle.putDouble("com.example.longitude",longitude);



        bundle.putBoolean("com.example.button", button);
        intent.putExtras(bundle);

        setResult(RESULT_OK,intent);
        finish();
    }
}