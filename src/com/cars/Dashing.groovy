package com.cars

import java.net.URLEncoder

// Put env vars
class Dashing implements Serializable {

    def script;

    Dashing(def script){
      this.script = script
    }

    def notify(buildResult, branchName, commitAuthor) {
      def String payload = URLEncoder.encode('{ "buildResult":"'+buildResult+'\","branchName":\"'+branchName+'\","commitAuthor":\"'+commitAuthor+'\"}','UTF-8')
      script.httpRequest "http://192.168.5.201:8080/webhook.php?name=LastBuildAndroid&payload="+payload
    }

}
