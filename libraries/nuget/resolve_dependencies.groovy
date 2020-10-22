void call() {
    stage('NuGet: Resolve dependencies') {
        bat "\"${config.nuget_exe}\" restore \"%WORKSPACE%\\${config.projectPath}\" -source https://api.nuget.org/v3/index.json -source http://178.170.110.241:8080/nuget -OutputDirectory %WORKSPACE%\\packages"
    }
}
