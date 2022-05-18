# Onboarding codeworks

## Prerequisite

Every script should be run as `root`.

```sh
docker network create --subnet=172.75.0.0/16 onboarding-net
```

or

```sh
./start.sh
```

## Postgres

```sh
./postgres/start.sh
```

## Backend java

```sh
./backend-java/start.sh
```

## Backend node

```sh
./backend-node/start.sh
```

## Frontend