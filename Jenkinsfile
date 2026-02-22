pipeline {
    agent any

    tools {
        maven 'Maven' // Make sure Maven is configured in Jenkins Global Tool Configuration
        jdk 'Java17'  // Make sure JDK is configured in Jenkins
    }

    environment {
        REPORT_DIR = 'test-output/extent-reports'
    }

    stages {

        stage('Checkout') {
            steps {
                // Checkout your GitHub repo
                git branch: 'main', url: 'https://github.com/YourUsername/selenium-tests.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Run Maven tests
                sh 'mvn clean test'
            }
        }

        stage('Archive Reports') {
            steps {
                // Archive TestNG XMLs and ExtentReports HTML
                archiveArtifacts artifacts: 'test-output/testng-results.xml', allowEmptyArchive: true
                archiveArtifacts artifacts: "${env.REPORT_DIR}/*.html", allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build and tests succeeded!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}