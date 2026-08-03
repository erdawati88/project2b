pipeline {
    // Run the pipeline on any available Jenkins agent
    agent any

    // Configure the build tools installed in Jenkins
    tools {
        maven 'Maven 3.9.10'
        jdk 'Java JDK 17'
    }

    stages {

        // Stage 1: Build the project and execute all unit tests
        stage('Build & Test') {
            steps {
                echo 'Starting Maven Build'
                bat 'mvn clean verify'
            }
        }

        // Stage 2: Run SonarQube static code analysis
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube_server') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

    }

    // Actions to perform after the pipeline completes
    post {
        always {

            // Publish JUnit XML test results
            junit 'target/surefire-reports/*.xml'

            // Publish JaCoCo HTML code coverage report
            publishHTML(target: [
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/site/jacoco',
                reportFiles: 'index.html',
                reportName: 'JaCoCo Code Coverage Report'
            ])
        }
    }
}