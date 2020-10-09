void call(context) {
    print("test")
    def sources = config.nugetSources.join(' -sources=')
    def nugetPath = config.nugetPath    
    def projectPath = config.project
    print("test2")
    stage('Resolve Dependencies: NuGet') {
        print("test3")
        print("${env.WORKSPACE}")
        print("test4") 
        bat "\"${nugetPath}\" restore \"${projectPath}\" ${sources} -OutputDirectory ${env.WORKSPACE}\\packages"
    }
}
