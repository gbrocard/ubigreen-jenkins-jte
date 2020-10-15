@Notify({ context.step == null && currentBuild.currentResult == 'SUCCESS' })
void call(context) {
    print("TEST WORKSPACE $WORKSPACE")

    build wait: false, job: config.jobName, parameters: [string(name: 'upstreamWorkspace', value: $WORKSPACE)]
}
