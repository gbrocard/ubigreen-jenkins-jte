void call() {
    stage('NuGet: Resolve dependencies') {
        bat "\"%NUGET%\" restore \"%WORKSPACE%\\UbigreenPerformance.sln\" -source https://api.nuget.org/v3/index.json -source http://178.170.110.241:8080/nuget -OutputDirectory %WORKSPACE%\\packages"
    }
}
