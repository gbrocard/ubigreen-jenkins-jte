@Notify({ context.step == null && currentBuild.currentResult == 'SUCCESS' })
void call(context) {
    def workspace = pwd()
    print("TEST WORKSPACE ${workspace}")

    build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
}
