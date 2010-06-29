package head.eye.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class cameraview extends SurfaceView implements SurfaceHolder.Callback{
	private static final String TAG2 = "CameraDemo";
	private static int sendFlag = 0;
	public Camera camera;
	SurfaceHolder mHolder;
	
	public cameraview(Context context)
	{
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	public void surfaceCreated(SurfaceHolder holder) {
		//Preview Camera Images!!!
        camera=Camera.open();

        try {
                camera.setPreviewDisplay(mHolder);
        }
        catch (Throwable t) {
        }
    }

	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera = null;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		camera.startPreview();
		/*
		camera.setPreviewCallback(new PreviewCallback() {
			 
			  public void onPreviewFrame(byte[] data, Camera camera) {
				  
				  if(sendFlag == 0)
				  {
					  sendFlag=1;
					  Log.d(TAG2, "Bytelength: " + data.length);
					  Log.d(TAG2, "DatagramSocket socket");
					  DatagramSocket socket = null;
					  try {
							Log.d(TAG2, "socket = new DatagramSocket()");
							socket = new DatagramSocket(1235);
					  }
					  catch (SocketException e1) {
							// TODO Auto-generated catch block
							Log.d(TAG2, "Exception: " + e1.getMessage());
					  }
					  int tempX = data.length;
					  int bufLen = 0;
					  int offSet = 0;
					  DatagramPacket packet = null;
					  while(tempX > 0)
					  {
						  offSet = data.length - tempX;
						  if(tempX >= 1500)
						  {
							  tempX  = tempX - 1500;
							  bufLen = 1500;
						  }
						  else{
							  bufLen = tempX;
							  tempX = tempX - 1500;
						  }
						  Log.d(TAG2, "OffSet: " + offSet);
						  Log.d(TAG2, "bufLen " + bufLen);
						  try {
								packet = new DatagramPacket(data, offSet, bufLen, InetAddress.getByName("155.69.149.39"), 1235);
								Log.d(TAG2, "send(packet)");
								socket.send(packet);
							} catch (IOException e) {
								Log.d(TAG2, "IOException: " + e.getMessage());
							} 
					  }
					  	Log.d(TAG2, "close()");
						socket.close();
						Log.d(TAG2, "Picture Sent!");
				  }
			  }
			});*/
	}
}
