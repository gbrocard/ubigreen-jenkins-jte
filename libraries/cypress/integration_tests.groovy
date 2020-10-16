void call() {
    stage("Cypress: Integration tests") {
        dir (config.projectPath) {
            try {
                bat "npx cypress run --config video=false --reporter junit --reporter-options \"mochaFile=cypress/results/Ubigreen.PerformanceAnalyser.FullWeb.xml\""
            } catch (Exception e) {
                print(e.getMessage())
            }
        }
    }
}