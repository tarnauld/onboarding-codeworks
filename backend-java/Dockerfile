FROM maven:3.8.5-openjdk-11
ENV OKTA_ISSUER="https://dev-06452185.okta.com/oauth2/default"
ENV OKTA_CLIENT_ID="0oa58cfs2n8G5gu7N5d7"
ENV OKTA_CLIENT_SECRET="K-P8ZH0nZ5OOlUkizCeSrKbdr1Oa2Ps491XEx53U"
ENV POSTGRES_USER="postgres"
ENV POSTGRES_PW="toor"
COPY . /usr/src/onboarding-codeworks
WORKDIR /usr/src/onboarding-codeworks
RUN mvn clean install
EXPOSE 8080
CMD ["java", "-jar", "target/onboarding-1.0-SNAPSHOT.jar", "--spring.profiles.active=prod", "--okta.oauth2.issuer=${OKTA_ISSUER}", "--okta.oauth2.clientId=${OKTA_CLIENT_ID}", "--okta.oauth2.clientSecret=${OKTA_CLIENT_SECRET}", "--spring.datasource.username=${POSTGRES_USER}", "--spring.datasource.password=${POSTGRES_PW}"]