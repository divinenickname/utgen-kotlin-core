import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    kotlin("jvm") version "1.7.22"
}

version = "0.1.0-SNAPSHOT"
group = "org.ilinykh.kotlin.utgen"
java.targetCompatibility = JavaVersion.VERSION_17
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")

    runtimeOnly("com.squareup:kotlinpoet:1.16.0")
}

repositories {
    mavenLocal()
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
