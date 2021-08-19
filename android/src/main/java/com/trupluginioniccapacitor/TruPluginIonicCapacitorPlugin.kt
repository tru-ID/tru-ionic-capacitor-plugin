package com.trupluginioniccapacitor

import android.util.Log
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import id.tru.sdk.ReachabilityDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import id.tru.sdk.TruSDK
import java.net.URL

@CapacitorPlugin(name = "TruPluginIonicCapacitor")
class TruPluginIonicCapacitorPlugin : Plugin() {
    private val implementation = TruPluginIonicCapacitor()
    private val TAG = TruPluginIonicCapacitorPlugin::class.qualifiedName
 public override fun load() {
        TruSDK.initializeSdk(context)
    }
    @PluginMethod
     fun check(call: PluginCall){
        val url = call.getString("url");


        CoroutineScope(context = Dispatchers.IO).launch {
            try {
                val truSdk = TruSDK.getInstance()
                Log.d(TAG,"check is called")
                val isOnCellular = url?.let { truSdk.check(it) }
                val ret =  JSObject();
                ret.put("result", url);
                call.resolve(ret);
                Log.d(TAG,"check is resolved")
            } catch (exception: Exception) {
                call.reject(exception.localizedMessage, null, exception)

                Log.d(TAG,"check errors out")
            }
        }

    }
    @PluginMethod
    fun checkWithTrace(call: PluginCall){
        val url = call.getString("url");

        CoroutineScope(context = Dispatchers.IO).launch {
            try {
                Log.d(TAG,"checkWithTrace is called")
                val truSdk = TruSDK.getInstance()
                val traceInfo = truSdk.checkWithTrace(URL(url))
                val ret =  JSObject();
                ret.put("result", traceInfo.trace);
                call.resolve(ret);
                Log.d(TAG,"checkWithTrace Promise resolved")
            } catch (exception: java.lang.Exception) {
                Log.d(TAG,"checkWithTrace Promise rejection")
                call.reject(exception.localizedMessage, null, exception);
            }
        }
        Log.d(TAG,"checkWithTrace method exit")
    }
    @PluginMethod
    fun isReachable(call: PluginCall){
        try {
            Log.d(TAG,"isReachable is called")
            val truSdk = TruSDK.getInstance()
            val reachabilityInfo: ReachabilityDetails? = truSdk.isReachable()
            val payload = reachabilityInfo?.toJsonString()
            val ret = JSObject()
            ret.put("result", payload)
            call.resolve(ret)
        }catch (exception: java.lang.Exception) {
            call.reject(exception.localizedMessage, null, exception);
        }
    }

}