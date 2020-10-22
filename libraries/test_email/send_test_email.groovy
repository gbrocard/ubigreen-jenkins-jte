@AfterStep({ context.step == "unit_test" })
void call(context) {
    def jobName = "${JOB_NAME}"
    def isTestJob = jobName.toLowerCase().contains("test")

    if (currentBuild.result == 'FAILURE') {
        def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else if (isTestJob) {
        def emailSubject = "Tests regression on ${JOB_NAME} - Build #${BUILD_NUMBER}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"

        if (isRegression()) {
            print("regression detected")
            // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
        } else {
            print("No regression found")
        }
    } else {
        print ("No email to send")
    }
}

def isRegression() {
    print("Checking for tests regression...")

    def previousBuild = currentBuild.rawBuild.getPreviousNotFailedBuild()
    if (previousBuild == null) {
        print("No previous successfull or unstable build was found")
        return false;
    } 

    def previousBuildFailedTestNumber = previousBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
    def previousBuildFailedTests = previousBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()
    
    def currentBuildFailedTestNumber = currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailCount()
    def currentBuildFailedTests =  currentBuild.rawBuild.getAction(hudson.tasks.junit.TestResultAction.class)?.getFailedTests()

    print("Comparing tests to build nb ${previousBuild.number}. Previous failed tests : ${previousBuildFailedTestNumber}, current failed tests : ${currentBuildFailedTestNumber}")

    //si on a + de tests en failure ou si les tests en failure ont changés
    return (currentBuildFailedTestNumber > previousBuildFailedTestNumber || !testsAreEqual(currentBuildFailedTests, previousBuildFailedTests))
}

/*
* Prends les deux listes de tests (courants et précédents)
* et compare leur nom et message d'erreur pour détecter des changements dans les résultats des tests
*/
def testsAreEqual(currentTestList, previousTestList) {
    //taille de currentTestList est toujours inférieure ou égale à previousTestList
    def nbTests = currentTestList.size();

    for (int i = 0; i < nbTests; i++) {
        def currentTest = currentTestList[i];
        def previousTest = previousTestList.find { it.getFullName() == currentTest.getFullName() };

        // new test in failure
        if (previousTest == null) {
            print("ERROR : ${currentTest.getFullName()} NOT FOUND");
            return false;
        }

        // compare error messages
        if (currentTest.getErrorDetails() != previousTest.getErrorDetails()) {
            print("----------------------------------------- REGRESSION FOUND -----------------------------------------")
            print("Previous error message : ${previousTest.getErrorDetails()}")
            print("Current error message : ${currentTest.getErrorDetails()}")

            return false;
        }
    }

    return true;
}
