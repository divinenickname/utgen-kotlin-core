import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

version = "0.1.0-SNAPSHOT"
group = "template"
java.targetCompatibility = JavaVersion.VERSION_17
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
    implementation("org.hibernate:hibernate-core:6.1.7.Final")

    api("org.postgresql:postgresql:42.6.0")
    implementation("org.liquibase:liquibase-core:4.20.0")
    implementation("org.jsoup:jsoup:1.15.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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