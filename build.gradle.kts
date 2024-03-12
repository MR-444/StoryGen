import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinxCoRoutinesVersion = "1.9.0"

plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "me.mr"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.exposed", "exposed-core", "0.48.0")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.48.0")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.48.0")

    implementation("com.h2database:h2:2.2.224") // H2 Database
    implementation("org.slf4j:slf4j-api:2.0.12") // logger
    implementation("ch.qos.logback:logback-classic:1.5.3") // logback dependency
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:$kotlinxCoRoutinesVersion")

    testImplementation("io.kotest:kotest-runner-junit5:5.8.1") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:5.8.1") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:5.8.1") // for kotest property test

    testImplementation(kotlin("test"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}