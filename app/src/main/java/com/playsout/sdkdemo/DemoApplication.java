package com.playsout.sdkdemo;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Instance FlutterEngine ，cache
        FlutterEngine flutterEngine = new FlutterEngine(this);
        flutterEngine.getNavigationChannel().setInitialRoute("/home?channel=playsout&sdkkey=123456");

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache.getInstance().put(CacheId.PLAYSOUT_ENGINE_ID, flutterEngine);
    }
}
