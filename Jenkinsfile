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
        stage('Docker Build & Run') {
            steps {
                script {
                    docker.build('be-teamstatsfoxs')
                    docker.run('-d -p 8090:8090 be-teamstatsfoxs')
                }
            }
        }
    }
}  
