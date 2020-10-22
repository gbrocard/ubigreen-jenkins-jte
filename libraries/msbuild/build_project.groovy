void call() {
    stage("MSBuild: Build") {
        bat "\"${config.MSBUILD_EXE}\" %WORKSPACE%\\${config.projectPath} /p:Configuration=Release /p:WarningLevel=0 /m"
    }
}
