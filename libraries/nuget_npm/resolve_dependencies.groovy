void call() {
    stage('NuGet: Resolve dependencies') {
        bat "\"${config.nuget_exe}\" restore \"%WORKSPACE%\\UbigreenPerformance.sln\" -source https://api.nuget.org/v3/index.json -source http://178.170.110.241:8080/nuget -OutputDirectory %WORKSPACE%\\packages"
    }
    stage('npm: Resolve dependencies') {
        dir("Ubigreen.PerformanceAnalyser.FullWeb") {
            //remove readonly on package*.json
            bat (script: "attrib -r package.json && attrib -r package-lock.json && npm i", returnStdout: true)
        }
    }
}
