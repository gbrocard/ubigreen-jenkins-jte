libraries {
    tfs
    nuget_npm {
        nuget_exe = "C:\\Program Files (x86)\\NuGet\\nuget.exe"
        NODE_PATH = "%WORKSPACE%\\Ubigreen.PerformanceAnalyser.FullWeb\\node_modules"
        gulp = "%WORKSPACE%\\Ubigreen.PerformanceAnalyser.FullWeb\\node_modules\\gulp\\bin\\gulp.js"
    }
    msbuild {
        MSBUILD_EXE = "C:\\Program Files (x86)\\MSBuild\\14.0\\Bin\\MSBuild.exe"
        TESTS_PATH = "%WORKSPACE%\\Tests"
        RESULTS_PATH = "%WORKSPACE%\\TestResults\\Jenkins\\%BUILD_TAG%"
        buildConfiguration = "Release"
    }
    notification
}


