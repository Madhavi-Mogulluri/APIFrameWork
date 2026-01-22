pipeline {
    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Build Sample Project') {
            steps {
                dir('sample-project') {
                    git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                    sh 'mvn clean package'
                }
            }
        }

        stage('Checkout API Framework') {
            steps {
                dir('api-framework') {
                    git 'https://github.com/Madhavi-Mogulluri/APIFrameWork.git'
                }
            }
        }

        stage('Deploy to DEV') {
            steps {
                echo 'Deploy to DEV'
            }
        }

        stage('Run Sanity API Tests on DEV') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('api-framework') {
                        sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_sanity.xml -Denv=dev'
                    }
                }
            }
        }

        stage('Deploy to QA') {
            steps {
                echo 'Deploy to QA'
            }
        }

        stage('Run Regression API Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('api-framework') {
                        sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_regression.xml -Denv=qa'
                    }
                }
            }
        }

        stage('Publish Allure Reports') {
            steps {
                dir('api-framework') {
                    allure([
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }

        stage('Publish ChainTest HTML Report') {
            steps {
                dir('api-framework') {
                    publishHTML([
                        allowMissing: false,
                        keepAll: true,
                        reportDir: 'target/chaintest',
                        reportFiles: 'Index.html',
                        reportName: 'API Regression ChainTest Report (QA)'
                    ])
                }
            }
        }

        stage('Deploy to PROD') {
            steps {
                echo 'Deploy to PROD'
            }
        }

        stage('Run Sanity API Tests on PROD') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    dir('api-framework') {
                        sh 'mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_sanity.xml -Denv=prod'
                    }
                }
            }
        }
    }
}
