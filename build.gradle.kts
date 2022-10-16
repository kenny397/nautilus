import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


buildscript {
    repositories {
        mavenCentral()

    }
}

plugins {
    id("org.springframework.boot") version "2.7.1" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
 

    /**
     * jpa 관련 plugin
     */
    kotlin("plugin.jpa") version "1.5.21"  apply false
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.5.21" apply false
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.5.21" apply false

 

    
}


allprojects {

    group = "com.jun"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            apiVersion = "1.5"
            languageVersion = "1.5"
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
        tasks.withType<Test> {
            useJUnitPlatform()

        }
    }
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("io.spring.dependency-management")


    }


}

