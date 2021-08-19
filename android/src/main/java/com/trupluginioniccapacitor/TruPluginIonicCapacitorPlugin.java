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

    @Override
    public void load(){
        TruSDK.initializeSdk(getActivity().getApplicationContext())
    }

   

    @PluginMethod
    public void check(PluginCall call){
        String url = call.getString("url");

        Thread thread = new Thread(()-> {
            try {
            val truSdk = TruSDK.getInstance();
            val isOnCellular = truSdk.openCheckUrl(url);

            JSObject ret = new JSObject();

            ret.put("result", url);

            call.resolve(ret); 

          } catch (Exception exception) {
            call.reject(exception.getLocalizedMessage(), null, exception);
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

                JSObject ret = new JSObject();

                ret.put("result", traceInfo.trace);

                call.resolve(ret);

            } catch(Exception exception){
                call.reject(exception.getLocalizedMessage(), null, exception);
            } 
        });

        thread.start();

        try {
        // wait for thread to finish executing 
        thread.join();
        }catch (InterruptedException e){
         call.reject(e.getLocalizedMessage(), null, exception);
        }
    }

    @PluginMethod
    public void isReachable(PluginCall call){
        try {
           
            val truSdk = TruSDK.getInstance();
            ReachabilityDetails reachabilityInfo = truSdk.isReachable();

            JSObject ret = new JSObject();
            
            val payload = reachabilityInfo.toJsonString();

            ret.put("result", payload);

            call.resolve(ret);
          }catch (Exception exception) {
            call.reject(exception.getLocalizedMessage(), null, exception);
          }
    }
    
}
