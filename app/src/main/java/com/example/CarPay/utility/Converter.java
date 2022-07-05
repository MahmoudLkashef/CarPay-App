package com.example.CarPay.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Converter {

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public static Bitmap getBitmapImage(byte[] image) {
        Bitmap bitmap=null;
        if(image!=null){
            bitmap= BitmapFactory.decodeByteArray(image, 0, image.length);
            return bitmap;
        }
        return bitmap;
    }
}
