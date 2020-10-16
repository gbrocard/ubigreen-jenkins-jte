void call(applicationEnv) {
    step("Job: " + applicationEnv.jobName) {
        build job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
    }
}
