docker build -t backend-java .
docker run -ti --net onboarding-net --ip 172.75.0.4 --name my-java-app -p 8080:8080 backend-java