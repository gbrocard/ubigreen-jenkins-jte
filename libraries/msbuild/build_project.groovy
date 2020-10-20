void call() {
    stage("MSBuild: Build") {
        def emailSubject = "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}!"
        def emailBody = "Check console output at ${BUILD_URL} to view the results"
        emailext attachLog: true, body: emailBody, to: 'gbrocard@ubigreen.com', subject: emailSubject, from: "DevOps <noreply@ubigreen.com>"
        
        bat "\"${config.MSBUILD_EXE}\" %WORKSPACE%\\UbigreenPerformance.sln /p:Configuration=Release /p:WarningLevel=0 /m"
    }
}
