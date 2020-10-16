void call() {
    stage("Cypress: Integration tests") {
        dir (config.projectPath) {
            print("${pwd()}")
            try {
                bat "npx cypress run --reporter junit"
            } catch (Exception e) {
                print(e.getMessage())
            }
        }
    }
}