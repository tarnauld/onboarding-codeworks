docker build -t frontend .
docker run --net onboarding-net --ip 172.75.0.7 --name my-frontend -d -p 4000:80 frontend