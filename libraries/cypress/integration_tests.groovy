void call() {
    stage("Cypress: Integration tests") {
        dir (config.projectPath) {
            print("${pwd()}")
            try {
                bat "npx cypress run"
            } catch (Exception e) {
                print(e.getMessage())
            }
        }
    }
}