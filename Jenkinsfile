pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clonar el repositorio desde GitHub
                git 'https://github.com/acadev98/teamstatsfox'
            }
        }
        stage('Build') {
            steps {
                // Construir la aplicación (por ejemplo, con Maven)
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                // Construir la imagen Docker
                sh 'docker build -t be-teamstatsfoxs .'
            }
        }
        stage('Run Docker Container') {
            steps {
                // Ejecutar el contenedor Docker utilizando docker-compose
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            // Limpiar cualquier recurso residual
            sh 'docker-compose down'
        }
    }
}