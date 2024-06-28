plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.components.resources)
    implementation(compose.material3)
}

compose.desktop {
    application {
        mainClass = "de.thomaskuenneth.counterdemodesktop.MainKt"
        nativeDistributions {
            packageName = "CounterDemoDesktop"
            packageVersion = "1.0.0"
        }
    }
}
