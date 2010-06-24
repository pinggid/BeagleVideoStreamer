package head.eye.main;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class VideoRecorder {
      private static final String TAG1 = "CameraDemo";
	  final MediaRecorder recorder = new MediaRecorder();
	  final String path;

	  /**
	   * Creates a new audio recording at the given path (relative to root of SD card).
	   */
	  public VideoRecorder(String path) {
	    this.path = sanitizePath(path);
	  }

	  private String sanitizePath(String path) {
	    if (!path.startsWith("/")) {
	      path = "/" + path;
	    }
	    if (!path.contains(".")) {
	      path += "DCIM/test.3gp";
	    }
	    Log.d(TAG1, Environment.getExternalStorageDirectory().getAbsolutePath());
	    Log.d(TAG1, path);
	    return Environment.getExternalStorageDirectory().getAbsolutePath() + path;
	  }

	  /**
	   * Starts a new recording.
	   */
	  public void start() throws IOException {
		Log.d(TAG1, "Enter Camera Started");
	    String state = android.os.Environment.getExternalStorageState();
	    if(!state.equals(android.os.Environment.MEDIA_MOUNTED))  {
	    	Log.d(TAG1, "SD Card is not mounted.  It is " + state + ".");
	        throw new IOException("SD Card is not mounted.  It is " + state + ".");
	    }

	    // make sure the directory we plan to store the recording in exists
	    File directory = new File(path).getParentFile();
	    if (!directory.exists() && !directory.mkdirs()) {
	       Log.d(TAG1, "Path to file could not be created.");
	      throw new IOException("Path to file could not be created.");
	    }
	    Log.d(TAG1, "setVideoSource");
	    recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
	    Log.d(TAG1, "setOutputFormat");
	    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	    Log.d(TAG1, "setVideoEncoder");
	    recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
	    Log.d(TAG1, "setOutputFile: " + path);
	    recorder.setOutputFile(path);
	    Log.d(TAG1, "prepare");
	    try{
	    	recorder.prepare();
	    }
	    catch(IOException e){
	    	Log.d(TAG1, "recorderreset");  
		    recorder.reset();
		    Log.d(TAG1, "releasestop"); 
		    recorder.release();
	    	Log.d(TAG1, "Exception" + e.getMessage());
	    	Log.d(TAG1, "Exception" + e.getLocalizedMessage());
	    	Log.d(TAG1, "Exception" + e.toString());
	    	return;
	    }
	    Log.d(TAG1, "recorderstart");
	    recorder.start();
	    Log.d(TAG1, "afterrecorderstart");
	  }

	  /**
	   * Stops a recording that has been previously started.
	   */
	  public void stop() throws IOException {
		Log.d(TAG1, "recorderstop");  
	    recorder.stop();
	    Log.d(TAG1, "releasestop"); 
	    recorder.release();
	  }

	}