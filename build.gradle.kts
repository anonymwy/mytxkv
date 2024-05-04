plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "com.wuyangnju"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.wuyangnju.mytxkv.MainKt")
}