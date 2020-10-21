void call() {
    def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
    def emailBody = "Check console output at ${BUILD_URL} to view the results"
    emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'FailingTestSuspectsRecipientProvider']] to: 'gbrocard@ubigreen.com', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
}