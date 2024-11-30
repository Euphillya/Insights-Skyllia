plugins {
    id("java")
}

group = "fr.euphyllia.skyllia_insight_addon"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.fvdh.dev/releases")
    maven{
        url = uri("https://maven.pkg.github.com/Euphillya/Skyllia")
        credentials {
            username = System.getenv("GITHUB_USERNAME") ?: ""
            password = System.getenv("GITHUB_TOKEN") ?: ""
        }
    }
}

dependencies {
    compileOnly(files("./libs/Insights-6.19.2.jar"))
    compileOnly(files("./libs/Skyllia-1.0-5ec34bf-all.jar"))
    compileOnly("io.papermc.paper:paper-api:1.21.3-R0.1-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}