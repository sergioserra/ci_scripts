package com.cars

class Preparation implements Serializable {

    Preparation(){}

    def prepareBuild(){
        cleanWorkspace()
        checkout()
        cleanAndRefreshDependencies()
    }

    def private checkout(){
      checkout scm
    }

    def private cleanWorkspace(){
      cleanWs()
    }

    def private cleanAndRefreshDependencies(){
        //Cleans the build and the dependencies
        sh "./gradlew clean --refresh-dependencies"
    }

}
