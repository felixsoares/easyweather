package com.felix.easywheater.code.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.felix.easywheater.R;

public class ProgressHUD extends Dialog {

    public ProgressHUD(Context context) {
        super(context);
    }

    public ProgressHUD(Context context, int theme) {
        super(context, theme);
    }

    public ProgressHUD(Activity context, int theme) {
        super(context, theme);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public static ProgressHUD show(Activity context, CharSequence message) {
        try {
            ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
            dialog.setTitle("");
            dialog.setContentView(R.layout.progress_hud);
            if (message == null || message.length() == 0) {
                dialog.findViewById(R.id.message).setVisibility(View.GONE);
            } else {
                TextView txt = (TextView) dialog.findViewById(R.id.message);
                txt.setText(message);
            }
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.dimAmount = 0.2f;
            dialog.getWindow().setAttributes(lp);
            //dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            if (context != null)
                dialog.show();
            return dialog;
        } catch (Exception e) {
            Log.d("Dialog", e.getMessage());
        }
        return null;
    }
}
