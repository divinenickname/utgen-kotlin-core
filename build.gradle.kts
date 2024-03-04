import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    kotlin("jvm") version "1.9.22"
    id("antlr")
}

version = "0.1.0-SNAPSHOT"
group = "org.ilinykh.kotlin.utgen"
java.targetCompatibility = JavaVersion.VERSION_17
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")

    api("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")

    implementation("com.squareup:kotlinpoet:1.16.0")
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
tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
}

tasks.withType<Jar>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.ilinykh.kotlin.utgen.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
