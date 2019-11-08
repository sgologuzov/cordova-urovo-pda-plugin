package ru.kservice.ksm.cordova;

import android.content.Context;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class UrovoPDAPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "UrovoPDAPlugin";
    private static final String SCAN_ACTION = "urovo.rcv.message";

    private Context context;
    private ScanManager scanManager;
    private UrovoBroadcastReceiver scanReceiver;

    @Override
    protected void pluginInitialize() {
        context = this.cordova.getActivity().getApplicationContext();
        scanReceiver = new UrovoBroadcastReceiver(context);
        System.out.println("Hello World!");
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("onBarcodeScanned")) {
            this.onBarcodeScanned(callbackContext);
            return true;
        } else if (action.equals("doScan")) {
            this.doScan(callbackContext);
            return true;
        }
        return false;
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app
     */
    @Override
    public void onPause(boolean multitasking) {
        if (scanManager != null) {
            scanManager.stopDecode();
        }
        context.unregisterReceiver(scanReceiver);
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app
     */
    @Override
    public void onResume(boolean multitasking) {
        initScan();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SCAN_ACTION);
        context.registerReceiver(scanReceiver, filter);
    }

    /**
     * Called when the activity is becoming visible to the user.
     */
    @Override
    public void onStart() {
    }

    private void initScan() {
        scanManager = new ScanManager();
        scanManager.openScanner();
        scanManager.switchOutputMode(0);
    }

    private void doScan(final CallbackContext callbackContext) {
        try {
            scanManager.stopDecode();
            Thread.sleep(100);
            scanManager.startDecode();
            callbackContext.success();
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());
            callbackContext.error(e.getLocalizedMessage());
        }
    }

    private void onBarcodeScanned(final CallbackContext callbackContext) {
        scanReceiver.addEventListener(barcode -> {
            PluginResult result = new PluginResult(PluginResult.Status.OK, barcode);
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);
        });
    }
}
