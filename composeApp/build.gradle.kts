import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.mokoResources)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        // Required for moko-resources to work
        applyDefaultHierarchyTemplate()

        androidMain {
            dependencies {
                implementation(project.dependencies.platform(libs.compose.bom))
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.android.sqlDelight)

                implementation(libs.media3.ui)
                implementation(libs.media3.exoplayer)
                implementation(libs.media3.exoplayer.dash)
                implementation("androidx.credentials:credentials:1.3.0-alpha01")
                implementation("androidx.credentials:credentials-play-services-auth:1.3.0-alpha01")
                implementation("com.google.android.libraries.identity.googleid:googleid:1.1.0")

            }

            // Required for moko-resources to work
            dependsOn(commonMain.get())
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.ktor.client.darwin)
            implementation(libs.ios.sqlDelight)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(libs.moko.mvvm.core)
            implementation(libs.moko.mvvm.flow)
            implementation(libs.moko.mvvm.compose)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kamel)
            implementation(libs.koin.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.koin)
            implementation(libs.moko.resources.compose)
            implementation(libs.main.sqlDelight) //convert database quires to flow
            //implementation(libs.main.sqlDelight1) //convert database quires to flow


        }
//        jvmMain.dependencies {
//            implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
//        }
    }
}

android {
    namespace = "com.jetbrains.kmpapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.jetbrains.kmpapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.jetbrains.kmpapp"
}
repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation(libs.androidx.ui.tooling.preview.android)
}
sqldelight {
    databases {

        create("Database") {

            packageName.set("com.database")
        }
    }
}
