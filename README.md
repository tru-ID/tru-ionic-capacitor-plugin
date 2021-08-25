# tru.ID Plugin for Ionic & Capacitor.

## Installation

```bash
npm install @tru_id/tru-plugin-ionic-capacitor
ionic build
ionic cap sync
```

For Android, update the following in `android/build.gradle` to:

```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url "https://gitlab.com/api/v4/projects/22035475/packages/maven"
        }
    }
}
```

## Compatibility

- Minimum Android SDK: TruSDK requires a minimum API level of 21 (Android 5).
- Compile Android SDK: TruSDK requires you to compile against API 30 (Android 11 or later).
- Minimun deployment target for iOS is iOS 12.
- Minimum Swift version is 5.1.

## Usage

```tsx
import { TruPluginIonicCapacitor } from '@tru_id/tru-plugin-ionic-capacitor';

// Make a GET request using the cellular connection to the tru.ID check URL
const checkDetails = await TruPluginIonicCapacitor.check(check_url);

// Test if the device mobile network is currently supported
const reachabilityDetails = await TruPluginIonicCapacitor.isReachable();

console.log('Check results', checkDetails.result);

console.log('Reachability details', reachabilityDetails.result);
```
