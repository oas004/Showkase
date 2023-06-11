plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.3.1"
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
}
repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "18"
//}


dependencies {
    ksp(project(":showkase-processor"))
    implementation(project(":showkase-annotation"))

    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.2.1")
    implementation("org.jetbrains.compose.material3:material3:1.2.1")

}

compose.desktop {
    application {
        mainClass = "src/main/kotlin/MainKt"
        nativeDistributions {
            modules("java.sql")
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
            )
            packageVersion = "1.0.0"
            packageName = "theme"

        }
    }
}
