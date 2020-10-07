@AfterStep({currentBuild.result.toString() == "FAILURE"})
void call(context) {
    def emailSubject = "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${currentBuild.result.toString()}!"
    def emailBody = "Check console output at ${env.BUILD_URL} to view the results"
    // emailext body: emailBody, recipientProviders: [requestor(), buildUser(), developers(), recipients()], subject: emailSubject, from: "DevOps <devops@ubigreen.com>"
    emailext attachLog: true, body: emailBody, to: "gbrocard@ubigreen.com", subject: emailSubject, from: "DevOps <devops@ubigreen.com>"

}
