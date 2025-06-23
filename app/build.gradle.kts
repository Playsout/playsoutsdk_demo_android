plugins {
    alias(libs.plugins.android.application)
}



android {
    namespace = "com.playsout.sdkdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.playsout.sdkdemo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            //abiFilters 'armeabi-v7a','arm64-v8a','x86_64'
            //abiFilters.add("armeabi-v7a")
            abiFilters.add("x86_64")
            abiFilters.add("arm64-v8a")

        }

    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("profile") {
            initWith(getByName("debug"))
        }


    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    configurations {
        getByName("profileImplementation") {
        }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(fileTree(mapOf("dir" to "../libs", "include" to listOf("*.jar","*.aar"))))
    //implementation(files("../libss/demo.aar"))
   // implementation(project(":libss"))

    debugImplementation ("com.playsout.playsout_sdk:flutter_debug:1.0")
    add("profileImplementation", "com.playsout.playsout_sdk:flutter_profile:1.0")
    releaseImplementation ("com.playsout.playsout_sdk:flutter_release:1.0")

}