package com.teamandroid.snapshare.utils;

import android.content.Context;

import android.widget.Toast;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static String getDateFromUnixTime(Timestamp time) {
        Date date = time.toDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm a");
        return dateFormat.format(date);
    }
}
