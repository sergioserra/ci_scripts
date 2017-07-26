package com.cars

// Put env vars
class Dashing implements Serializable {

    def script;

    Dashing(def script){
      this.script = script
    }

    def notify(buildResult, branchName) {
      script.httpRequest 'http://httpbin.org/get?result=' + buildResult + '&branchName='+ branchName + '&author=' + getCommitAuthor()
    }

    // Get latest commit author
    def private getCommitAuthor() {
        script.sh 'git log --format="%ae" | head -1 > commit-author.txt'
        return script.readFile('commit-author.txt').trim()
    }
}
