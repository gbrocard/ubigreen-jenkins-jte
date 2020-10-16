void call(applicationEnv) {
    build wait: false, quietPeriod: 5, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${pwd()}")]
}
