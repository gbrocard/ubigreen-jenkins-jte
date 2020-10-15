void call() {
    stage("TFS: Checkout") {
        checkout([
            $class: 'TeamFoundationServerScm', 
            projectPath: '$/UbigreenProject/UbigreenPerformance',
            credentialsConfigurer: [$class: 'AutomaticCredentialsConfigurer'],
            serverUrl: 'http://tls-ubi-tfs:8080/tfs/UbigreenCollection', 
            useOverwrite: true,
            useUpdate: true, 
            workspaceName: 'Hudson-${JOB_NAME}-${NODE_NAME}'
        ])
    }
}