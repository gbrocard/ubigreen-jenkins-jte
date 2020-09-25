@BeforeStep({context.step.equals("build")})
void call(context) {
    node {
        stage('Resolve dependencies: Node') {
            dir("merde/Ubigreen.PerformanceAnalyser.FullWeb") {
                //remove readonly on package*.json
                bat "attrib -r package.json && attrib -r package-lock.json && npm i"
            }
            post {
                always {
                    dir("merde/Ubigreen.PerformanceAnalyser.FullWeb") {
                        //add back readonly on package*.json
                        bat "attrib +r package.json && attrib +r package-lock.json"
                    }
                }
            }
        }
    }
}
