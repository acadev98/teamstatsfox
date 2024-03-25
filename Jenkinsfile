pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/acadev98/teamstatsfox'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t be-teamstatsfoxs .'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}  
