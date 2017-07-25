package com.cars

class UnitTesting implements Serializable{

    UnitTesting() {}

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

            saveTestResultsFromPath("app/build/test-results/**/*.xml")
        }catch (Exception exception){
            // If the tests fail save the results but rethrow the exception so the build fails
            saveTestResultsFromPath("app/build/test-results/**/*.xml")
            throw exception
        }
    }

    def runAllUnitTests(variant){
        sh "./gradlew runAllUnit${variant}Tests"
    }

    def runSpecificFlavours(flavours,variant){
        def flavoursList = flavours.tokenize(',')
        for (String flavour : flavoursList){
            echo "Running unitTests for ${flavour}${variant}"
            sh "./gradlew test${flavour}${variant}UnitTest"
        }
    }

}
