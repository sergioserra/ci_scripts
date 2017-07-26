package com.cars

// Put env vars
class Dashing implements Serializable {

    def script;

    Dashing(def script){
      this.script = script
    }

    def notify(buildResult, branchName, commitAuthor) {
      script.httpRequest 'http://httpbin.org/get?result=' + buildResult + '&branchName='+ branchName + '&author=' + commitAuthor
    }

}
