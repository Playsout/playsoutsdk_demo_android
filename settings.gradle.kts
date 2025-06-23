pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()


        flatDir {
            dirs("libs")
        }
        }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://storage.googleapis.com/download.flutter.io")
        maven(url = "../Playsout_sdk_android/build/host/outputs/repo")
    }
}

rootProject.name = "PlaysoutSDKDemo"
include(":app")
include(":libs")
