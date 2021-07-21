folder('All project'){
    description('Folder of all project.')
}

folder('All project/Tek1'){
    description('Folder of all tek1 projects.')
}

folder('All project/Tek2'){
    description('Folder of all tek2 projects.')
}

freeStyleJob("All project/SEED"){
    parameters{
        choiceParam('EPITECH_YEAR', ['Tek1', 'Tek2', 'Tek3'], 'The epitech year of the project')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
        stringParam('LOGIN_EPITECH', '', 'Login of the student')
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name')
    }
    steps {
        dsl {
            text('''freeStyleJob("All project/$EPITECH_YEAR/$DISPLAY_NAME"){
                wrappers {
                    preBuildCleanup()
                }
                scm {
                    github("$GITHUB_NAME")
                }
                steps{
                    shell('make fclean')
                    shell('make')
                    shell('make tests_run')
                    shell('make clean')
                }
                publishers {
                    extendedEmail {
                        recipientList("$LOGIN_EPITECH")
                        defaultSubject('Moulix result')
                        defaultContent('See atached file for more details')
                        contentType('text/html')
                        triggers {
                            always {
                                attachBuildLog(true)
                                subject('Moulix result')
                                content('See atached file for more details')
                                sendTo {
                                    developers()
                                    recipientList()
                                }
                            }
                        }
                    }
                    mailer("$LOGIN_EPITECH", true, true)
                }
            }''')
        }
    }
}