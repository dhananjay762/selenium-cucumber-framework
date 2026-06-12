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
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            
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
            
            // Send email with report attached
            emailext(
                subject: "Test Automation Report - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                body: """
                    <html>
                        <body>
                            <h2>Test Automation Report</h2>
                            <table>
                                <tr><td><b>Project:</b></td><td>${env.JOB_NAME}</td></tr>
                                <tr><td><b>Build Number:</b></td><td>#${env.BUILD_NUMBER}</td></tr>
                                <tr><td><b>Status:</b></td><td>${currentBuild.currentResult}</td></tr>
                                <tr><td><b>Duration:</b></td><td>${currentBuild.durationString}</td></tr>
                                <tr><td><b>Report URL:</b></td><td><a href="${env.BUILD_URL}Extent_20Test_20Report/">Click here to view report</a></td></tr>
                                <tr><td><b>Build URL:</b></td><td><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></td></tr>
                            </table>
                            <br/>
                            <p>Please find the attached Extent Report for this build.</p>
                        </body>
                    </html>
                """,
                to: 'dhananjay762@gmail.com',          // ← change to recipient email
                from: 'dhananjay762@gmail.com',        // ← change to your sender email
                mimeType: 'text/html',
                attachmentsPattern: 'target/ExtentReport/TestAutomationReport.html'  // attaches the report
            )
        }
        success {
            echo 'Automation tests completed successfully!'
        }
        failure {
            echo 'Automation tests failed. Please review the archived reports or console output.'
        }
    }
}