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

    // Get latest commit author
    def private getCommitAuthor() {
        sh 'git log --format="%ae" | head -1 > commit-author.txt'
        return readFile('commit-author.txt').trim()
    }
}
