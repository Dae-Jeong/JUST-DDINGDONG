plugins {
    java
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "just.ddingdong"
version = "0.0.1-SNAPSHOT"
description = "dd-api"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Apply separated configuration scripts
apply(from = "gradle/scripts/dependencies.gradle")
apply(from = "gradle/scripts/code-quality.gradle")
apply(from = "gradle/scripts/git-hooks.gradle")
