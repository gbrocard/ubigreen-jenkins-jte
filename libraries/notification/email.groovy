//only executes at the end of the pipline and if in failure
@Notify({ context.step == null })
void call(context) {
    def jobName = "${JOB_NAME}"
    def isTestJob = jobName.toLowerCase().contains("test")

    if (currentBuild.result == 'FAILURE') {
        def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else if (isTestJob) {
        def emailSubject = "Tests regression on ${JOB_NAME} - Build #${BUILD_NUMBER}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']], subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else {
        print ("No email to send")
    }
}
