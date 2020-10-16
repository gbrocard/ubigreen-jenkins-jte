void call() {
    stage("Cypress: Integration tests") {
        docker.image('cypress/base:10').inside {
            bat "npm run test"
        }
    }
}