package head.eye.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;


public class BSteamer extends Activity {
    /** Called when the activity is first created. */

	private cameraview cam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        cam = new cameraview(this);
        ((FrameLayout) findViewById(R.id.preview)).addView(cam);
        // Create A Preview View


    }
}