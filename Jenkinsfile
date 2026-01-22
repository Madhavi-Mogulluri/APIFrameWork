pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout API Framework') {
            steps {
                git 'https://github.com/Madhavi-Mogulluri/APIFrameWork.git'
            }
        }

        stage('Deploy to DEV') {
            steps {
                echo 'Deploying application to DEV environment'
            }
        }

        stage('Run Sanity API Tests on DEV') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_sanity.xml -Denv=dev'
                }
            }
        }

        stage('Deploy to QA') {
            steps {
                echo 'Deploying application to QA environment'
            }
        }

        stage('Run Regression API Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_regression.xml -Denv=qa'
                }
            }
        }

        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }

        stage('Publish ChainTest HTML Report') {
            steps {
                script {
                    publishHTML([
                        allowMissing: false,
                        keepAll: true,
                        reportDir: 'target/chaintest',
                        reportFiles: 'Index.html',
                        reportName: 'API Automation Report (QA)'
                    ])
                }
            }
        }

        stage('Deploy to PROD') {
            steps {
                echo 'Deploying application to PROD environment'
            }
        }

        stage('Run Sanity API Tests on PROD') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_sanity.xml -Denv=prod'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }
    }
}
