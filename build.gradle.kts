plugins {
    id("java")
}

group = "fr.euphyllia.skyllia_insight_addon"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
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
    compileOnly("dev.frankheijden.insights:Insights:6.17.2")
    compileOnly("fr.euphyllia.skyllia:api:1.0-RC7.1")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}