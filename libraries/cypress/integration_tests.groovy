void call() {
    stage("Cypress: Integration tests") {
        bat "npm run test"
    }
}