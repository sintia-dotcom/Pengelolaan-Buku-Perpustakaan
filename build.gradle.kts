plugins {
    kotlin("jvm") version "1.9.23"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "pengelolaan_buku_perpustakaan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.formdev:flatlaf:3.4")
    implementation("mysql:mysql-connector-java:5.1.49")
    implementation("org.apache.poi:poi-ooxml:5.2.5")
    implementation("com.github.librepdf:openpdf:1.3.30")
}

application {
    mainClass.set("pengelolaan_buku_perpustakaan.MainKt")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "pengelolaan_buku_perpustakaan.MainKt"
    }
}

kotlin {
    jvmToolchain(21)
}
