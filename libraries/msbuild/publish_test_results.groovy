@AfterStep({ context.step == "unit_test" && context.library == "msbuild" })
void call(context) {
    ws(params.upstreamWorkspace) {
        mstest testResultsFile:"**/*.trx"

        //reset test results
        def workspace = pwd()
        dir ("${workspace}\\TestResults\\Jenkins") {
            print("Deleting ${pwd()}...")
            deleteDir()
        }
    }
}
