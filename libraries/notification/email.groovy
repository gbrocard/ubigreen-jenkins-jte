@AfterStep({context.status.equals("FAILURE")})
void call(context) {
    def emailSubject = "merde - Build #merde - merde!"
    def emailBody = "Check console output at merde to view the results"
    emailext body: emailBody, recipientProviders: [requestor(), buildUser(), developers(), recipients()], subject: emailSubject, from: "DevOps <devops@ubigreen.com>"
}
