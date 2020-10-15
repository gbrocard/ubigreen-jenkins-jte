@AfterStep({ context.step == "unit_test" && context.library == "msbuild" })
void call(context) {
    ws(params.upstreamWorkspace) {
        mstest testResultsFile:"**/*.trx"

        //reset test results
        dir ('${WORKSPACE}\\TestResults\\Jenkins') {
            print("Deleting ${pwd()}...")
            deleteDir()
        }
    }
}
