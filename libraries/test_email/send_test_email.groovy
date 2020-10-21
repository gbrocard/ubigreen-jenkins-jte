void call() {
    def jobName = '$JOBNAME'
    print(jobName)
    def isTestJob = jobName.toLowerCase().contains("test")

    if (isTestJob) {
        def emailSubject = "Tests regression on ${JOB_NAME} - Build #${BUILD_NUMBER}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        // emailext attachLog: true, body: emailBody, to: 'gbrocard@ubigreen.com', subject: emailSubject, from: "DevOps <noreply@ubigreen.com>"
        emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']], to: 'gbrocard@ubigreen.com', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    } else {
        def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        // emailext attachLog: true, body: emailBody, to: 'gbrocard@ubigreen.com', subject: emailSubject, from: "DevOps <noreply@ubigreen.com>"
        emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider'], [$class: 'FailingTestSuspectsRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
    }
}