void call() {
    stage("Cypress: Integration tests") {
        dir (params.projectPath) {
            print("${pwd()}")
            bat "npm run test"
        }
    }
}