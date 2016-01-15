package com.example.hao.materialdesigndemo.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.hao.materialdesigndemo.R;

/**
 * Created by 012 on 2016/1/14.
 */
public class SnackbarUtils {
    private static Snackbar snackbar;
    public static void show(View view,String msg,int i){
        if (i==0){
            snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        }else{
            snackbar=Snackbar.make(view,msg,Snackbar.LENGTH_LONG);
        }

        snackbar.show();
        snackbar.setAction(R.string.close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }
}
