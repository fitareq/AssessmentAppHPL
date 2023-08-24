package com.fitareq.assessmentapphpl.data.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private Dialog dialog;

    public void showLoadingScreen() {
        if (dialog == null) {
            dialog = new Dialog(this);
            dialog.setContentView(new ProgressBar(this));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
        }

        dialog.show();
    }

    public void hideLoadingScreen(){
        dialog.dismiss();
    }
}
