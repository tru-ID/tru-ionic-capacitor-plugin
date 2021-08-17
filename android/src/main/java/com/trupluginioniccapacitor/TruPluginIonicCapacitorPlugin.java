package com.trupluginioniccapacitor;

import java.net.URL;
import java.util.logging.Logger;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import id.tru.sdk.TruSDK;
import id.tru.sdk.ReachabilityDetails;

import android.util.Log;

@CapacitorPlugin(name = "TruPluginIonicCapacitor")
public class TruPluginIonicCapacitorPlugin extends Plugin {

    private TruPluginIonicCapacitor implementation = new TruPluginIonicCapacitor();

   

    @PluginMethod
    public void check(PluginCall call){
        String url = call.getString("url");

        Thread thread = new Thread(()-> {
            try {
            val truSdk = TruSDK.getInstance();
            val isOnCellular = truSdk.openCheckUrl(url);
            call.resolve(url); 
          } catch (Exception exception) {
            call.reject(exception);
          }
        });
        // start thread 
        thread.start();
        try {
        // wait for thread to finish executing 
        thread.join();
        }catch (InterruptedException e){
        call.reject(e);
        }
    }

    @PluginMethod 
    public void checkWithTrace(PluginCall call){
        Thread thread = new Thread(()-> {
            try {
                val truSdk = TruSDK.getInstance();
                val traceInfo = truSdk.checkWithTrace(URL(url));
                call.resolve(traceInfo.trace);

            } catch(Exception exception){
                call.reject(exception);
            } 
        });

        thread.start();

        try {
        // wait for thread to finish executing 
        thread.join();
        }catch (InterruptedException e){
        call.reject(e);
        }
    }

    @PluginMethod
    public void isReachable(PluginCall call){
        try {
           
            val truSdk = TruSDK.getInstance();
            ReachabilityDetails reachabilityInfo = truSdk.isReachable();
            val payload = reachabilityInfo.toJsonString();
            call.resolve(payload);
          }catch (Exception exception) {
            call.reject(exception);
          }
    }
    
}
