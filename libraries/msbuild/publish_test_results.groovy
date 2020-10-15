@AfterStep({ context.step == "unit_test" && context.library == "msbuild" })
void call(context) {
    ws(params.upstreamWorkspace) {
        mstest testResultsFile:"**/*.trx"

        //reset test results
        deleteDir('${WORKSPACE}\\TestResults\\Jenkins')
    }
}
