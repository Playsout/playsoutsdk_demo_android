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

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

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
            launchFlutterActivity();
        });
    }
    private void launchFlutterActivity() {
        try {
            Intent intent = FlutterActivity
                    .withCachedEngine(CacheId.PLAYSOUT_ENGINE_ID)
                    //.initialRoute("/")
                    //.withCachedEngine("default_engine")
                    //.initialRoute("/")
                    .build(MainActivity.this);
            startActivity(intent);
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