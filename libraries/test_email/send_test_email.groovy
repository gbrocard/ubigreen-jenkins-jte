void call() {
    def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
    def emailBody = "Check console output at ${BUILD_URL} to view the results"
    emailext attachLog: true, body: emailBody, to: 'gbrocard@ubigreen.com,wcourtade@ubigreen.com,eelaoud@ubigreen.com,npunti@ubigreen.com,thuguet@ubigreen.com,lmarfaing@ubigreen.com,mleitao@ubigreen.com', subject: emailSubject, from: "DevOps <team-solution@ubigreen.com>"
}