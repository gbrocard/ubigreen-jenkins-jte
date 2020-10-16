void call() {
    stage("Cypress: Integration tests") {
        dir (config.projectPath) {
            print("${pwd()}")
            bat "npm run test"
        }
    }
}