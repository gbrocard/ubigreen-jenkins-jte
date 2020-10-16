void call(applicationEnv) {
    print("ok")
    print(applicationEnv.jobName)
    withEnv(["WORKSPACE=${pwd()}"]) {
        build wait: false, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
    }
}
