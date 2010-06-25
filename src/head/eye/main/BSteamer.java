package head.eye.main;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class BSteamer extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "CameraDemo";
	private cameraview cam;
	private Button buttonClick;
	private static boolean toogleButtonFlag = true;
	private VideoRecorder vRecorder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        
        //cam = new cameraview(this);
        //((FrameLayout) findViewById(R.id.preview)).addView(cam);
        // Create A Preview View
        Log.d(TAG, "Create Camera Preview View 2");
        vRecorder = new VideoRecorder("");
        
        buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try{
					if(toogleButtonFlag )
					{   Log.d(TAG, "Camera Started");
						vRecorder.start();
						toogleButtonFlag = false;
					}
					else{
						Log.d(TAG, "Camera Stoped");
						vRecorder.stop();
						toogleButtonFlag = true;
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
			}
		});
		Log.d(TAG, "onCreate'd");
    }
}