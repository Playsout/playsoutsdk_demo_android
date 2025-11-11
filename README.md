
# Playsout SDK Integration

<div align="center">
  <a href="https://github.com/Playsout/playsoutsdk_demo_android/raw/main/docs/videos/demo.mp4">
    <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/appui1.png" alt="demo video">
  </a>
  <p><strong>[click to view demo video]</strong></p>
</div>

## 1. Overview

Playsout Mini Game Container SDK is developed using Flutter + C++ at the underlying layer, combining rapid cross-platform capabilities with high performance. By reading this document, you will learn how to integrate the Playsout Mini Game Container into your existing native Android/iOS development projects.

## 2. Technical Requirements

If you wish to incorporate Playsout Mini-Game Container capabilities into your existing App, we recommend adopting a hybrid development approach by embedding the Flutter module into your native App project.

This significantly reduces workload and enables rapid implementation of Playsout Mini-Game Container functionality in both Android and iOS native Apps.
<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/hybrid.png?raw=true" alt="hybrid" />
</div>

### Environment Requirements

| Environment | Version |
|-------------|---------|
| Android | Android Studio 3.5 or higher. The app requires devices running Android 5.0 or later. |
| iOS | Xcode 11.0 or higher. Ensure your project has a valid developer signature configured. |

## 3. Usage on Native Platforms

### 1) Principle - Flutter Hybrid Development

The core principle is to package the Flutter module into an executable program for the native platform and embed it into the native project. Since the Flutter module is reusable, you only need to write it once and embed it into both Android and iOS apps.

When your existing app needs to display Playsout-related pages, load the corresponding Activity(Android) or ViewController(iOS) that hosts Flutter. For cross-platform communication(e.g., passing user information or other data), use the Method Channel.

### 2) Adding the Flutter Module to an Android Project

Playsout SDK provides an Android Archive(AAR). For specific integration steps, refer to:

https://docs.flutter.dev/add-to-app/android

### 3) Adding the Flutter Module to an iOS Project

Playout SDK provides Xcode frameworks. For specific integration steps, refer to:
https://docs.flutter.dev/add-to-app/ios

## 4. Integration

This section explains how to quickly integrate the Playsout Mini Game component into an Android project(iOS integration is similar). You will complete the following key steps and ultimately implement a mini-game feature with a complete UI.
<div align="center">

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/appui1.png?raw=true" alt="appui1" width="300" />
    </td>
    <td align="center">
      <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/game1.png?raw=true" alt="game1" width="300" />
    </td>
  </tr>
</table>

</div>

### 1) Environment Preparation

Android 5.0(SDK API Level 21) or later.

Gradle 4.2.1 or later.

Physical device running Android 5.0 or later.

### 2) Enabling Services

Before using the Playsout Mini Game Container service, you need to enable the service for your app(contact business partners to access the control platform). After enabling the service, note down the SDKAppID and SDKSecretKey, which will be used in subsequent steps.

<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/channel_key.png?raw=true" alt="channel_key" />
</div>

### 3) SDK Import and Configuration

Download playsoutsdk(https://github.com/Playsout/Playsout_sdk_android), add Maven repository configuration in settings.gradle.kts
<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/config1.png?raw=true" alt="config1" />
</div>


Add SDK library dependency in app/build.gradle.kts
<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/config2.png?raw=true" alt="config2" />
</div>

Add required configurations in AndroidManifest.xml referencing demo:
<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/config3.png?raw=true" alt="config3" />
</div>

### 4) Initialize the Playsout SDK

Add the following code to your project. Its purpose is to complete the Playsout SDK login by calling relevant interfaces. This step is critical only after successful login can you use Playsout's features.
```java
public void onCreate() {
        super.onCreate();
        // Instance FlutterEngine ，cache
        FlutterEngine flutterEngine = new FlutterEngine(this);
        flutterEngine.getNavigationChannel().setInitialRoute("/home?channel=playsout&sdkkey=123456");

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache.getInstance().put(CacheId.PLAYSOUT_ENGINE_ID, flutterEngine);
    }
```

### 5) Opening the Mini Game Library and Launching Your First Game

To launch the mini-game container in your target Activity, execute the following code:

```java
private void launchFlutterActivity() {
    try {
        Intent intent = FlutterActivity.withCachedEngine(CacheId.PLAYSOUT_ENGINE_ID).build(context MainActivity.this);
        startActivity(intent);
    } catch(Exception e) {
        handleLaunchError(e);
    }
}
```
<div align="center">

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/appui2.png?raw=true" alt="appui2" width="300" />
    </td>
    <td align="center">
      <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/game2.png?raw=true" alt="game2" width="300" />
    </td>
  </tr>
</table>

</div>

### 6) How to add your admob AD
1.Register with AdMob, add two rewarded ad uints.
2.Add required configurations in AndroidManifest.xml referencing demo:
<div align="center">
  <img src="https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/config3.png?raw=true" alt="config3" />
</div>
3.Modify the code to use your rewarded ad ID.
```java
	MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("appAdId", "ca-app-pub-3940256099942544/1712485313"); // change them by your admob ad ID
        arguments.put("gameAdId", "ca-app-pub-3940256099942544/1712485313");
        channel.invokeMethod("init",arguments);
```

### 7) How to add your in app purchase
In your Play Store , add the following consumable in-app purchase items to your app.
'com.playsout.minigames.gems12', US$0.99

'com.playsout.minigames.gems60', US$4.99

'com.playsout.minigames.gems180', US$14.99

'com.playsout.minigames.gems360', US$29.99

'com.playsout.minigames.gems600', US$49.99

'com.playsout.minigames.gems1200', US$99.99
