import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "eu.thomaskuenneth.swingpaneldemo"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation("com.github.sarxos:webcam-capture:0.3.12")
    // Default BridJ driver is x86_64-only; native driver uses AVFoundation on macOS/arm64
    implementation("io.github.eduramiba:webcam-capture-driver-native:1.3.1")

    implementation(compose.desktop.currentOs)
    implementation(compose.components.resources)
    implementation(compose.material3)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            packageName = "SwingPanelDemo"
            packageVersion = "1.0.0"
        }
    }
}
