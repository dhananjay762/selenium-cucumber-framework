pipeline {
    agent any // Runs on any available Jenkins worker node
    
    tools {
        maven 'Maven3'  
    }

    environment {
        TEST_REPORTS_DIR = "target/surefire-reports"
        EXTENT_REPORT_DIR = "target/ExtentReport"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Pull code from your SCM repository
                checkout scm
            }
        }

        stage('Environment Setup') {
            steps {
                // Verify background language/tool versions (e.g., Maven)
                echo 'Checking tool versions...'
                bat 'mvn --version'
            }
        }

        stage('Run Automation Tests') {
			steps {
                echo 'Launching Chrome execution...'
                bat "mvn test -Dbrowser=Chrome -Dheadless=true"
            }
        }
    }

    post {
        always {
            echo 'Processing test results...'
            // Publishes JUnit or TestNG XML reports automatically
            junit allowEmptyResults: true, '**/target/surefire-reports/*.xml'
            
            // Publish Extent HTML Report
            publishHTML(target: [
                allowMissing         : true,
                alwaysLinkToLastBuild: true,
                keepAll              : true,
                reportDir            : 'target/ExtentReport',
                reportFiles          : 'TestAutomationReport.html',
                reportName           : 'Extent Test Report',
                reportTitles         : 'Automation Test Report'
            ])
        }
        success {
            echo 'Automation tests completed successfully!'
        }
        failure {
            echo 'Automation tests failed. Please review the archived reports or console output.'
        }
    }
}