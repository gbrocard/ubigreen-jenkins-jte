void call(env) {
    withEnv(["WORKSPACE=${pwd()}"]) {
        build wait: false, job: env.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${WORKSPACE}")]
    }
}
