@BeforeStep({ context.step.equals("build") })
void call(context) {
    String sources = config.nugetSources.join(' -sources=')
    String nugetPath = config.nugetPath    
    String projectPath = config.project

    stage('Resolve Dependencies: NuGet') {
        def workspace = env.WORKSPACE
        print ("WORKSPACE: ${workspace}")
        bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${workspace}\\packages"
    }
}
