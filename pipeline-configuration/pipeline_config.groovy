libraries {
    merge = true
    msbuild {
        nugetPath = 'C:\\Program Files (x86)\\NuGet\\nuget.exe'
        project = "merde\\UbigreenPerformance.sln"
        nugetSources = ['https://api.nuget.org/v3/index.json', 'http://178.170.110.241:8080/nuget']
        buildConfiguration = "Release"
        testsPath = "merde\\Tests"
        testsResultPath = "merde\\TestResults\\Jenkins\\merde"
    }
    // node
    notification
    resolve_dependencies
}
