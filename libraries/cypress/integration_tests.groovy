void call() {
    stage("Cypress: Integration tests") {
        dir (config.projectPath) {
            print("${pwd()}")
            try {
                bat "npx cypress run --reporter junit --reporter-options \"mochaFile=results/Ubigreen.PerformanceAnalyser.FullWeb.xml\""
            } catch (Exception e) {
                print(e.getMessage())
            }
        }
    }
}