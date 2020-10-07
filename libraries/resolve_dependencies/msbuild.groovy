@BeforeStep({ context.step.equals("build") })
void call(context) {
    print("test")
    String sources = config.nugetSources.join(' -sources=')
    String nugetPath = config.nugetPath    
    String projectPath = config.project
    print("test2")
    stage('Resolve Dependencies: NuGet') {
        print("test3")
        print("${env.WORKSPACE}")
        print("test4") 
        bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${env.WORKSPACE}\\packages"
    }
}
