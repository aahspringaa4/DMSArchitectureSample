dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "DMSArchitectureSample"
include (":presentation")
include (":data-features")
include (":data-auth")
include (":domain-auth")
include (":domain-features")
include (":local-database")
include (":domain-local-database")
