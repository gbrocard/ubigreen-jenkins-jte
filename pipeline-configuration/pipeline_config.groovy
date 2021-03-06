application_environments {
    tests {
        jobName = "UbigreenPerformance/UbigreenPerformanceUnitTests"
    }
}

libraries {
    tfs
    nuget_npm {
        nuget_exe = "C:\\Program Files (x86)\\NuGet\\nuget.exe"
    }
    msbuild {
        MSBUILD_EXE = "C:\\Program Files (x86)\\MSBuild\\14.0\\Bin\\MSBuild.exe"
        TESTS_PATH = "Tests"
        RESULTS_PATH = "TestResults\\Jenkins"
        buildConfiguration = "Release"
        projectPath = "UbigreenPerformance.sln"
    }
    notification
    jenkins_jobs
}

template_methods {
    chain_job
}