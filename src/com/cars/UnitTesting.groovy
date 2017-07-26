package com.cars

class UnitTesting implements Serializable{

    def script

    UnitTesting(def script) {
      this.script = script
    }

    /**
     * Defaults to run all the release unit tests for the application
     * but you can choose to build only specific flavours and build types
     */
    def runUnitTests(flavours = "All",variant = "Debug"){
        try {

            if(flavours == "All"){
                runAllUnitTests(variant)
            }else{
                runSpecificFlavours(flavours,variant)
            }

            saveTestResults()
        }catch (Exception exception){
            // If the tests fail save the results but rethrow the exception so the build fails
            saveTestResults()
            throw exception
        }
    }

    def private saveTestResults() {
        script.junit 'app/build/test-results/**/*.xml'
    }

    def runAllUnitTests(variant){
        script.sh "./gradlew runAllUnit${variant}Tests"
    }

    def runSpecificFlavours(flavours,variant){
        def flavoursList = flavours.tokenize(',')
        for (String flavour : flavoursList){
            script.echo "Running unitTests for ${flavour}${variant}"
            script.sh "./gradlew test${flavour}${variant}UnitTest"
        }
    }

}
