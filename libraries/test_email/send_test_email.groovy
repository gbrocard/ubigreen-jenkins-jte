void call() {
    def jobName = "${JOB_NAME}"
    def isTestJob = jobName.toLowerCase().contains("test")

    if (currentBuild.result == 'FAILURE') {
        def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        print("FAILURE")
        // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else if (isTestJob) {
        def emailSubject = "Tests regression on ${JOB_NAME} - Build #${BUILD_NUMBER}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"

        def previousBuild = currentBuild.getPreviousSuccessfulBuild()
        if (previousBuild == null) return;

        def previousBuildFailedTestNumber = currentBuild.getPreviousSuccessfulBuild()?.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
        def previousBuildFailedTests = currentBuild.getPreviousSuccessfulBuild()?.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()
        
        def currentBuildFailedTestNumber = currentBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
        def currentBuildFailedTests =  currentBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()
        
        //si on a + de tests en failure ou si les tests en failure ont changés
        if (currentBuildFailedTestNumber > previousBuildFailedTestNumber || !(previousBuildFailedTests.equals(currentBuildFailedTests))) {
            print("REGRESSION !")
        }
        // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']], subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else {
        print ("No email to send")
    }
}