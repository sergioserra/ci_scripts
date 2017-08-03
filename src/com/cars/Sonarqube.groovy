package com.cars

// Put in a env variable
class Sonarqube implements Serializable{

    def script;

    Sonarqube(def script) {
      this.script = script
    }

    def runPreviewAnalysisForPullRequest(pullRequestId) {
      script.sh './gradlew sonarqube -Dsonar.analysis.mode=preview -Dsonar.github.pullRequest='+5
    }

    def runFullAnalysis() {
      script.sh './gradlew sonarqube'
    }

}
