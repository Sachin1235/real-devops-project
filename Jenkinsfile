pipeline {
    agent any

    environment {
        REGISTRY = "localhost:8083"
        IMAGE_NAME = "devops-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Maven Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh """
                docker build -t ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} .
                """
            }
        }

        stage('Push to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh """
                    echo "$NEXUS_PASS" | docker login ${REGISTRY} -u "$NEXUS_USER" --password-stdin
                    docker push ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}
                    """
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                sh """
                ansible-playbook ansible/deploy.yml \
                --extra-vars "IMAGE_TAG=${IMAGE_TAG}"
                """
            }
        }
    }

    post {
        success {
            echo "PIPELINE SUCCESS ✅"
        }
        failure {
            echo "PIPELINE FAILED ❌"
        }
    }
}
