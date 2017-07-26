package com.cars

class Notifier implements Serializable {

    def env;
    def script;

    Notifier(def script) {
      this.script = script
      env = script.env
    }

    def notifySlack( String buildStatus = 'STARTED', commitAuthor) {

       // Build status of null means success.
       buildStatus = buildStatus ?: 'SUCCESS'

       def color

       if (buildStatus == 'STARTED') {
           color = '#D4DADF'
       } else if (buildStatus == 'SUCCESS') {
           color = '#BDFFC3'
       } else if (buildStatus == 'UNSTABLE') {
           color = '#FFFE89'
       } else {
           color = '#FF9FA1'
       }

       def msg = "${buildStatus}: `${env.JOB_NAME}` `${commitAuthor}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

       script.slackSend(color: color, message: msg)

    }

}
