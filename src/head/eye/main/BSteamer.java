package head.eye.main;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class BSteamer extends Activity {
    /** Called when the activity is first created. */

	private cameraview cam;
	Button buttonClick;
	public static boolean toogleButtonFlag = true;
	MediaRecorder recorder = new MediaRecorder(); 
	LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        
        cam = new cameraview(this);
        ((FrameLayout) findViewById(R.id.preview)).addView(cam);
        // Create A Preview View
                
        buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
					/*try{
					 //recorder.setCamera(cam.camera);
					 recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
					 recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					 recorder.setAudioEncoder(MediaRecorder.VideoEncoder.H264);
					 recorder.setOutputFile("/sdcard/");
					 recorder.prepare();
					 recorder.start();   // Recording is now started
					 recorder.stop();
					 recorder.reset();   // You can reuse the object by going back to setAudioSource() step
					 recorder.release(); // Now the object cannot be reused
				 
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}*/
			}
		});
    }
}