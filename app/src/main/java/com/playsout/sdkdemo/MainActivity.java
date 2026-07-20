package com.playsout.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL = "com.playsout.minigames";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        setContentView(R.layout.activity_main);
        setupWindowInsets();
        setupFlutterLaunchButton();

    }
    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setupFlutterLaunchButton() {
        findViewById(R.id.clickme).setOnClickListener(v -> {
            launchPlaysoutActivity();
        });
        findViewById(R.id.clickme2).setOnClickListener(v -> {
            launchGameActivity();
        });
    }
    private void launchPlaysoutActivity() {
        try {
            FlutterEngine flutterEngine = FlutterEngineCache.getInstance().get(CacheId.PLAYSOUT_ENGINE_ID);
            if(flutterEngine!=null) {
                MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
                HashMap<String, Object> arguments = new HashMap<>();
                arguments.put("appAdId", "ca-app-pub-3940256099942544/1712485313"); // change them by your admob ad ID
                arguments.put("gameAdId", "ca-app-pub-3940256099942544/1712485313");
                channel.invokeMethod("init", arguments);

                Intent intent = FlutterActivity
                        .withCachedEngine(CacheId.PLAYSOUT_ENGINE_ID)
                        .build(MainActivity.this);
                startActivity(intent);
            }
        } catch (Exception e) {
            handleLaunchError(e);
        }
    }

    private void launchGameActivity() {
        try {
            FlutterEngine flutterEngine = FlutterEngineCache.getInstance().get(CacheId.PLAYSOUT_ENGINE_ID);
            if(flutterEngine!=null) {
                MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
                HashMap<String, Object> arguments = new HashMap<>();
                arguments.put("appAdId", "ca-app-pub-3940256099942544/1712485313"); // change them by your admob ad ID
                arguments.put("gameAdId", "ca-app-pub-3940256099942544/1712485313");
                arguments.put("gameId", "poiv5z171lslnuof0g");
                arguments.put("gameTitle", "KittyCrushSaga");
                channel.invokeMethod("init", arguments);

                Intent intent = FlutterActivity
                        .withCachedEngine(CacheId.PLAYSOUT_ENGINE_ID)
                        .build(MainActivity.this);
                startActivity(intent);
            }
        } catch (Exception e) {
            handleLaunchError(e);
        }
    }

    private void handleLaunchError(Exception e) {
        Log.e("FlutterLaunch", "Error: " + e.getMessage(), e);
        Toast.makeText(this, "run failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= TRIM_MEMORY_MODERATE) {
            System.gc();
        }
    }
}