@Notify({ context.step == null && currentBuild.currentResult == 'SUCCESS' })
void call(context) {
    withEnv(["WORKSPACE=${pwd()}"]) {
        build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
    }
}
