#!/bin/groovy

properties([
            [
            $class  : 'jenkins.model.BuildDiscarderProperty',
            strategy: [
                    $class: 'LogRotator',
                    numToKeepStr        : "2",
                    artifactNumToKeepStr: "1"
            ]]
    ])

node {
    stage('Checkout') {
        checkout scm
        sh "rm -rf dist/ && mkdir dist/"
    }

    stage('Build') {
        withMaven(maven: 'maven-3.3.9') {
            sh "mvn clean compile"
        }
    }
    
    stage('Test') {
        withMaven(maven: 'maven-3.3.9') {
            sh "mvn test"
        }
    }
    
    stage('Package') {
        withMaven(maven: 'maven-3.3.9') {
            sh "mvn package"
            sh "mv target/*.jar dist/"
        }
    }
    
    stage('Archive') {
        archiveArtifacts artifacts: "dist/*"
        cleanWs()
    }
}