package com.cars

class Preparation implements Serializable {

    def script

    Preparation(def script){
      this.script = script
    }

    def prepareBuild(){
        cleanWorkspace()
        checkout()
        cleanAndRefreshDependencies()
    }

    def private checkout(){
      script.checkout scm
    }

    def private cleanWorkspace(){
      script.cleanWs()
    }

    def private cleanAndRefreshDependencies(){
        //Cleans the build and the dependencies
        script.sh "./gradlew clean --refresh-dependencies"
    }

}
