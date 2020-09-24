void call() {
    stage('Build: MSBuild') {
        environment {
            PATH = "${env.APPDATA}\\npm;$PATH"
        }
        steps {
            bat "\"${env.MSBUILD}\" ${env.WORKSPACE}\\UbigreenPerformance.sln /p:Configuration=${config.buildConfiguration} /p:WarningLevel=0 /m"
        }
    }
}
