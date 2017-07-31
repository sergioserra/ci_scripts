package com.cars

class Preparation implements Serializable {

    def script

    Preparation(def script){
      this.script = script
    }

    def prepareBuild(){
      setEnviromentVariables()
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

    def setEnviromentVariables() {

      // Sonarqube
      script.env.login = "admin"
      script.env.password  = "admin"
      script.env.repository = "AtlasVerticals/android-cars"
      script.env.oauth = "098fbdbc659876f2e63e370bcff2ab00958bd14e"
      script.env.sonarqube = "http://localhost:9000"

      // Dashing
      script.env.dashing = 'http://192.168.5.201:8080/webhook.php?name=LastBuildAndroid&payload='

    }

}
