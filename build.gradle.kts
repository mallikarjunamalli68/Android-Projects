// build.gradle.kts (Project Level)

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1") // replace with your actual version if using libs.versions.toml
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    id("com.google.gms.google-services") apply false
}
