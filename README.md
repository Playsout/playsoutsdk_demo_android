# playsoutsdk_demo_android
Playsout SDK Integration Guide

 <!-- Add actual image path -->
Overview

Playsout Mini Game Container SDK is developed using Flutter + C++ at the underlying layer, combining rapid cross-platform capabilities with high performance. This document guides you through integrating Playsout Mini Game Container into your existing native Android/iOS projects.
Technical Requirements

If you wish to incorporate Playsout Mini-Game Container capabilities into your existing App, we recommend adopting a hybrid development approach by embedding the Flutter module into your native App project.
This significantly reduces workload and enables rapid implementation of Playsout Mini-Game Container functionality in both Android and iOS native Apps.
`https://github.com/Playsout/playsoutsdk_demo_android/blob/main/docs/images/hybrid.png?raw=true`

Environment Requirements
Environment Version Requirements

Android <ul><li>Android Studio 3.5+</li><li>Android 5.0+ devices (API 21+)</li></ul>
iOS <ul><li>Xcode 11.0+</li><li>Valid developer signature</li></ul>
Build System Gradle 4.2.1+

Native Platform Implementation

1) Flutter Hybrid Development Principle

mermaid
graph TD
    A[Native App] --> B[Flutter Module]
--> C[Playsout Game Container]

--> D[Method Channel]

--> D

Key components:
Flutter Module: Reusable game container

Native Host: 

Android: FlutterActivity

iOS: FlutterViewController

Communication: Method Channel for data exchange

2) Adding to Android Project

Playsout SDK provides Android Archive (AAR) for integration:
Add dependency in app/build.gradle:

gradle
dependencies {
    implementation 'com.playsout:sdk:latest_version'

Configure in AndroidManifest.xml:

xml
<activity
    android:name="io.flutter.embedding.android.FlutterActivity"
    android:theme="@style/AppTheme" />

https://docs.flutter.dev/add-to-app/android

3) Adding to iOS Project

Playsout SDK provides Xcode frameworks:
Add to Podfile:

ruby
pod 'PlaysoutSDK', '~> latest_version'

Initialize in AppDelegate:

swift
let flutterEngine = FlutterEngine(name: "playsout_engine")

https://docs.flutter.dev/add-to-app/ios
Integration Steps (Android Example)

1) Environment Preparation
✅ Android 5.0+ device

✅ Gradle 4.2.1+

✅ Android Studio 3.5+

2) Service Activation
Contact Playsout business team for control platform access

Obtain your unique credentials:

SDKAppID

SDKSecretKey

 <!-- Add actual image path -->

3) SDK Configuration
Download SDK:  

   https://github.com/Playsout/Playsout_sdk_android
Configure repositories in settings.gradle.kts:

kotlin
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://github.com/Playsout/Playsout_sdk_android/raw/main/releases")
}

Add dependency in app/build.gradle.kts:

kotlin
dependencies {
    implementation("com.playsout:sdk:latest_version")

Update AndroidManifest.xml (reference demo implementation)

4) SDK Initialization

Initialize in your Application class:
java
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        PlaysoutSdkConfig config = new PlaysoutSdkConfig.Builder()
            .appId("YOUR_SDK_APP_ID")    // From service activation
            .secretKey("YOUR_SECRET_KEY") 
            .build();
            
        PlaysoutSdk.initialize(this, config);
}

5) Launch Mini Game Container

Start the game container from any Activity:
java
private void launchFlutterActivity() {
    try {
        Intent intent = FlutterActivity
            .withCachedEngine(CacheId.PLAYSOUT_ENGINE_ID)
            .build(MainActivity.this);
        startActivity(intent);
catch (Exception e) {

        // Handle launch errors
        Log.e("Playsout", "Launch failed", e);
}

Support Resources
GitHub Repository: https://github.com/Playsout/Playsout_sdk_android

Issue Tracker: https://github.com/Playsout/Playsout_sdk_android/issues

