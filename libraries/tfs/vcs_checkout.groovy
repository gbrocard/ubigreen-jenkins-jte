void call() {
    stage("TFS: Checkout") {
        print("Reset workspace ${!params.resetWorkspace ?: true}")

        checkout([
            $class: 'TeamFoundationServerScm', 
            projectPath: '$/UbigreenProject/UbigreenPerformance',
            credentialsConfigurer: [$class: 'AutomaticCredentialsConfigurer'],
            serverUrl: 'http://tls-ubi-tfs:8080/tfs/UbigreenCollection', 
            useOverwrite: true,
            useUpdate: params.resetWorkspace == null ? true : params.resetWorkspace,
            workspaceName: 'Hudson-${JOB_NAME}-${NODE_NAME}'
        ])
    }
}