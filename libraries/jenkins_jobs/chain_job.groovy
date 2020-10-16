void call(applicationEnv) {
    withEnv(["WORKSPACE=${pwd()}"]) {
        print("${WORKSPACE}")
        build wait: false, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
    }
}
