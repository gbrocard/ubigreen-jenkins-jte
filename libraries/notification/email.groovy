//only executes at the end of the pipline and if in failure && currentBuild.result == 'FAILURE' 
@Notify({ context.step == "checkout" })
void call(context) {
    def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
    def emailBody = "Check console output at ${BUILD_URL} to view the results"
    emailext attachLog: true, body: emailBody, to: 'thuguet@ubigreen.com,gbrocard@ubigreen.com,lmarfaing@ubigreen.com,wcourtade@ubigreen.com,npunti@ubigreen.com,eelaoud@ubigreen.com', subject: emailSubject, from: "DevOps <noreply@ubigreen.com>"
    // emailext attachLog: true, body: emailBody, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider']], to: '$DEFAULT_RECIPIENTS', subject: emailSubject, from: "DevOps <noreply@ubigreen.com>"
}