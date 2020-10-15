@CleanUp({ currentBuild.result == 'SUCCESS' })
void call(context) {
    build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: pwd())]
}
