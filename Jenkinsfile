pipeline {
    agent any // Runs on any available Jenkins worker node

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
                sh 'mvn --version'
            }
        }

        stage('Run Automation Tests') {
			steps {
                echo 'Launching Chrome execution...'
                sh "mvn test -Dbrowser=Chrome -Dheadless=true"
            }
        }
    }

    post {
        always {
            echo 'Processing test results...'
            // Publishes JUnit or TestNG XML reports automatically
            junit '**/target/surefire-reports/*.xml'
            
            // Publish Extent HTML Report
            publishHTML(target: [
                allowMissing         : false,
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