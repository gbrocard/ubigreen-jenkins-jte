void call() {
    stage ("npm: Resolve dependencies") {
        dir(config.projectPath) {
            //remove readonly on package*.json
            bat (script: "attrib -r package.json && attrib -r package-lock.json && npm i", returnStdout: true)
        }
    }
}
