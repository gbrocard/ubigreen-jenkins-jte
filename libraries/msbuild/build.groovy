void call() {
    stage('Build: MSBuild') {
        bat "\"${env.MSBUILD}\" ${env.WORKSPACE}\\UbigreenPerformance.sln /p:Configuration=${config.buildConfiguration} /p:WarningLevel=0 /m"
    }
}
