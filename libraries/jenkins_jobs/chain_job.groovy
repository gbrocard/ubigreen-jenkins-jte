@Notify({ context.step == null && currentBuild.currentResult == 'SUCCESS' })
void call(context) {
    stage("Job: Launching " + config.jobName) {
        print('TEST WORKSPACE ${WORKSPACE}')

        build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: $WORKSPACE)]
    }
}
