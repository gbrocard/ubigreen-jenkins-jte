@CleanUp({ currentBuild.result == 'SUCCESS' })
void call(context) {
    stage('Job: ' + config.jobName) {
        String workspace = pwd()
        print("TEST WORKSPACE ${workspace}")

        build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
    }
}
