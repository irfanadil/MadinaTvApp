import com.android.build.api.dsl.Lint
import com.android.build.api.dsl.LintOptions

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.android.args)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

    //kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22" // MUST match Kotlin version

}

android {
    namespace = "com.usa.madina.mosques"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.usa.madina.mosques"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    lint {
        disable.add("NullSafeMutableLiveData")
        abortOnError = false
        checkDependencies = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation ("androidx.appcompat:appcompat:1.7.1")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.9.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.9.0")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.9.1")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.google.code.gson:gson:2.12.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    // daggerHilt
    implementation("com.google.dagger:hilt-android:2.56.1")
    kapt("com.google.dagger:hilt-android-compiler:2.56.1")

    //Kotlin serializer...
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    //implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20") // Must match

    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0") // For image loading

}