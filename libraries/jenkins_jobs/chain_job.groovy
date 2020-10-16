void call(applicationEnv) {
    withEnv(["WORKSPACE=${pwd()}"]) {
        build wait: false, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
        print("sent")
    }
}
