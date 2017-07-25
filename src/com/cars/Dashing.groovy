package com.cars

// Put env vars
class Dashing implements Serializable {

    Dashing(){}

    def notify(buildResult, branchName) {

      httpRequest 'http://httpbin.org/get?result='
          + buildResult
          + '&branchName='+ branchName
          + '&author=' + getCommitAuthor()

    }


}
