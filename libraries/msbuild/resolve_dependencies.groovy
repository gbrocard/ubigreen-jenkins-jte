void call() {
    String sources = config.nugetSources.join(' -sources=')
    String nugetPath = config.nugetPath
    String projectPath = config.project

    stage('Resolve Dependencies: NuGet') {
        steps {
            bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory %WORKSPACE%\\packages"
        }
    }
}
