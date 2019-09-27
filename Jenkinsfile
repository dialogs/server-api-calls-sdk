@Library('shared-libs') _

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
                    env.PACKAGE_VERSION = sh(script: "grep 'version' package.json | head -1 | cut -d '\"' -f 4", returnStdout: true).trim()
                }
                withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                credentialsId: 'Bitbucket_jenkins_user',
                                usernameVariable: 'GIT_USERNAME',
                                passwordVariable: 'GIT_PASSWORD']]) {
                    sh """
                        git checkout -b release/${env.PACKAGE_VERSION}
                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@bitbucket.transmit.im/scm/calls/server-api-calls-sdk.git release/${env.PACKAGE_VERSION}
                    """
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
                        libCalls.npmBuild()
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
                        libCalls.gradleBuild()
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
                    branch 'develop'
                    allOf {
                        expression{env.BRANCH_NAME != 'master'}
                        expression{env.BRANCH_NAME != 'release/.*'}
                        triggeredBy cause: "UserIdCause"
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
                        unstash 'buildNPM'

                        withCredentials([string(credentialsId: 'jenkinsNexus', variable: 'jenkinsNexus')]) {
                            sh """
                                cd npm
                                npm set registry "https://nexus.transmit.im/repository/calls-libraries/"
                                npm set //nexus.transmit.im/repository/calls-libraries/:_authToken=${env.jenkinsNexus}
                                npm publish --registry=https://nexus.transmit.im/repository/calls-libraries/ --tag=${CURRENT_BRANCH}-latest
                            """
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
                        withCredentials([usernamePassword(credentialsId: 'jenkins_ci_nexus', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                            unstash 'gradleBuild'
                            sh """
                                env
                                echo "mavenUser=${NEXUS_USERNAME}" > gradle.properties
                                echo "mavenPassword=${NEXUS_PASSWORD}" >> gradle.properties
                                echo "snapshotsRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                echo "releasesRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                gradle properties
                                ./gradlew publish
                            """
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
                        unstash 'buildNPM'
                        withCredentials([string(credentialsId: 'jenkinsNexus', variable: 'jenkinsNexus')]) {
                            sh """
                                cd npm
                                npm set registry "https://nexus.transmit.im/repository/calls-libraries/"
                                npm set //nexus.transmit.im/repository/calls-libraries/:_authToken=${env.jenkinsNexus}
                                npm publish --registry=https://nexus.transmit.im/repository/calls-libraries/ --tag=latest
                            """
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
                        withCredentials([usernamePassword(credentialsId: 'jenkins_ci_nexus', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                            unstash 'gradleBuild'
                            sh """
                                env
                                echo "mavenUser=${NEXUS_USERNAME}" > gradle.properties
                                echo "mavenPassword=${NEXUS_PASSWORD}" >> gradle.properties
                                echo "snapshotsRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                echo "releasesRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                gradle properties
                                ./gradlew publish
                            """
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
                        unstash 'buildNPM'
                        withCredentials([string(credentialsId: 'jenkinsNexus', variable: 'jenkinsNexus')]) {
                            sh """
                                cd npm
                                npm set registry "https://nexus.transmit.im/repository/calls-libraries/"
                                npm set //nexus.transmit.im/repository/calls-libraries/:_authToken=${env.jenkinsNexus}
                                npm publish --registry=https://nexus.transmit.im/repository/calls-libraries/ --tag=RELEASE-latest
                            """
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
                        withCredentials([usernamePassword(credentialsId: 'jenkins_ci_nexus', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                            unstash 'gradleBuild'
                            sh """
                                env
                                echo "mavenUser=${NEXUS_USERNAME}" > gradle.properties
                                echo "mavenPassword=${NEXUS_PASSWORD}" >> gradle.properties
                                echo "snapshotsRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                echo "releasesRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                                gradle properties
                                ./gradlew publish
                            """
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
