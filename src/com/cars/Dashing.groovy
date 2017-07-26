package com.cars

import java.net.URLEncoder

// Put env vars
class Dashing implements Serializable {

    def script;

    Dashing(def script){
      this.script = script
    }

    def notify(buildResult, branchName, commitAuthor) {
      def payload = "{'buildResult' : '${buildResult}','branchName' : '${branchName}','commitAuthor' : '${commitAuthor}'}"
      script.httpRequest 'http://192.168.5.201:8080?name=LastBuildAndroid&payload'<<URLEncoder.encode(payload, "UTF-8")
    }

}
