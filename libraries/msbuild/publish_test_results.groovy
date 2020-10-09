@AfterStep({ context.step == "unit_test" && context.library == "msbuild" })
void call(context) {
    mstest testResultsFile:"**/*.trx"
}
