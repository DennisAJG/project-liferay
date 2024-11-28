def call (body) {

    def settings = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = settings 
    body()

    container('python') {
                    sh '''
                        pip install -r requirements.txt
                        bandit -r . -x '/.venv/','/tests/','/venv/'
	                    black .
	                    flake8 . --exclude venv --ignore=E501
	                    pytest -v --disable-warnings
                        ls -l
                    '''
                }

}