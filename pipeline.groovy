pipeline {
    agent any
    stages {
        stage('Building') {
            steps {
                echo 'Building Stage Running...'
                echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"
            }
        }
        stage('Testing') {
            steps {
                echo 'Testing Stage Running...'
            }
        }
        stage('Deploying') {
            steps {
                echo 'Deploying Stage Running...'
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarHome') {
                    bat """
                    sonar-scanner -D"sonar.projectKey=python" ^
                    -D"sonar.sources=." ^
                    -D"sonar.host.url=http://localhost:9000" ^
                    -D"sonar.login=sqp_c84a56847a2f17688bfe80a1d533feac2acab633"
                    """
                }
            }
        }
    }
}
