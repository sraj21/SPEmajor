pipeline{
    environment{
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')

	}
    agent any

    stages{
        stage('Git clone'){
            steps{
                git url: 'https://github.com/sraj21/SPEmajor-backend.git',
                branch : 'master'
            }
        }
            
        stage('maven build')
        {
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build Docker Image') {
              steps {
                sh 'docker build -t sraj21/spemajor-backend .'
              }
            }
        stage('Login into DockerHub') {
              steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
              }
            }
        stage('Push to DockerHub') {
              steps {
                sh 'docker push sraj21/spemajor-backend'
              }
            }
        stage('Delete Docker Image from Local'){
                steps {
                    sh 'docker rmi sraj21/spemajor-backend'
                }
            }
        stage("Ansible Deploy"){
                    steps{
        //                 ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'inventory', playbook: 'plybk.yml'
                            sh "ansible-playbook -i inventory playbook.yml"
                    }
                }
        
    }
}
