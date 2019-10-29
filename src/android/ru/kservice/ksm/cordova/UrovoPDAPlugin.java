package ru.kservice.ksm.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class UrovoPDAPlugin extends CordovaPlugin {
    private static final String TAG = "UrovoPDAPlugin";
    //private static final Logger LOGGER = LoggerFactory.getLogger(Firmata.class);

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return false;
    }
}
