plugins {
    java
    kotlin("jvm") version "1.4.31"
}

group = "de.hglabor"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
}
