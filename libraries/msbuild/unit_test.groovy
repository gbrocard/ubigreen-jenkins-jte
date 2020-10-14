void call() {
    stage("MSBuild: Unit tests") {
        def allTestsNames = bat(script: "dir /B \"%WORKSPACE%\\${config.TESTS_PATH}\"", returnStdout: true).split("\n")
        def workspace = pwd()

        //map the projects name with their corresponding DLL
        def testDllMap = [:]
        for(test in allTestsNames) {
            def testName = test.trim(); //remove carriage return
            def dllLocation = "${workspace}\\${config.TESTS_PATH}\\${testName}\\bin\\${config.buildConfiguration}\\${testName}.dll"

            testDllMap[testName] = dllLocation
        }
        
        // execute all tests
        dir(config.RESULTS_PATH) {
            parallel generateTestTasks(testDllMap)
        }
    }
}

def generateTestSingleTask(mstest, testDll, testName) {
    return {
        try {
            print("Launching ${testName} tests...")
            bat("\"${mstest}\" /nologo /testcontainer:${testDll} /resultsfile:${config.RESULTS_PATH}\\${testName}Results.trx")
        } catch (Exception e) {
            print(e.getMessage())
        }
    }
}

def generateTestTasks(testDllMap) {
    def mstest = tool('MSTest')
    def testTasksMap = [:]
    
    testDllMap.each { test ->
        def testName = test.key;
        def testDll = test.value;
    
        def dllExists = fileExists(testDll)
        if (dllExists) {
            testTasksMap[testName] = generateTestSingleTask(mstest, testDll, testName)
        }
    }
    return testTasksMap;
}
