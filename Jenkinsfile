pipeline {
    agent any
    tools {
        maven 'Maven 3.9.10'
        jdk 'Java JDK 17'
    }
    stages {
        stage("Build & Test") {
            steps {
                echo "Starting Maven Build"
                bat "mvn clean verify"
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}