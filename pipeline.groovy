pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'echo "Simulating build process"'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'echo "Simulating test execution"'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deployment will be done on my Birthday!!'
                sh 'echo "Simulating deployment process"'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarHome') {
                    sh '''
                    /opt/homebrew/bin/sonar-scanner \
                    -Dsonar.projectKey=java \
                    -Dsonar.sources=. \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.token=sqp_348caaa0b53e96f64f491d166ea004e8a726565b
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
    }
}
