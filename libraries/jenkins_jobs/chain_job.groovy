void call(env) {
    print("ok")
    print(env.jobName)
    withEnv(["WORKSPACE=${pwd()}"]) {
        build wait: false, job: env.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
    }
}
