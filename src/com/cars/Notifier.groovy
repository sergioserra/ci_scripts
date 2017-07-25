package com.cars

class Notifier implements Serializable {

    def env;
    def script;

    Notifier(def script) {
      this.script = script
      env = script.env
    }

    def notifySlack( String buildStatus = 'STARTED') {

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

       def msg = "${buildStatus}: `${env.JOB_NAME}` `${getCommitAuthor()}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

       slackSend(color: color, message: msg)

    }

    // Get latest commit author
    def private getCommitAuthor() {
        script.sh 'git log --format="%ae" | head -1 > commit-author.txt'
        return scripts.readFile('commit-author.txt').trim()
    }

}
