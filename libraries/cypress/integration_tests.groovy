void call() {
    stage("Cypress: Integration tests") {
        dir (params.projectPath) {
            bat "npm run test"
        }
    }
}