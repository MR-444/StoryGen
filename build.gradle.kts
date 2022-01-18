import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "me.mr"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.exposed", "exposed-core", "0.37.3")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.37.3")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.37.3")
    implementation("com.h2database:h2:2.1.210") // H2 Database

    testImplementation("io.kotest:kotest-runner-junit5:5.0.3") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:5.0.3") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:5.0.3") // for kotest property test

    testImplementation(kotlin("test"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}