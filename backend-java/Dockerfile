FROM maven:3.8.5-openjdk-11
COPY . /usr/src/onboarding-codeworks
WORKDIR /usr/src/onboarding-codeworks
RUN mvn clean install
EXPOSE 8080
CMD ["java", "-jar", "target/onboarding-1.0-SNAPSHOT.jar"]