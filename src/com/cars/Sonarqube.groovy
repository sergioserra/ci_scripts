package com.cars

// Put in a env variable
class Sonarqube implements Serializable{

    def script;

    Sonarqube(def script) {
      this.script = script
    }

    def runPreviewAnalysisForPullRequest(pullRequestId) {

      script.sh './gradlew sonarqube -Dsonar.analysis.mode=preview -Dsonar.github.pullRequest='+pullRequestId

      /*def scannerHome = script.tool "SonarScanner"
      script.sh "$scannerHome/bin/sonar-scanner " +
          "-Dsonar.projectName=Android-Cars " +
          "-Dsonar.projectKey=android-cars " +
          "-Dsonar.host.url=${script.env.sonarqube} " +
          "-Dsonar.login=${script.env.login} " +
          "-Dsonar.password=${script.env.password} " +
          "-Dsonar.language=java " +
          "-Dsonar.sources=app/src/main/java " +
          "-Dsonar.github.repository=${script.env.repository} " +
          "-Dsonar.github.oauth=${script.env.oauth} " +
          "-Dsonar.analysis.mode=preview " +
          "-Dsonar.github.pullRequest=$pullRequestId" */

    }

    def runFullAnalysis() {

      script.sh './gradlew sonarqube'

      /*def scannerHome = tool "SonarScanner"
      script.sh "$scannerHome/bin/sonar-scanner " +
          "-Dsonar.projectName=Android-Cars " +
          "-Dsonar.projectKey=android-cars " +
          "-Dsonar.host.url=${script.env.sonarqube} " +
          "-Dsonar.login=${script.env.login} " +
          "-Dsonar.password=${script.env.password} " +
          "-Dsonar.language=java " +
          "-Dsonar.sources=app/src/main/java " +
          "-Dsonar.github.repository=${script.env.repository} " +
          "-Dsonar.github.oauth=${script.env.oauth} "*/

    }

}
