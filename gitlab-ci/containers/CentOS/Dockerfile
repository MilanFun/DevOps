FROM centos
COPY pom.xml pom.xml
COPY maven.sh /etc/profile.d/maven.sh
RUN chmod +x /etc/profile.d/maven.sh
RUN cd /etc/yum.repos.d/
RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*
RUN cd /
RUN yum -y update
RUN yum -y install java-11-openjdk java-11-openjdk-devel
RUN update-alternatives --set java /usr/lib/jvm/java-11-openjdk-11.0.13.0.8-4.el8_5.x86_64/bin/java
RUN yum -y install wget
RUN yum install -y maven
RUN wget "https://dlcdn.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz" -P /tmp
RUN tar xf /tmp/apache-maven-3.6.3-bin.tar.gz -C /opt && ln -s /opt/apache-maven-3.6.3 /opt/maven
RUN /etc/profile.d/maven.sh
RUN mvn install