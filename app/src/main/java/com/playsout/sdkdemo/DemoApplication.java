package com.playsout.sdkdemo;

import android.app.Application;

import java.util.HashMap;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

public class DemoApplication extends Application {
    private static final String CHANNEL = "com.playsout.minigames";
    @Override
    public void onCreate() {
        super.onCreate();

	// Instance FlutterEngine ，cache        
        FlutterEngine flutterEngine = new FlutterEngine(this);
        flutterEngine.getNavigationChannel().setInitialRoute("/home?channel=playsout&sdkkey=eyJ2ZXIiOiJ2MSIsImNoYW5uZWwiOiJwbGF5c291dCIsInBhY2thZ2VuYW1lIjoiIiwiZXhwIjoxNzYwOTY3ODIwfS5zaWc"); //update if sdkkey expire,look log

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());

        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("appAdId", "ca-app-pub-3940256099942544/1712485313"); // change them by your admob ad ID
        arguments.put("gameAdId", "ca-app-pub-3940256099942544/1712485313");
        channel.invokeMethod("init",arguments);

        FlutterEngineCache.getInstance().put(CacheId.PLAYSOUT_ENGINE_ID, flutterEngine);
    }
}
