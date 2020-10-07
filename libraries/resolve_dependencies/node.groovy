@BeforeStep({ context.step.equals("build") })
void call(context, closure) {
    print(env.WORKSPACE)
    stage('Resolve dependencies: Node') {
        dir("${env.WORKSPACE}/Ubigreen.PerformanceAnalyser.FullWeb") {
            //remove readonly on package*.json
            bat "attrib -r package.json && attrib -r package-lock.json && npm i"
        }
        dir("${env.WORKSPACE}/Ubigreen.PerformanceAnalyser.FullWeb") {
            //add back readonly on package*.json
            bat "attrib +r package.json && attrib +r package-lock.json"
            print ("ok")
        }
    }
}
