import Foundation
import Capacitor
import tru_sdk_ios

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(TruPluginIonicCapacitorPlugin)
public class TruPluginIonicCapacitorPlugin: CAPPlugin {
    private let implementation = TruPluginIonicCapacitor()

   
    @objc func check(_ call: CAPPluginCall) {
       guard let url = call.getString("url") as? String else {
           call.reject("Must provide a URL")
           return
       }

         guard let nurl = URL(string: url) else {
           call.reject("Unable to create a url.")
           return
       }

        let truSdk: TruSDK = TruSDK()
        truSdk.check(url: nurl) { error in
            if let error = error as NSError? {
               call.reject(error.localizedDescription, nil, error)
            } else {
                call.resolve([
            "result": url
        ])
            }
        }
       
    }

      
       @objc func checkWithTrace(_ call: CAPPluginCall) {
       guard let url = call.getString("url") as? String else {
           call.reject("Must provide a URL")
           return
       }

         guard let nurl = URL(string: url) else {
           call.reject("Unable to create a url.")
           return
       }

        let truSdk: TruSDK = TruSDK()
        truSdk.checkWithTrace(url: nurl) { error, traceInfo in
            if let error = error {
                call.reject("Error from checkWithTrace =>\(error)", nil, error)
                return
            }

            guard let trace = traceInfo else {
                call.reject("There is not error received, however TraceInfo is not available")
                return
            }

            call.resolve([
                result: trace.trace
            ])
        }
       
    }

    @objc func checkWithTrace(_ call: CAPPluginCall) {

      let truSdk: TruSDK = TruSDK()
      truSdk.isReachable{ result in
        switch(result) {
          case .success(let reachabilityDetails):
               do{
                    let jsonData = try JSONEncoder().encode(reachabilityDetails)
                    let jsonString = String(data: jsonData, encoding: .utf8)!
                    call.resolve([
                        "result": jsonString
                    ])
                } catch {
                    call.reject("Unable to decode reachability details to json")
                }
            case .failure(let error):
                call.reject(error.localizedDescription, nill, error)
            }
        }
    }
}
