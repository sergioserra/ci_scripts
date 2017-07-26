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
      script.checkout(script.scm)
    }

    def private cleanWorkspace(){
      script.cleanWs()
    }

    //Cleans the build and the dependencies
    def private cleanAndRefreshDependencies(){
        script.sh "./gradlew clean"// --refresh-dependencies" // TODO remove refresh-dependencies for now
    }

}
