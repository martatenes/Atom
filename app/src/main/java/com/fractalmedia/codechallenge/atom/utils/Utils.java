package com.fractalmedia.codechallenge.atom.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.constants.Constants;

public class Utils {

    public static String getBaseUrlImages(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.CONFIGURATION_URL, null);
    }

    public static void ShowSimpleAlert(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.TR_ERROR_COMUNICAR_SERVIDOR).setTitle(R.string.TR_ERROR);
        builder.setPositiveButton(R.string.TR_ACEPTAR, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static  void ShowSimpleAlert(Context context, String title, String message, View.OnClickListener callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.TR_ERROR_COMUNICAR_SERVIDOR).setTitle(R.string.TR_ERROR);
        builder.setPositiveButton(R.string.TR_ACEPTAR, (dialog, which) -> {
            dialog.dismiss();
            callback.onClick(null);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
