plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    maven("https://jitpack.io")
}

// val dittNavDependenciesVersion = "2021.11.09-12.25-fbd35517b350"
val dittNavDependenciesVersion = "2022.01.27-13.16-8429354bdc72"

dependencies {
    implementation("com.github.navikt:dittnav-dependencies:$dittNavDependenciesVersion")
}
