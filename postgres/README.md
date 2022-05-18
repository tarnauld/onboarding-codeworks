# Onboarding codeworks

## Backend postgres

### To make it run

```sh
docker build -t backend-postgres .
docker run -ti -p 5432:5432 --name my-postgres-app backend-postgres
```

or

```sh
chmod +x start.sh
./start.sh
```

then run

```sh
docker exec -ti CONTAINER_ID bash
psql -U postgres -f ./dump.sql
```