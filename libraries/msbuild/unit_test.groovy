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

def generateTestSingleTask(mstest, testDll, testName) {
    return {
        try {
            echo "Launching ${testName} tests..."
            // if (params.generateCodeCoverage == true) {
            //     bat "\"${OPENCOVER}\" -register:admin -target:\"${mstest}\"  -targetargs:\"/nologo /testcontainer:\"${testDll}\" /resultsfile:\"${RESULTS_LOCATION}\\${testName}Results.trx\"\" -mergebyhash -output:\"${env.WORKSPACE}\\CodeCoverage\\Printing_CodeCoverage.xml\""
            // } else {
            bat("\"${mstest}\" /nologo /testcontainer:${testDll} /resultsfile:${RESULTS_LOCATION}\\${testName}Results.trx")
            // }
        } catch (Exception e) {
            echo e.getMessage()
        }
    }
}

void call() {
    //conf
    String testsPath = config.testsPath;
    String buildConfiguration = config.buildConfiguration;

    stage('Unit tests: C#') {
        //list all tests projects and put them in a list
        def allTestsNames = bat(script: "dir /B \"${testsPath}\"", returnStdout: true).split("\n")

        //map the projects name with their corresponding DLL
        def testDllMap = [:]    
        for(test in allTestsNames) {
            def testName = test.trim(); //remove carriage return
            def dllLocation = "${testsPath}\\${testName}\\bin\\${buildConfiguration}\\${testName}.dll"
            testDllMap[testName] = dllLocation
        }

        // execute all tests
        dir(testsResultPath) {
            parallel generateTestTasks(testDllMap)

            //publish test results
            mstest testResultsFile:"**/*.trx"
        }
    }
}