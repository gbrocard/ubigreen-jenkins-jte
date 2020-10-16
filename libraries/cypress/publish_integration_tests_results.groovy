@AfterStep({ context.step == "integration_tests" && context.library == "cypress" })
void call(context) {
    //publish junit tests results
    dir("Ubigreen.PerformanceAnalyser.FullWeb/cypress") {
        junit "results/**/*.xml"
    }
    
    dir("Ubigreen.PerformanceAnalyser.FullWeb/cypress/results"){
        deleteDir()
    }
}
