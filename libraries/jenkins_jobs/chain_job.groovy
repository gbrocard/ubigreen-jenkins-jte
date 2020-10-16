void call(applicationEnv) {
    def workspace = pwd()
    print(workspace)
    print(applicationEnv.jobName)
    build wait: false, job: applicationEnv.jobName, parameters: [string(name: 'upstreamWorkspace', value: workspace)]
    print("sent")
}
