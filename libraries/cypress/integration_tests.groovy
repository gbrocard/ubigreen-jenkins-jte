void call() {
    docker.image('cypress/base:10').inside {
        bat "npm run test"
    }
}