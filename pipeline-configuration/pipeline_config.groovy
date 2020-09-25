libraries {
    merge = true
    resolve_dependencies {
        nugetPath = 'C:\\Program Files (x86)\\NuGet\\nuget.exe'
        nugetSources = ['https://api.nuget.org/v3/index.json', 'http://178.170.110.241:8080/nuget']
        project = "${env.WORKSPACE}\\UbigreenPerformance.sln"
    }
    msbuild {
        
        buildConfiguration = "Release"
        testsPath = "${env.WORKSPACE}\\Tests"
        testsResultPath = "${env.WORKSPACE}\\TestResults\\Jenkins\\${env.BUILD_TAG}"
    }
    // node
    notification
}
