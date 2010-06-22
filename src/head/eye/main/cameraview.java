package head.eye.main;

import java.io.IOException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class cameraview extends SurfaceView implements SurfaceHolder.Callback{
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
		//Preview Camera Images
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
	}
}
