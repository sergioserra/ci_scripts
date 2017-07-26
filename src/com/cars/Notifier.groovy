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

    def notifyEmail(buildStatus,commitAuthor) {
         // evaluate the body block, and collect configuration into the object
         def config = [:]
         def subject = config.subject ? config.subject : "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${buildStatus}!"
         def content = '${JELLY_SCRIPT,template="static-analysis"}'
         // Attach buildlog when the build is not successfull
         def attachLog = (config.attachLog != null) ? config.attachLog : (buildStatus != "SUCCESS")
         // Send email
         script.emailext(
             body: content, mimeType: 'text/html',
             replyTo: '$DEFAULT_REPLYTO', subject: subject, attachmentsPattern: 'app/build/test-results/**/*.xml',
             to: config.emailRecipients, attachLog: attachLog, recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']]
        )
    }

}
