@CleanUp({ currentBuild.result == 'SUCCESS' })
void call(context) {
    def workspace = pwd()
    print("WORKSPACE PARAM : ${workspace}")
    build wait: false, job: config.jobName, parameters: [[$class: 'StringParameterValue', name: 'upstreamWorkspace', value: workspace]]
}