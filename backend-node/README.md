# Onboarding codeworks

## Backend node

### Make it run

```sh
docker build -t backend-node .
docker run -ti -p 3000:3000 --name my-node-app backend-node
```

or 

```sh
chmod +x start.sh
./start.sh
```