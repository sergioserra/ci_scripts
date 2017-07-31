package com.cars

// Put env vars
class Fabric implements Serializable {

    def script;

    Fabric(def script){
      this.script = script
    }

    def upload(flavours, branchName, changelog) {
        def releaseNotes = "${changelog}"
        script.env.ORG_GRADLE_PROJECT_BETA_RELEASE_NOTES=releaseNotes // Pass BETA_RELEASE_NOTES to gradle

        def flavoursList = flavours.tokenize(',')
        for (String flavour : flavoursList){
            script.println "Upload to Fabric ${flavour}"
            script.sh "./gradlew crashlyticsUploadDistribution${flavour}Release"
        }
    }

}
