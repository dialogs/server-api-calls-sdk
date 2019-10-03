@Library('shared-libs@callsGroovy') _

pipeline {
    parameters {
            booleanParam(defaultValue: false, description: 'Create release branch', name: 'createRelease')
    }
    agent none
    environment {
        HOME = "${env.WORKSPACE}"
    }
    stages {
        stage("Import global env vars") {
            agent {
                label 'docker'
            }
            steps{
                script{
                    setPipeEnv.setGlobalEnvVars()
                    sh "env"
                }
            }
            post { 
                always { 
                    cleanWs()
                }
                failure {
                    script {
                        env.failedStage = STAGE_NAME
                    }
                }
            }
        }
        stage("Create release branch") {
            when {
                    expression { return params.createRelease }
            }
            agent {
                docker {
                    image 'harbor.transmit.im/calls/calls-server-builder:master-22959'
                }
            }
            steps {
                script {
                    libCalls.createReleaseBranch()
                }
            }
            post {
                always {
                    cleanWs()
                }
                failure {
                    script {
                        env.failedStage = STAGE_NAME
                    }
                }
            }
        }
        stage("Parallel build/test"){
            parallel {
                stage("Build and test npm") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-npm-runner:v10.16.0'
                        }
                    }
                    steps {
                        script {
                            libCalls.npmBuild()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
                stage("Build and test gradle") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-gradle-runner:v5.5_oracle'
                        }
                    }
                    steps {
                        script {
                            libCalls.gradleBuild()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
            }
        }
        stage("Parallel publish shapshot") {
            when {
                anyOf {
                    triggeredBy cause: "UserIdCause"
                    allOf {
                        expression{env.BRANCH_NAME != 'master'}
                        expression{env.BRANCH_NAME != 'release/.*'}
                    }
                }
            }
            parallel {
                stage("Publish npm shapshot") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-npm-runner:v10.16.0'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishNPMshapshot()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
                stage("Publish gradle snapshot") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-gradle-runner:v5.5_oracle'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishGradleshapshot()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
            }
        }
        stage("Parallel publish master") {
            when {
                branch 'master'
            }
            parallel {
                stage("Publish npm master") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-npm-runner:v10.16.0'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishNPMmaster()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
                stage("Publish gradle master") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-gradle-runner:v5.5_oracle'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishGradleMater()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
            }
        }
        stage("Parallel publish release") {
            when {
                branch 'release/*'
            }
            parallel {
                stage("Publish npm release") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-npm-runner:v10.16.0'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishNPMrelease()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
                stage("Publish gradle release") {
                    agent {
                        docker {
                            image 'harbor.transmit.im/jnr/jenkins-gradle-runner:v5.5_oracle'
                        }
                    }
                    steps {
                        script {
                            libCalls.publishGradleRelease()
                        }
                    }
                    post { 
                        always { 
                            cleanWs()
                        }
                        failure {
                            script {
                                env.failedStage = STAGE_NAME
                            }
                        }
                    }
                }
            }
        }
    }
    post {
        success {
            script {
                node ("docker") {
                    libNotification.jobSuccess()
                }
            }
        }
        failure {
            script {
                node ("docker") {
                    libNotification.jobFailure()
                }
            }
        }
    }
}