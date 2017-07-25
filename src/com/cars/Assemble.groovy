package com.cars

class Assemble implements Serializable{

    def script;

    Assemble(def script) {
      this.script = script
    }

    def assembleReleaseBuildWithTask(task){
      script.sh "./gradlew -PBUILD_NUMBER=${steps.env.BUILD_NUMBER} ${task}"
    }

    def assembleBuildsWithNames(flavours, variant){
        def flavoursList = flavours.tokenize(',')
        for (String flavour : flavoursList){
            println "Assemble ${flavour}${variant}"
            script.sh "./gradlew -PBUILD_NUMBER=${steps.env.BUILD_NUMBER} assemble${flavour}${variant}"
        }
    }

    def assembleBuilds(flavours, variant = "Release"){
        if(flavours == "All"){
            assembleReleaseBuildWithTask("AssembleAll${variant}")
        }else{
            assembleBuildsWithNames(flavours,variant)
        }
    }

    def archiveApk(buildResult){
      if (buildResult == "SUCCESS") {
        script.archiveArtifacts '**/build/**/*.apk'
      }
    }

}
