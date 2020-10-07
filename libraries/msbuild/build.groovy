void call() {
    stage('Build: MSBuild') {
        bat "\"${config.MSBuild}\" ${env.WORKSPACE}\\UbigreenPerformance.sln /p:Configuration=${config.buildConfiguration} /p:WarningLevel=0 /m"
    }
}
