void call() {
    stage('Build: MSBuild') {
        bat "\"merde\" merde\\UbigreenPerformance.sln /p:Configuration=${config.buildConfiguration} /p:WarningLevel=0 /m"
    }
}
