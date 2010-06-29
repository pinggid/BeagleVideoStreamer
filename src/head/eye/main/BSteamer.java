package head.eye.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
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
        
        cam = new cameraview(this);
        ((FrameLayout) findViewById(R.id.preview)).addView(cam);
         //Create A Preview View
        Log.d(TAG, "Create Camera Preview View 2");
        //vRecorder = new VideoRecorder("");
        
        buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
					cam.camera.takePicture(shutterCallback, rawCallback,
							jpegCallback);
				/*try{
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
				}*/
			}
		});
		Log.d(TAG, "onCreate'd");
    }
    ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "FileOutputStream outStream");
			FileOutputStream outStream = null;
			Log.d(TAG, "DatagramSocket socket");
			DatagramSocket socket = null;
			try {
				Log.d(TAG, "socket = new DatagramSocket()");
				socket = new DatagramSocket(1235);
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				Log.d(TAG, "Exception: " + e1.getMessage());
				e1.printStackTrace();
			}
			DatagramPacket packet = null;
			try {
				Log.d(TAG, "new DatagramPacket");
				byte[] databuffer = new byte[128];
				packet = new DatagramPacket(data, 49000, InetAddress.getByName("155.69.149.39"), 1235);
				Log.d(TAG, "Length: " + packet.getLength());
				Log.d(TAG, "socket.send(packet)");
				socket.send(packet);
				Log.d(TAG, "socket.close()");
				socket.close();
				// write to local sandbox file system
				// outStream =
				// CameraDemo.this.openFileOutput(String.format("%d.jpg",
				// System.currentTimeMillis()), 0);
				// Or write to sdcard
				Log.d(TAG, "new FileOutputStream");
				outStream = new FileOutputStream(String.format(
						"/sdcard/DCIM/%d.jpg", System.currentTimeMillis()));
				Log.d(TAG, "outStream.write");
				outStream.write(data);
				Log.d(TAG, "outStream.close()");
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			//} catch (FileNotFoundException e) {
			//	e.printStackTrace();
			} catch (IOException e) {
			//	e.printStackTrace();
				Log.d(TAG, "IOException: " + e.getMessage());
			} //finally {
			//}
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};
}