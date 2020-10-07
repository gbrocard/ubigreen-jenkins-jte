@AfterStep({currentBuild.result.toString() == "FAILURE"})
void call(context) {
    print "slack"
}
