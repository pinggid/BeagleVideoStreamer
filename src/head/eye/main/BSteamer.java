package head.eye.main;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class BSteamer extends Activity {
    /** Called when the activity is first created. */

	private cameraview cam;
	private Button buttonClick;
	private static boolean toogleButtonFlag = true;
	private MediaRecorder recorder = new MediaRecorder(); 

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
				if(toogleButtonFlag )
				{
					startRecording();
					toogleButtonFlag = false;
				}
				else{
					stopRecording();
					toogleButtonFlag = true;
				}
				
			}
		});
    }
    
    public void startRecording(){
    	recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
    	 recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    	 recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
    	 recorder.setOutputFile("/sdcard/.3pg");
    	 try{
    	 recorder.prepare();
    	 }
    	 catch(IOException e)
    	 {
    		 e.printStackTrace();
    		 recorder.reset();   
			 recorder.release();
    	 }
    	 recorder.start();   // Recording is now started
    }
    
    public void stopRecording(){
    	 recorder.stop();
	   	 recorder.reset();   // You can reuse the object by going back to setAudioSource() step
	   	 recorder.release(); // Now the object cannot be reused
    }
}