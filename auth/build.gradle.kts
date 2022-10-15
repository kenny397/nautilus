plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation ("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation ("org.hamcrest:hamcrest:2.2")
}

configurations{
    create("tests")
}

val testsJar by tasks.registering(Jar::class) {
    dependsOn("testClasses")
    archiveClassifier.set("tests")
    from(project.the<SourceSetContainer>()["test"].output)
}
artifacts {
    add("tests", testsJar)
}