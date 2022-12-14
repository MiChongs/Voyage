package com.manchuan.tools.activity.touch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.manchuan.tools.activity.touch.widget.MultiTouch;
import com.manchuan.tools.base.BaseAlertDialogBuilder;

public class MainActivity extends Activity {

	public float[] a() {
        Display defaultDisplay = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int i3;
        Display.Mode[] supportedModes = defaultDisplay.getSupportedModes();
        int length = supportedModes.length;
        int i4 = 0;
        int i5 = i2;
        int i6 = i;
        int i7 = 60;
        while (i4 < length) {
            Display.Mode mode = supportedModes[i4];
            if (mode.getPhysicalWidth() * mode.getPhysicalHeight() > i6 * i5) {
                i6 = mode.getPhysicalWidth();
                i5 = mode.getPhysicalHeight();
            }
            i4++;
            i7 = Math.max(i7, (int) mode.getRefreshRate());
        }
        int i8 = i7;
        i = i6;
        i2 = i5;
        i3 = i8;
        return new float[]{(float) i, (float) i2, (float) i3, displayMetrics.xdpi, displayMetrics.ydpi, (float) displayMetrics.widthPixels, (float) displayMetrics.heightPixels};
    }

	public static void a(Activity activity, boolean z, String str, int i) {
        try {
            if (activity.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
                activity.getWindow().requestFeature(11);
            }
        } catch (Exception ignored) {
        }
        if (z) {
            try {
                activity.getActionBar().setDisplayHomeAsUpEnabled(true);
            } catch (Exception ignored) {
            }
        }
        if (str != null) {
            activity.setTitle(str);
        }
        if (i != -1) {
            activity.setContentView(i);
        }
    }

	public static void a(Activity activity, String str) {
        new BaseAlertDialogBuilder(activity).setMessage(str).setPositiveButton("??????", null).setOnDismissListener(null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		a(this, true, "??????", -1);
        MultiTouch touch;
        a();
		// ??????????????????
		String b = "multi";// multi ???????????????sample ??????????????????pressure ???????????????coverage????????????
        touch = new MultiTouch(this);
        String i;
        touch.setShowInfo(b);
        i = "????????????????????????????????????????????????????????????????????????????????????";
        setContentView(touch);
        new BaseAlertDialogBuilder(this).setMessage(i).setPositiveButton("????????????", null).setOnCancelListener(null).show().setCanceledOnTouchOutside(false);
    }

	public void b() {
        finish();
    }

}
