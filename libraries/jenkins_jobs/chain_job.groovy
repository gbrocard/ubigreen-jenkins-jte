void call(applicationEnv) {
    def workspace = pwd()
    print(workspace)
    print(applicationEnv.jobName)
    build wait: true, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
}
