
plugins {
    id("org.springframework.boot")
    kotlin("plugin.jpa")
    kotlin("plugin.noarg")
    kotlin("plugin.allopen")
    kotlin("jvm")
    kotlin("plugin.spring")

}




allOpen {
    annotation("javax.persistence.Entity")
}

noArg {
    annotation("javax.persistence.Entity")
}


dependencies {
    implementation(project(":domain"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("io.jsonwebtoken:jjwt:0.9.1")

    /**
     * jpa
     */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation ("com.h2database:h2")
    testImplementation("io.mockk:mockk:1.12.5")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(path = ":domain", configuration = "tests"))
}
