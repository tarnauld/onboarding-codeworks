docker build -t backend-node .
docker run --net onboarding-net --ip 172.75.0.2 --name my-node-app -p 3000:3000 backend-node
