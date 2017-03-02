package io.rmiri.quicksettingstilesdemo;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.service.quicksettings.TileService.requestListeningState;

/**
 * Created by Rasoul Miri on 3/2/17.
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //when call requestListeningState, onStartListening in MyTileService.class is run
        ComponentName componentName = new ComponentName(getApplicationContext(), MyTileService.class);
        requestListeningState(this, componentName);
    }
}
