allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Set the root project build directory
val newBuildDir = rootProject.layout.buildDirectory.dir("../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

// Set each subproject's build directory inside the shared build folder
subprojects {
    val subBuildDir = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(subBuildDir)
}

// Ensure app project is evaluated first
subprojects {
    project.evaluationDependsOn(":app")
}

// Clean task
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
