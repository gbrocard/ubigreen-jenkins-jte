void call(applicationEnv) {
    build job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: "${pwd()}")]
}
