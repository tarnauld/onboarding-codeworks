FROM maven:3.8.5-openjdk-11
ENV OKTA_ISSUER="default"
ENV OKTA_CLIENT_ID="default"
ENV OKTA_CLIENT_SECRET="default"
ENV POSTGRES_USER="default"
ENV POSTGRES_PW="default"
COPY . /usr/src/onboarding-codeworks
WORKDIR /usr/src/onboarding-codeworks
RUN mvn clean install
EXPOSE 8080
CMD ["java", "-jar", "target/onboarding-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod", "--okta.oauth2.issuer=${OKTA_ISSUER}", "--okta.oauth2.clientId=${OKTA_CLIENT_ID}", "--okta.oauth2.clientSecret=${OKTA_CLIENT_SECRET}", "--spring.datasource.username=${POSTGRES_USER}", "--spring.datasource.password=${POSTGRES_PW}"]