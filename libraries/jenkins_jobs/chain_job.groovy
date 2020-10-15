@Notify({ context.step == null && currentBuild.result == 'SUCCESS' })
void call(context) {
    stage ("Launchin Job: " + config.jobName) {
        def workspace = pwd()
        build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
    }
}