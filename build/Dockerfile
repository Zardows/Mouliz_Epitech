FROM jenkins/jenkins:lts

USER root

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

ENV CASC_JENKINS_CONFIG /var/jenkins_home/config.yml

COPY job_mouliz.groovy .

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN apt-get update

RUN apt-get -y install make

RUN apt-get install bzip2

RUN apt-get -y install gcc

RUN apt-get -y install jq

RUN cd /tmp

RUN wget https://github.com/Snaipe/Criterion/releases/download/v2.3.2/criterion-v2.3.2-linux-x86_64.tar.bz2

RUN tar xjf criterion-v2.3.2-linux-x86_64.tar.bz2

RUN cp -r criterion-v2.3.2/include/* /usr/local/include

RUN cp -r criterion-v2.3.2/lib/* /usr/local/lib

RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

RUN ldconfig

COPY config.yml /var/jenkins_home/config.yml