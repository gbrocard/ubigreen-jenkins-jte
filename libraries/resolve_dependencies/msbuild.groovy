@BeforeStep({ context.step.equals("build") })
void call(context) {
    String sources = config.nugetSources.join(' -sources=')
    String nugetPath = config.nugetPath    
    String projectPath = config.project

    stage('Resolve Dependencies: NuGet') {
        print ("${env.WORKSPACE}")
        bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${env.WORKSPACE}\\packages"
    }
}
