docker build -t backend-postgres .
docker run -ti --net onboarding-net --ip 172.75.0.3 -p 5432:5432 --name my-postgres-app backend-postgres