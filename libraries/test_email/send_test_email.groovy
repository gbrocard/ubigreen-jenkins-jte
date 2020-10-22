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

        if (isRegression()) {
            print("REGRESSION !")
        } else {
            print("no regression")
        }
        // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']], subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else {
        print ("No email to send")
    }
}

@NonCPS
def isRegression() {
    def previousBuild = currentBuild.rawBuild.getPreviousNotFailedBuild()
    if (previousBuild == null) {
        print("No previous successfull or unstable build")
        return false;
    } 

    def previousBuildFailedTestNumber = previousBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
    def previousBuildFailedTests = previousBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()
    
    def currentBuildFailedTestNumber = currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
    def currentBuildFailedTests =  currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()

    print("Previous nb : ${previousBuildFailedTestNumber} build: ${previousBuild.number}")
    print("Current nb : ${currentBuildFailedTestNumber}")

    print("Failed tests are not equal ${previousBuildFailedTests != currentBuildFailedTests}")

    //si on a + de tests en failure ou si les tests en failure ont changés
    return (currentBuildFailedTestNumber > previousBuildFailedTestNumber || !testsAreEqual(currentBuildFailedTests, previousBuildFailedTests))
}

@NonCPS
def testsAreEqual(currentTestList, previousTestList) {
    //currentTestList est inférieur ou égal à previousTestList
    def nbTests = currentTestList.size();

    for (int i = 0; i < nbTests; i++) {
        def currentTest = currentTestList[i];
        def previousTest = previousTestList.find { it.getFullName() == currentTest.getFullName() };

        // test is not found
        if (previousTest == null) {
            print("ERROR : ${currentBuild.getFullName()} NOT FOUND");
            return false;
        }

        // compare error messages
        if (currentTest.getErrorDetails() != previousTest.getErrorDetails()) {
            print("Current error message : ${currentTest.getErrorDetails()}")
            print("Previous error message : ${previousTest.getErrorDetails()}")

            return false;
        }
    }

    return true;
}
