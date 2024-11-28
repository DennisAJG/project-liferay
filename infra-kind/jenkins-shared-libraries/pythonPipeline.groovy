def call (body) {

    def settings = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = settings 
    body()

    pipeline {
    agent {
        kubernetes {
            yamlFile 'jenkinsPod.yaml'
            // defaultContainer 'shell'
        }
    }
    stages {
        stage('Unit test') {
            steps {
               pythonUnitTest{}
            }
            when {
                anyOf {
                    branch pattern: "feature-*"
                    branch pattern: "develop"
                    branch pattern: "hotfix-*"
                    branch pattern: "release-*"
                    branch pattern: "v*"
                }
            }
        }
    }
}
}