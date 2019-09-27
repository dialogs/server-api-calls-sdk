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
        stage("Build and test") {
            agent {
                docker {
                    image 'harbor.transmit.im/jnr/jenkins-npm-runner:v10.16.0'
                }
            }
            steps {
                script {
                    env.RELEASE_STAGE = env.STAGE_NAME
                    if (env.BRANCH_NAME.startsWith('PR')) {
                      env.CURRENT_BRANCH = env.CHANGE_BRANCH.replaceAll("/", "-")
                    } else {
                      env.CURRENT_BRANCH = env.BRANCH_NAME.replaceAll("/", "-")
                    }
                    env.PACKAGE_VERSION = sh(script: "grep 'version' package.json | head -1 | cut -d '\"' -f 4", returnStdout: true).trim() + "-" + env.CURRENT_BRANCH + "-" + env.BUILD_NUMBER
                }
                withCredentials([string(credentialsId: 'jenkinsNexus', variable: 'jenkinsNexus')]) {
                    sh """
                        env
                        npm install
                        npm run-script install
                        sed -Ei 's/VERSION/'${env.PACKAGE_VERSION}'/g' ./npm/package.json
                    """
                }
                stash includes: 'npm/*', name: 'buildNPM'
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
        stage("Publish npm master") {
            when {
                branch 'master'
            }
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
        stage("Publish npm release") {
            when {
                branch 'release/*'
            }
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
        stage("Publish npm shapshot") {
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
        stage("Build android") {
            agent {
                docker {
                    image 'harbor.transmit.im/jnr/jenkins-gradle-runner:v5.5_oracle'
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'jenkins_ci_nexus', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME'),
                                [$class: 'UsernamePasswordMultiBinding',credentialsId: 'GITHUB_CREDENTIAL_dialog_bot',usernameVariable: 'GITHUB_USER',passwordVariable: 'GITHUB_PASSWORD']]) {
                    sh """
                        env
                        echo "mavenUser=${NEXUS_USERNAME}" > gradle.properties
                        echo "mavenPassword=${NEXUS_PASSWORD}" >> gradle.properties
                        echo "githubUser=${GITHUB_USER}" >> gradle.properties
                        echo "githubPassword=${GITHUB_PASSWORD}" >> gradle.properties
                        echo "snapshotsRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                        echo "releasesRepoUrl = https://nexus.transmit.im/repository/call-mvn/" >> gradle.properties
                        gradle properties
                        ./gradlew build
                    """
                    stash name: 'gradleBuild', includes: 'build/**'
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
        stage("Publish android snapshot") {
            when {
                anyOf {
                    branch 'develop'
                    allOf {
                        expression{env.BRANCH_NAME != 'master'}
                        expression{env.BRANCH_NAME != 'release/*'}
                        triggeredBy cause: "UserIdCause"
                    }
                }
            }
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
        stage("Publish android release") {
            when {
                branch 'release/*'
            }
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
        stage("Publish android master") {
            when {
                branch 'master'
            }
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
