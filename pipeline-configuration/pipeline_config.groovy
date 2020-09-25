libraries {
    merge = true
    resolve_dependencies
    msbuild {
        nugetPath = 'C:\\Program Files (x86)\\NuGet\\nuget.exe'
        project = "${env.WORKSPACE}\\UbigreenPerformance.sln"
        nugetSources = ['https://api.nuget.org/v3/index.json', 'http://178.170.110.241:8080/nuget']
        buildConfiguration = "Release"
        testsPath = "${env.WORKSPACE}\\Tests"
        testsResultPath = "${env.WORKSPACE}\\TestResults\\Jenkins\\${env.BUILD_TAG}"
    }
    // node
    notification
}
