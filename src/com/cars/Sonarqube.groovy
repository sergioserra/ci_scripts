package com.cars

// Put in a env variable
class Sonarqube implements Serializable{

    def script;

    Sonarqube(def script) {
      this.script = script
    }

    def runPreviewAnalysisForPullRequest(pullRequestId) {

      def scannerHome = script.tool 'SonarScanner'
      script.sh "$scannerHome/bin/sonar-scanner " +
          '-Dsonar.projectName=Android-Cars ' +
          '-Dsonar.projectKey=android-cars ' +
          '-Dsonar.host.url=http://localhost:9000 ' +
          '-Dsonar.login=admin ' +
          '-Dsonar.password=admin ' +
          '-Dsonar.language=java ' +
          '-Dsonar.sources=app/src/main/java ' +
          '-Dsonar.github.repository=AtlasVerticals/android-cars ' +
          '-Dsonar.github.oauth=098fbdbc659876f2e63e370bcff2ab00958bd14e ' +
          '-Dsonar.analysis.mode=preview ' +
          "-Dsonar.github.pullRequest=$pullRequestId"

    }

    def runFullAnalysis() {

      def scannerHome = tool 'SonarScanner'
      script.sh "$scannerHome/bin/sonar-scanner " +
          '-Dsonar.projectName=Android-Cars ' +
          '-Dsonar.projectKey=android-cars ' +
          '-Dsonar.host.url=http://localhost:9000 ' +
          '-Dsonar.login=admin ' +
          '-Dsonar.password=admin ' +
          '-Dsonar.language=java ' +
          '-Dsonar.sources=app/src/main/java ' +
          '-Dsonar.github.repository=AtlasVerticals/android-cars ' +
          '-Dsonar.github.oauth=098fbdbc659876f2e63e370bcff2ab00958bd14e '

    }

}
