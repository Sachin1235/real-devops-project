pipeline {
 agent any
 environment {
   NEXUS = "localhost:8083"
   IMAGE = "devops-app"
   TAG   = "${BUILD_NUMBER}"
 }
 stages {
   stage('Build Maven') {
     steps { sh './mvnw clean package -DskipTests' }
   }
   stage('Build Docker Image') {
     steps { sh 'docker build -t $NEXUS/$IMAGE:$TAG .' }
   }
   stage('Push to Nexus') {
     steps {
       withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
         sh '''
           docker login $NEXUS -u $USER -p $PASS
           docker push $NEXUS/$IMAGE:$TAG
         '''
       }
     }
   }
   stage('Deploy with Ansible') {
     steps {
       withCredentials([usernamePassword(credentialsId: 'nexus-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
         sh '''
           cd ansible
           ansible-playbook -i inventory.ini deploy.yml \
           --extra-vars "image_tag=$TAG nexus_user=$USER nexus_pass=$PASS"
         '''
       }
     }
   }
 }
}
