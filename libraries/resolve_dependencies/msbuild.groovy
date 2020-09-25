@BeforeStep({ context.step.equals("build") })
void call(context) {
    println("test1")


    String sources = config.nugetSources.join(' -sources=')
    println("test2")

    String nugetPath = config.nugetPath
    println("test3")
    
    String projectPath = config.project
    println("test4")

    node {
        stage('Resolve Dependencies: NuGet') {
            println("test5")
         
            bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${env.WORKSPACE}\\packages"
        }
    }
}
