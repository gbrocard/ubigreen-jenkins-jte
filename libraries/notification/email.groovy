@AfterStep({context.step.equals("build") && context.status.equals("FAILURE")})
void call(context) {
    def emailSubject = "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${context.status}!"
    def emailBody = "Check console output at ${env.BUILD_URL} to view the results"
    emailext body: emailBody, recipientProviders: [requestor(), buildUser(), developers(), recipients()], subject: emailSubject, from: "DevOps <devops@ubigreen.com>"
}
