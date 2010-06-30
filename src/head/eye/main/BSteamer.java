package head.eye.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
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
	public DatagramSocket soc = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        Log.d(TAG, "Create Camera Preview View 2");
        cam = new cameraview(this);
        ((FrameLayout) findViewById(R.id.preview)).addView(cam);
         //Create A Preview View
        //Log.d(TAG, "Create Camera Preview View 2");
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
		try {
			Log.d(TAG, "DatagramSocket soc");
			soc = new DatagramSocket();
			soc.setBroadcast(true);
			Log.d(TAG, "byte buffer[]");
			byte buffer[] = new byte[65508];
			Log.d(TAG, "DatagramPacket pac ");
	        DatagramPacket pac = new DatagramPacket(buffer, buffer.length);
	        Log.d(TAG, "soc.receive(pac): " + pac.getLength());
	        soc.receive(pac);
	        Log.d(TAG, "soc.close() " + pac.getLength());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, "SocketException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, "IOException: " + e.getMessage());
		}
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
				socket = new DatagramSocket();
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				Log.d(TAG, "Exception: " + e1.getMessage());
				e1.printStackTrace();
			}
			DatagramPacket packet = null;
			try {
				//Log.d(TAG, "socket.send(packet)");
				//socket.send(packet);
				//Log.d(TAG, "socket.close()");
				//socket.close();
				//Log.d(TAG, "new FileOutputStream");
				long fileName = System.currentTimeMillis();
				outStream = new FileOutputStream(String.format("/sdcard/DCIM/%d.jpg", fileName));
				Log.d(TAG, "Compress");
				Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
				bmp.compress(CompressFormat.JPEG, 0, outStream);
				Log.d(TAG, "FileInputStream");
				FileInputStream input = new FileInputStream("/sdcard/DCIM/" + fileName + ".jpg");
				byte[] buffer = new byte[input.available()];
				Log.d(TAG, "Compressed Byte: " + input.available());
				input.read(buffer);
				Log.d(TAG, "new DatagramPacket");
				packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("155.69.148.140"), 1235);
				Log.d(TAG, "Length: " + packet.getLength());								
				Log.d(TAG, "outStream.write");
				//outStream.write(data);
				//Log.d(TAG, "outStream.close()");
				socket.send(packet);
				outStream.close();
				input.close();
				socket.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + buffer.length);
			} catch (FileNotFoundException e) {
				Log.d(TAG, "IOException: " + e.getMessage());
			} 
				catch (IOException e) {
				Log.d(TAG, "IOException: " + e.getMessage());
			}
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};
}