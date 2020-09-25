@BeforeStep({context.step.equals("build")})
void call(context) {
    String sources = config.nugetSources.join(' -sources=')
    String nugetPath = config.nugetPath
    String projectPath = config.project

    node {
        stage('Resolve Dependencies: NuGet') {
            println("env:  " + env.WORKSPACE)
            bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${env.WORKSPACE}\\packages"
        }
    }
}
