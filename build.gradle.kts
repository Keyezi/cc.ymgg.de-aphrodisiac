plugins {
    val kotlinVersion = "1.4.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.3.2" // mirai-console version
}

mirai {
    coreVersion = "2.3.2" // mirai-core version
}

group = "cc.ymgg"
version = "2.2"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}
dependencies{
    implementation("com.squareup.okhttp3:okhttp:4.1.0")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("com.alibaba:fastjson:1.2.75")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //  implementation ("com.squareup.retrofit2:retrofit-gson:2.9.0")
    implementation(kotlin("stdlib"))
}