## Documentation

### Pre-requisites

To run the project, you should have the following requirements:

* `docker`
* `docker-compose`
* the `vault` CLI

Check out the docker [documentation](https://docs.docker.com/) for more information.

I am currently running on MacOS and use [Rancher Desktop](https://rancherdesktop.io/) to manage my container and kubernetes clusters. It's a pretty cool alternative to Docker Desktop but you use the manager you want.

### Configure Vault

For this part, I recommend you to read this cool [tutorial](https://blog.ruanbekker.com/blog/2019/05/06/setup-hashicorp-vault-server-on-docker-and-cli-guide/) written by Ruan Bekker which will guide you to the vault installation and setup.

This is a short description:

First, you need to create a docker container to run vault:

Create a directory named `vault`:

```bash
mkdir vault
touch docker-compose.yml
mkdir -p volumes/{config,file,logs}
```

And run:

```bash
cat > volumes/config/vault.json << EOF
{
  "backend": {
    "file": {
      "path": "/vault/file"
    }
  },
  "listener": {
    "tcp":{
      "address": "0.0.0.0:8200",
      "tls_disable": 1
    }
  },
  "ui": true
}
EOF
```

Then create your docker-compose.yml file:

```bash
cat > docker-compose.yml << EOF
version: '2'
services:
  vault:
    image: vault
    container_name: vault
    ports:
      - "8200:8200"
    restart: always
    volumes:
      - ./volumes/logs:/vault/logs
      - ./volumes/file:/vault/file
      - ./volumes/config:/vault/config
    cap_add:
      - IPC_LOCK
    entrypoint: vault server -config=/vault/config/vault.json
EOF
```

Finally run the vault server using:

```bash 
docker-compose up
```

The UI is now available at [http://127.0.0.1:8200](http://127.0.0.1:8200).

Now, you need to install the tool which allows you to communicate with the API:

```bash
brew install vault
```

and set the environment variable:

```bash
export VAULT_ADDR=http://127.0.0.1:8200
```

...checkout the tutorials for more details.

### Okta App creation

I used Okta SSO for both backend and frontend. You can create a free developer account on the [official website](https://developer.okta.com/signup/).



### Create Secrets

For this project, you need to create 5 secrets inside the vault using the KV engine:

* `OKTA_CLIENT_ID`
* `OKTA_CLIENT_SECRET`
* `OKTA_ISSUER`
* `POSTGRES_PW`
* `POSTGRES_USER`

You can create them using the UI or with the following commands:

```bash
vault kv put kv/onboarding-codeworks \
OKTA_CLIENT_ID=************  \
OKTA_CLIENT_SECRET=************ \
OKTA_ISSUER=************ \
POSTGRES_PW=************ \
POSTGRES_USER=************
```

### Frontend

In the `main.ts` file, change the following lines with your own values:

```ts
const oktaAuth = new OktaAuth({
  issuer: "https://dev-06452185.okta.com/oauth2/default",
  clientId: "0oa58c5rg5SuL2WcY5d7",
  redirectUri: window.location.origin + "/login/callback",
  scopes: ["openid", "profile", "email"],
});
```

### Run the project

In order to dynamically load the secrets from vault, I wrote a simple script who takes two arguments:

1. VAULT_ADDR
2. VAULT_TOKEN

You can call it using:

```bash
cd onboarding-codeworks
./start.sh $VAULT_ADDR $VAULT_TOKEN
```

### After that

#### Postgres

Connect to the postgres container:

```bash
docker exec -ti onboarding-codeworks-my-postgres-app-1 bash
psql -U postgres -f ./dump.sql
```

#### Grafana

Import the json file to enable the default dashboard I created.
Be careful and replace the default prometheus uid in the configuration file before.

Example:

```json
"panels": [
    {
      "datasource": {
        "type": "prometheus",
        "uid": "NQ4Y9eunz" //Replace this value using the current value
      },
      "fieldConfig": {
        "defaults": {
          "color": {
            "mode": "palette-classic"
          },
//...
```

### Support or Contact

Having a problem with the project? Please contact me by [email](mailto:timothee.arnauld1gmail.com) or submit an [issue](https://github.com/tarnauld/onboarding-codeworks/issues).
