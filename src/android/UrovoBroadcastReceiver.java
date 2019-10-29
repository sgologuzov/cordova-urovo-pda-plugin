package ru.kservice.ksm.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;

public class UrovoBroadcastReceiver extends BroadcastReceiver {
    private Vibrator vibrator;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;
    private BarcodeListener listener;

    public UrovoBroadcastReceiver(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        isScaning = false;
        soundpool.play(soundid, 1, 1, 0, 0, 1);
        vibrator.vibrate(100);

        byte[] barcode = intent.getByteArrayExtra("barocode");
        int barocodelen = intent.getIntExtra("length", 0);
        byte temp = intent.getByteExtra("barcodeType", (byte) 0);
        android.util.Log.i("debug", "----codetype--" + temp);
        barcodeStr = new String(barcode, 0, barocodelen);
        System.out.println("barcode= " + barcodeStr);
        if (listener != null) {
            listener.onBarcodeScanned(barcodeStr);
        }
    }

    public void addEventListener(BarcodeListener listener) {
        this.listener = listener;
    }
}
