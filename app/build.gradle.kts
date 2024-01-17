plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.androidprojectbtl"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidprojectbtl"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    //noinspection GradleCompatible
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0") // thong tin ca nhan
    implementation ("com.squareup.picasso:picasso:2.71828") // Picasso xử lý các ImageView trong adapter
    implementation ("com.google.firebase:firebase-firestore:24.4.1")
    implementation ("com.google.firebase:firebase-analytics")
    implementation (platform("com.google.firebase:firebase-bom:32.7.0"))// lưu dữ liệu firebasestore
    implementation ("com.google.android.gms:play-services-maps:18.0.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
}