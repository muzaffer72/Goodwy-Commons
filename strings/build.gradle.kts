plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlinAndroid)
    `maven-publish`
}

// Match the coordinates referenced from the published commons-* artifacts.
group = "com.github.goodwy.goodwy-commons"
version = "5c55d3f6cc"

android {
    namespace = "com.goodwy.strings"
    compileSdk = libs.versions.app.build.compileSDKVersion.get().toInt()

    publishing {
        singleVariant("release") {}
    }
}

publishing.publications {
    create<MavenPublication>("release") {
        afterEvaluate {
            from(components["release"])
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/muzaffer72/Goodwy-Commons")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
