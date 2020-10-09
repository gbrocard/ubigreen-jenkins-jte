void call() {
    stage("MSBuild: Build") {
        bat "\"${config.MSBUILD_EXE}\" %WORKSPACE%\\UbigreenPerformance.sln /p:Configuration=Release /p:WarningLevel=0 /m"
    }
}