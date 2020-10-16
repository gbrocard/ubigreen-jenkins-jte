void call() {
    stage("npm: Build") {
        dir(config.projectPath) {
            bat (script: "attrib -r package.json && attrib -r package-lock.json && npm i", returnStdout: true)
        }
    }
}
