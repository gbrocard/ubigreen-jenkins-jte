@Init
void call() {
    stage('Checkout: TFS') {
        options {
            timeout(time: 15, unit: 'MINUTES')
            retry(1)
        }

        checkout([
            $class: 'TeamFoundationServerScm', 
            projectPath: '$/UbigreenProject/UbigreenPerformance',
            credentialsConfigurer: [$class: 'AutomaticCredentialsConfigurer'],
            serverUrl: 'http://tls-ubi-tfs:8080/tfs/UbigreenCollection', 
            useOverwrite: true,
            workspaceName: "Hudson-test-test"
            // workspaceName: "Hudson-${env.JOB_NAME}-${NODE_NAME}"
        ])
    }
}