@Init
void call(context) {
    stage("TFS: Checkout") {
        checkout([
            $class: 'TeamFoundationServerScm', 
            projectPath: '$/UbigreenProject/UbigreenPerformance',
            credentialsConfigurer: [$class: 'AutomaticCredentialsConfigurer'],
            serverUrl: 'http://tls-ubi-tfs:8080/tfs/UbigreenCollection', 
            useOverwrite: true,
            workspaceName: "Hudson-${JOB_NAME}-${NODE_NAME}"
        ])
    }
}