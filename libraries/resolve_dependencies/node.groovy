@BeforeStep({ context.step.equals("build") })
void call(context, closure) {
    stage('Resolve dependencies: Node') {
        dir("${env.WORKSPACE}/Ubigreen.PerformanceAnalyser.FullWeb") {
            //remove readonly on package*.json
            bat "attrib -r package.json && attrib -r package-lock.json && npm i"
        }
        post {
            always {
                dir("${env.WORKSPACE}/Ubigreen.PerformanceAnalyser.FullWeb") {
                    //add back readonly on package*.json
                    bat "attrib +r package.json && attrib +r package-lock.json"
                }
            }
        }
    }
}
