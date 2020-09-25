@BeforeStep({context.step.equals("build")})
void call(context) {
    stage('Resolve dependencies: Node') {
        environment {
            NODE_PATH = "${env.WORKSPACE}\\Ubigreen.PerformanceAnalyser.FullWeb\\node_modules"
            gulp = "${env.WORKSPACE}\\Ubigreen.PerformanceAnalyser.FullWeb\\node_modules\\gulp\\bin\\gulp.js"
        }
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
